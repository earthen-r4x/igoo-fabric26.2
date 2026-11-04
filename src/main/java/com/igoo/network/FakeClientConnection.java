package com.igoo.network;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.PacketListener;
import net.minecraft.network.ProtocolInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;

public class FakeClientConnection extends Connection {

    public FakeClientConnection() {
        super(PacketFlow.SERVERBOUND);
    }

    @Override public void channelActive(ChannelHandlerContext ctx) { }
    @Override public void channelInactive(ChannelHandlerContext ctx) { }
    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { }
    @Override protected void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) { }
    @Override public void send(Packet<?> packet) { }
    @Override public void send(Packet<?> packet, ChannelFutureListener listener) { }
    @Override public void send(Packet<?> packet, ChannelFutureListener listener, boolean flush) { }
    @Override public void flushChannel() { }
    @Override public void disconnect(Component reason) { }
    @Override public void disconnect(DisconnectionDetails details) { }
    @Override public void setListenerForServerboundHandshake(PacketListener packetListener) { }
    @Override public <T extends PacketListener> void setupInboundProtocol(ProtocolInfo<T> protocol, T packetListener) { }
    @Override public void setupOutboundProtocol(ProtocolInfo<?> protocol) { }
    @Override public void setReadOnly() { }
    @Override public void setupCompression(int threshold, boolean validateDecompressed) { }
    @Override public boolean isConnected() { return true; }
    @Override public boolean isConnecting() { return false; }
    @Override public boolean isMemoryConnection() { return true; }
    @Override public void tick() { }
}