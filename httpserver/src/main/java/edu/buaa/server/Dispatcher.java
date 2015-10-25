package edu.buaa.server;

import java.io.IOException;
import java.net.Socket;

import edu.buaa.servlet.Servlet;
import edu.buaa.util.CloseUti;


/**
 * 一个请求与一个响应就一个此对象
 * @author destiny
 *
 */
public class Dispatcher implements Runnable {
	private Socket client;
	private Request req;
	private Response rep;
	private int code = 200;
	
	public Dispatcher(Socket client) {
		this.client = client;
		try {
			this.req = new Request(client.getInputStream());
			this.rep = new Response(client.getOutputStream());
		} catch (IOException e) {
			code = 500;
			return;
		}
	}
	
	@Override
	public void run() {
		try {
			Servlet serv = WebApp.getServlet(req.getUrl());
			if(null == serv) {
				this.code = 404;    //找不到对应的处理
			} else {
				serv.service(req, rep);
			}
			rep.pushToClient(code);  //推送到客户端
		} catch (Exception e) {
			this.code = 500;
		}
		
		CloseUti.closeSocket(client);
	}
}
