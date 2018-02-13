package com.guoyuan;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 终端 定时汇报 接受处理
 * @author asang
 *
 */
public class ReportReceiver {
	
	private static final int PORT = 6890;
	
	public void handle(){
		

		System.out.println("接受开始......");

		DatagramSocket ds = null;

		try {
			
			//InetAddress host = InetAddress.getByName(HOSTNAME);
			
			ds = new DatagramSocket(PORT);

			//静态的buff
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			
			while (true) {
				
				ds.receive(dp);
				
				String ip = dp.getAddress().getHostAddress();
				
				int port = dp.getPort();
				
				String text = new String(dp.getData(), 0, dp.getLength());
				
				if (text.equals("exit")) {
					System.out.println(ip + "退出会话......");
					break;
				}
				System.out.println(ip + ":" + port + "===>me " + text);
			}

			ds.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
