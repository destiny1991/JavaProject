package edu.buaa.server;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import edu.buaa.servlet.Servlet;

public class WebApp {
	private static ServletContext context;
	static {
		try {
			//获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//获取解析器
			SAXParser sax = factory.newSAXParser();
			//指定xml + 处理器
			WebHandler web = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("web.xml"), web);
			
			//将list转成Map
			context = new ServletContext();
			
			Map<String, String> servlet = context.getServlet();
			for (Entity e : web.getEntityList()) {
				servlet.put(e.getName(), e.getClz());
			}
			
			Map<String, String> mapping = context.getMapping();
			for (Mapping m : web.getMappingList()) {
				for (String url : m.getUlrPattern()) {
					mapping.put(url, m.getName());
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if(null ==url || url.equals("")) return null;
		
		//根据字符串（完整路径）创建对象
		String name = context.getServlet().get(context.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();		//确保空构造存在
	}
}
