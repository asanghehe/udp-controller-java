package com.guoyuan.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler4 extends SimpleChannelInboundHandler<String> {

    //读取客户端发送的数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client response :"+msg);
        ctx.channel().writeAndFlush("i am server !");

//        ctx.writeAndFlush("i am server !").addListener(ChannelFutureListener.CLOSE);
    }

    //新客户端接入
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    //客户端断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭通道
        ctx.channel().close();
        //打印异常
        cause.printStackTrace();
    }
}
