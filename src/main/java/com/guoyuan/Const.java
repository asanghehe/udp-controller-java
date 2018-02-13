package com.guoyuan;

public class Const {
	
	/**
	 * Slave 的汇报，服务端的监听端口
	 */
	public static final int REPORT_PORT = 6890;
	
	/**
	 * 服务端的命令发送，Slave 的命令接收端口
	 */
	public static final int COMMAND_PORT = 6666;
	
	/**
	 * 广播地址段，客户端上线、定时汇报会发广播消息，服务端的批量控制命令也发送广播消息
	 */
	public static final String BROADCAST_ADDRESS = "192.168.2.255";
}
