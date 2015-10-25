package edu.buaa.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Request {
	//请求方式
	private String method;
	//请求资源
	private String url;
	//请求参数
	private Map<String, List<String>> parameterMapValues;
	
	//内部
	public static final String CRLF = "\r\n";
	private String requestInfo;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Request() {
		method = "";
		url = "";
		parameterMapValues = new HashMap<String, List<String>>();
		requestInfo = "";
	}
	
	public Request(InputStream is) {
		this();
		try {
			byte[] data = new byte[65535];
			int len;
			len = is.read(data);
			requestInfo = new String(data, 0, len);
		} catch (IOException e) {
			return;
		}
		
		//分析请求信息
		parseRequestInfo();
	}
	
	/**
	 * 分析请求信息
	 */
	private void parseRequestInfo() {
		if(null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}
		
	   /**
	    * 从信息的首行分解出：请求方式 请求路径 请求参数(get可能存在)
	    * 
	    * 如果是post方式，请求参数可能在最后的正文中
	    */
		String paraString = ""; //接收请求参数
		
		//1.获取请求方式
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/");
		this.method = firstLine.substring(0, idx).trim();
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
		if(this.method.equalsIgnoreCase("post")) {
			this.url = urlStr;
			paraString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		} else if(this.method.equalsIgnoreCase("get")) {
			if(urlStr.contains("?")) {
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paraString = urlArray[1];
			} else {
				this.url = urlStr;
			}
		}
		
		//2.将请求参数封装到Map中
		//不存在请求参数, return
		if (paraString.equals("")) {
			return;
		}
		parseParams(paraString);
	}
	
	private void parseParams(String paraString) {
		//分割，将字符串转成数组
		StringTokenizer token = new StringTokenizer(paraString, "&");
		while (token.hasMoreTokens()) {
			String keyValue = token.nextToken();
			String[] keyValues = keyValue.split("=");
			if(keyValues.length == 1) {
				keyValues = Arrays.copyOf(keyValues, 2);
				keyValues[1] = null;
			}
			
			String key = keyValues[0].trim();
			String value = null == keyValues[1]? null: decode(keyValues[1].trim(), "GBK");
			//转换成Map
			if(!parameterMapValues.containsKey(key)) {
				parameterMapValues.put(key, new ArrayList<String>());
			}
			List<String> values = parameterMapValues.get(key);
			values.add(value);
		}
		
	}
	
	/**
	 * 解决中文
	 * @param value
	 * @param code
	 * @return
	 */
	private String decode(String value, String code) {
		try {
			return java.net.URLDecoder.decode(value, code);
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	//获取多值
	public String[] getParametersValues(String name) {
		List<String> values = null;
		if((values = parameterMapValues.get(name)) == null) {
			return null;
		}
		
		//底层一般返回数组，而不是List
		return values.toArray(new String[0]);
	}
	
	/**
	 * 根据页面的name 获取对应的单个值
	 */
	public String getParameter(String name) {
		String[] values = getParametersValues(name);
		if(null == values) return null;
		return values[0];
	}
}
