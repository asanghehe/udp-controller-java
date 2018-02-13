package com.guoyuan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CommandSender {
	
	public void handle(){

		// 传入0表示让操作系统分配一个端口号
		DatagramSocket socket = null;
		
		try{
			socket = new DatagramSocket(0);
			
			socket.setSoTimeout(10000);
			
			InetAddress host = InetAddress.getByName(Const.BROADCAST_ADDRESS);
			
			
			//不同的包（其实可以发到不同的主机上的）
			DatagramPacket reqStop = new DatagramPacket("stop".getBytes(), "stop".getBytes().length, host, Const.COMMAND_PORT);

			//设置为高电位（开关开）
			DatagramPacket reqHigh = new DatagramPacket("high".getBytes(), "high".getBytes().length, host, Const.COMMAND_PORT);
			//设置为低电位（开关关）
			DatagramPacket reqLow = new DatagramPacket("low".getBytes(), "low".getBytes().length, host, Const.COMMAND_PORT);
			
			// 为接受的数据包创建空间
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			
			//循环开/关
			for(int i = 0; i<20; i++){
			
				if(i % 2 == 0){
					socket.send(reqLow);
				}else{
					socket.send(reqHigh);
				}
				Thread.sleep(1000);
				//socket.receive(response);
			}
			
			socket.send(reqStop);
			//String result = new String(response.getData(), 0, response.getLength(), "ASCII");
			//System.out.println(result);
			
			socket.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
