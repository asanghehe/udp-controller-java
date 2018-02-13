package com.guoyuan.test.components;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.guoyuan.Const;

public class ThreadSender extends Thread{
	
	private boolean running = true;
	
	private DatagramSocket socket;
	private DatagramPacket report;
	
	@Override
	public void run() {

		try{
			socket = new DatagramSocket(0);
			socket.setSoTimeout(3000);
			
			InetAddress host = InetAddress.getByName(Const.BROADCAST_ADDRESS);
			
			String msg = " this mac=32:D2:43:sd:xc:df:CC:54 IP=10.92.23.111";
			
			//定时汇报数据包
			report = new DatagramPacket(msg.getBytes(), msg.getBytes().length, host, Const.COMMAND_PORT);
			
			//每 10s 汇报一次
			while(running){
				
				System.out.println("sending msg : "+msg);
				socket.send(report);
				
				Thread.sleep(10000);
			}

			socket.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void exit(){
		this.running = false;
		
		try{
			socket.send(report);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
