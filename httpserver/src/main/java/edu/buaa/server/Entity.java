package edu.buaa.server;
/*
 处理：
 <servlet>
		<servlet-name>login</servlet-name>
		<servlet-class>edu.buaa.server.LoginServlet</servlet-class>
 </servlet>
 */
public class Entity {
	private String name;
	private String clz;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClz() {
		return clz;
	}
	public void setClz(String clz) {
		this.clz = clz;
	}
	
	@Override
	public String toString() {
		return name + "+" + clz;
	}
}
