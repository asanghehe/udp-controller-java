package com.guoyuan.test;

import java.io.IOException;

import com.guoyuan.test.components.ThreadReceiver;
import com.guoyuan.test.components.ThreadSender;

/**
 * 终端 UDP 模拟器， 方便服务端的开发。开发时 启动模拟器 即可模拟处理 服务器与客户端的交互
 * @author asang
 *
 */
public class SlaveSimulator {
	
	private static Thread receiverT;
	private static ThreadSender senderT;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		sendSimulateReport();
		receiveCommand();
		
		//使用输入终端来结束子线程
		System.in.read();
		
		receiverT.interrupt();
		senderT.exit();
		
		/**
		//等待两个线程的结束
		try{
			while(receiverT.isAlive() || senderT.isAlive()){
				
				Thread.sleep(1000);
			}
		}catch (Exception e) {
			
		}
		**/
	}
	
	/**
	 * 接收 处理 服务端发来的命令
	 */
	public static void receiveCommand(){
		receiverT = new ThreadReceiver();
		receiverT.start();
	}
	
	/**
	 * 持续不断的 定时的UDP 汇报到服务端
	 */
	public static void sendSimulateReport(){
		senderT = new ThreadSender();
		senderT.start();
	}
}
