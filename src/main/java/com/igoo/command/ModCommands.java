package com.igoo.command;

import com.igoo.Igoo;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ModCommands {

    private static final String ROOT = "igoo";

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) ->
                registerAll(dispatcher, buildContext));
        Igoo.LOGGER.info("Registered Igoo commands");
    }

    private static void registerAll(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext buildContext) {

        LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal(ROOT)
                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS));

        root.then(EnchantCommand.build(buildContext));
        root.then(HealCommand.build());
        root.then(FakePlayerCommand.build());

        dispatcher.register(root);
    }
}