package edu.buaa.server;

import java.io.IOException;
import java.net.ServerSocket;
import edu.buaa.util.CloseUti;

/**
 * 创建服务器，并启动
 * @author destiny
 *
 */
public class Server {
	private ServerSocket server;
	public static final String CRLF = "\r\n";
	public static final String BLANK = " ";
	
	private boolean isShutDown = false;
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
	
	/**
	 * 默认端口启动方法
	 */
	public void start() {
		start(8888);
	}
	
	/**
	 * 指定端口启动方法
	 * @param port
	 */
	public void start(int port) {
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}
	
	/**
	 * 接收客户端
	 */
	private void receive() {
		try {
			while(!isShutDown) {
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			stop();
		}
	}
	
	/**
	 * 停止服务
	 */
	public void stop() {
		isShutDown = true;
		CloseUti.closeSocket(server);
	}

}
