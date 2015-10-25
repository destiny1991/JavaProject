package edu.buaa.servlet;

import edu.buaa.server.Request;
import edu.buaa.server.Response;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request req, Response rep) throws Exception {
		rep.println("<html><head><title>HTTP响应示例</title></head><body>");
		rep.println("欢迎:").println(req.getParameter("uname")).println("回来!");	
		rep.println("</body></html>");
	}

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		
	}

}
