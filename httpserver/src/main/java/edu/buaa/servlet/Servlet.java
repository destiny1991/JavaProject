package edu.buaa.servlet;

import edu.buaa.server.Request;
import edu.buaa.server.Response;

/**
 * 抽象为一个父类
 * @author destiny
 *
 */
public abstract class Servlet {
	public Servlet() {
	}
	
	public void service(Request req, Response rep) throws Exception {
		this.doGet(req, rep);
		this.doPost(req, rep);
	}
	
	public abstract void doGet(Request req, Response rep) throws Exception;
	public abstract void doPost(Request req, Response rep) throws Exception;
}
