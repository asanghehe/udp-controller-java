package com.guoyuan.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler4 extends SimpleChannelInboundHandler<String>{

    //接受服务端发来的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server response ： "+msg);
    }

    //与服务器建立连接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //给服务器发消息
        ctx.channel().writeAndFlush("i am client !");

        System.out.println("channelActive");
    }

    //与服务器断开连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //关闭管道
        ctx.channel().close();
        //打印异常信息
        cause.printStackTrace();
    }
}
