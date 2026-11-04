package com.igoo.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantCommand {

    private static final String NAME = "enchant";
    private static final int MIN_LEVEL = 1;
    private static final int MAX_LEVEL = 255;

    public static LiteralArgumentBuilder<CommandSourceStack> build(CommandBuildContext ctx) {
        return Commands.literal(NAME)
                .then(Commands.argument("enchantment",
                                ResourceArgument.resource(ctx, Registries.ENCHANTMENT))
                        .then(Commands.argument("level",
                                        IntegerArgumentType.integer(MIN_LEVEL, MAX_LEVEL))
                                .executes(EnchantCommand::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();

        // Phải là player (không dùng từ console)
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("§cLệnh này chỉ dùng được bởi người chơi!"));
            return 0;
        }

        // Tay chính phải cầm item
        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            source.sendFailure(Component.literal("§cTay chính phải cầm vật phẩm!"));
            return 0;
        }

        // Lấy enchantment
        Holder<Enchantment> enchantment;
        try {
            enchantment = ResourceArgument.getEnchantment(ctx, "enchantment");
        } catch (Exception e) {
            source.sendFailure(Component.literal("§cKhông tìm thấy enchantment!"));
            return 0;
        }

        int level = IntegerArgumentType.getInteger(ctx, "level");

        // Enchant bất kỳ item
        EnchantmentHelper.updateEnchantments(stack,
                mutable -> mutable.set(enchantment, level));

        // Thông báo
        String enchantName = enchantment.value().description().getString();
        String itemName = stack.getHoverName().getString();
        source.sendSuccess(() -> Component.literal(
                "§aĐã enchant §e" + enchantName + " " + toRoman(level)
                        + " §avào §e" + itemName + " §a(level " + level + ")"
        ), false);

        return 1;
    }

    // Chuyển số → số La Mã (1–255)
    private static String toRoman(int n) {
        if (n <= 0 || n > 3999) return String.valueOf(n);

        int[] values =    {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (n >= values[i]) {
                sb.append(symbols[i]);
                n -= values[i];
            }
        }
        return sb.toString();
    }
}