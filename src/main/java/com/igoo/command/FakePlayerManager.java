package com.igoo.command;

import com.igoo.entity.BotPlayer;
import com.igoo.network.FakeClientConnection;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Random;
import java.util.UUID;

public class FakePlayerManager {

    private static final Random RANDOM = new Random();
    private static final String NAME_PREFIX = "Bot_";

    public static int spawnFakePlayers(MinecraftServer server, ServerPlayer spawner, int count) {
        if (!server.isPublished()) return -1;

        PlayerList playerList = server.getPlayerList();
        ServerLevel level = server.overworld();
        int spawned = 0;

        for (int i = 0; i < count; i++) {
            String name = generateUniqueName(playerList);
            UUID uuid = UUID.nameUUIDFromBytes(name.getBytes());
            GameProfile profile = new GameProfile(uuid, name);

            BotPlayer bot = new BotPlayer(level, profile);
            bot.setOwner(spawner);   // 👈 bot biết ai là chủ

            // Give vật phẩm để bot có thể xây / đánh
            bot.getInventory().add(new ItemStack(Items.STONE, 64));
            bot.getInventory().add(new ItemStack(Items.DIRT, 64));
            bot.getInventory().add(new ItemStack(Items.COBBLESTONE, 64));
            bot.getInventory().add(new ItemStack(Items.DIAMOND_SWORD));
            bot.getInventory().add(new ItemStack(Items.IRON_PICKAXE));

            FakeClientConnection connection = new FakeClientConnection();
            try {
                CommonListenerCookie cookie = CommonListenerCookie.createInitial(profile, false);
                playerList.placeNewPlayer(connection, bot, cookie);
                spawned++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return spawned;
    }

    private static String generateUniqueName(PlayerList playerList) {
        String name;
        do { name = NAME_PREFIX + RANDOM.nextInt(10000); }
        while (playerList.getPlayerByName(name) != null);
        return name;
    }
}