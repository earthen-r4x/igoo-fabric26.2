package com.igoo.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class HealCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("heal")
                .executes(HealCommand::execute);
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("§cChỉ player mới dùng được!"));
            return 0;
        }
        player.setHealth(player.getMaxHealth());
        player.getFoodData().setFoodLevel(20);
        source.sendSuccess(() -> Component.literal("§aĐã hồi đầy máu và thức ăn!"), false);
        return 1;
    }
}