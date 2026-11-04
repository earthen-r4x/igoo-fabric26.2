package com.igoo.command;

import com.igoo.entity.BotPlayer;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FakePlayerCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("player")
                .then(Commands.literal("spawn")
                        .then(Commands.argument("count", IntegerArgumentType.integer(1, 50))
                                .executes(FakePlayerCommand::executeSpawn)))
                .then(Commands.literal("list")
                        .executes(FakePlayerCommand::executeList))
                .then(Commands.literal("remove")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .suggests(FakePlayerCommand::suggestBotNames)
                                .executes(FakePlayerCommand::executeRemove)))
                .then(Commands.literal("mode")
                        .then(Commands.argument("mode", StringArgumentType.word())
                                .suggests(FakePlayerCommand::suggestModes)
                                .executes(ctx -> setModeFor(ctx, null))
                                .then(Commands.argument("name", StringArgumentType.word())
                                        .suggests(FakePlayerCommand::suggestBotNames)
                                        .executes(ctx -> setModeFor(ctx,
                                                StringArgumentType.getString(ctx, "name"))))));
    }

    private static int executeSpawn(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
        int count = IntegerArgumentType.getInteger(ctx, "count");
        ServerPlayer spawner = source.getPlayer();
        int spawned = FakePlayerManager.spawnFakePlayers(source.getServer(), spawner, count);
        if (spawned <= 0) {
            source.sendFailure(Component.literal("§cKhông thể triệu hồi bot."));
            return 0;
        }
        source.sendSuccess(() -> Component.literal("§aĐã triệu hồi §e" + spawned + " §abot."), true);
        return spawned;
    }

    private static int executeList(CommandContext<CommandSourceStack> ctx) {
        List<BotPlayer> bots = collectBots(ctx);
        CommandSourceStack source = ctx.getSource();

        if (bots.isEmpty()) {
            source.sendSuccess(() -> Component.literal("§eKhông có bot nào."), false);
            return 0;
        }

        source.sendSuccess(() -> Component.literal(
                "§6=== Bot đang active (" + bots.size() + ") ==="), false);

        for (BotPlayer bot : bots) {
            BlockPos pos = bot.blockPosition();
            // 👈 getName() → name()
            String botName = bot.getGameProfile().name();
            source.sendSuccess(() -> Component.literal(String.format(
                    "§e- §f%s §7| §amode=§f%s §7| §f(%d, %d, %d) §7| §c%.0f/%.0f HP",
                    botName,
                    bot.getMode().name().toLowerCase(),
                    pos.getX(), pos.getY(), pos.getZ(),
                    bot.getHealth(), bot.getMaxHealth())), false);
        }
        return bots.size();
    }

    private static int executeRemove(CommandContext<CommandSourceStack> ctx) {
        String name = StringArgumentType.getString(ctx, "name");
        List<BotPlayer> toRemove = new ArrayList<>();
        CommandSourceStack source = ctx.getSource();

        if (name.equalsIgnoreCase("all")) {
            toRemove.addAll(collectBots(ctx));
        } else {
            ServerPlayer p = source.getServer().getPlayerList().getPlayerByName(name);
            if (p instanceof BotPlayer bot) toRemove.add(bot);
        }

        if (toRemove.isEmpty()) {
            source.sendFailure(Component.literal("§cKhông tìm thấy bot: " + name));
            return 0;
        }

        for (BotPlayer bot : toRemove) {
            source.getServer().getPlayerList().remove(bot);
        }
        final int count = toRemove.size();
        source.sendSuccess(() -> Component.literal("§aĐã xóa §e" + count + " §abot."), false);
        return count;
    }

    private static int setModeFor(CommandContext<CommandSourceStack> ctx, String botName) {
        String modeName = StringArgumentType.getString(ctx, "mode").toUpperCase();
        BotPlayer.Mode mode;
        try {
            mode = BotPlayer.Mode.valueOf(modeName);
        } catch (IllegalArgumentException e) {
            ctx.getSource().sendFailure(Component.literal("§cMode không hợp lệ: " + modeName));
            return 0;
        }

        int changed = 0;
        for (BotPlayer bot : collectBots(ctx)) {
            // 👈 getName() → name()
            if (botName == null || botName.equalsIgnoreCase("all")
                    || bot.getGameProfile().name().equalsIgnoreCase(botName)) {
                bot.setMode(mode);
                changed++;
            }
        }

        if (changed == 0) {
            ctx.getSource().sendFailure(Component.literal("§cKhông tìm thấy bot: " + botName));
            return 0;
        }
        final int c = changed;
        final String target = botName == null ? "tất cả" : botName;
        ctx.getSource().sendSuccess(() -> Component.literal(
                "§aĐổi mode §e" + mode.name().toLowerCase() + " §acho §e" + target
                        + " §a(" + c + " bot)"), false);
        return changed;
    }

    private static List<BotPlayer> collectBots(CommandContext<CommandSourceStack> ctx) {
        List<BotPlayer> bots = new ArrayList<>();
        for (ServerPlayer p : ctx.getSource().getServer().getPlayerList().getPlayers()) {
            if (p instanceof BotPlayer bot) bots.add(bot);
        }
        return bots;
    }

    private static CompletableFuture<Suggestions> suggestModes(
            CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
        String rem = builder.getRemaining().toLowerCase();
        for (BotPlayer.Mode m : BotPlayer.Mode.values()) {
            String n = m.name().toLowerCase();
            if (n.startsWith(rem)) builder.suggest(n);
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> suggestBotNames(
            CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) {
        String rem = builder.getRemaining().toLowerCase();
        if ("all".startsWith(rem)) builder.suggest("all");
        for (ServerPlayer p : ctx.getSource().getServer().getPlayerList().getPlayers()) {
            if (p instanceof BotPlayer) {
                // 👈 getName() → name()
                String n = p.getGameProfile().name();
                if (n.toLowerCase().startsWith(rem)) builder.suggest(n);
            }
        }
        return builder.buildFuture();
    }
}