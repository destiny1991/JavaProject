package edu.buaa.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUti {
	public static void closeAll(Closeable... io) {
		for(Closeable tmp: io) {
			try{
				if(null != tmp) {
					tmp.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void closeSocket(Socket client) {
		try {
			if(null != client) {
				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSocket(ServerSocket server) {
		try {
			if(null != server) {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
