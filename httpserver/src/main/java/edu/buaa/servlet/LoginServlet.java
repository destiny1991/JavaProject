package edu.buaa.servlet;

import edu.buaa.server.Request;
import edu.buaa.server.Response;

public class LoginServlet extends Servlet {

	@Override
	public void doGet(Request req, Response rep) throws Exception {
		String name = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		if(Login(name, pwd)) {
			rep.println("Login success!");
		} else {
			rep.println("Login fail!");
		}
	}
	
	public boolean Login(String name, String pwd) {
		return name.equals("chen") && pwd.equals("1234");
	} 

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		
	}

}
