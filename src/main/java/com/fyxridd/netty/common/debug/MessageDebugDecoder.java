package com.fyxridd.netty.common.debug;

import com.fyxridd.netty.common.util.Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;

public class MessageDebugDecoder implements ChannelInboundHandler{
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Util.log(">handlerAdded");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Util.log(">handlerRemoved");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelRegistered");
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelUnregistered");
        ctx.fireChannelUnregistered();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelActive");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelInactive");
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Util.log(">channelRead: "+((ByteBuf)msg).readableBytes());
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelReadComplete");
        ctx.fireChannelReadComplete();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Util.log(">userEventTriggered");
        ctx.fireUserEventTriggered(evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        Util.log(">channelWritabilityChanged");
        ctx.fireChannelWritabilityChanged();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Util.log(">exceptionCaught");
        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }
}
