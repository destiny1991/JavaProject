package edu.buaa.server;

import java.util.HashMap;
import java.util.Map;

public class ServletContext {
	//为每个servlet取一个别名,
	//如：login --> 包名.类名(完整路径)
	private Map<String, String> servlet;
	//url --> login
	private Map<String, String> mapping;
	
	public ServletContext() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
	}
	
	public Map<String, String> getServlet() {
		return servlet;
	}
	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}
	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
}
