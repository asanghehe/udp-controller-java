package com.guoyuan.test.components;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import com.guoyuan.Const;

public class ThreadReceiver extends Thread{
	
	private boolean running = true;

	DatagramSocket ds = null;
	
	@Override
	public void run() {

		System.out.println("Slave 接收开始......");
		
		try {

			ds = new DatagramSocket(Const.COMMAND_PORT);

			//静态的buff
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			
			while (running) {
				
				ds.receive(dp);
				
				String ip = dp.getAddress().getHostAddress();
				
				int port = dp.getPort();
				
				String text = new String(dp.getData(), 0, dp.getLength());
				
				System.out.println(ip + ":" + port + "===>command: " + text);
			}

			ds.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void interrupt() {
		running = false;
		
		if(ds != null){
			ds.disconnect();
			ds.close();
		}
	}
}
