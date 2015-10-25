package edu.buaa.PlaneGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import edu.buaa.util.Constant;
import edu.buaa.util.GameUtil;
import edu.buaa.util.MyFrame;

public class PlaneGameFrame extends MyFrame {
	private static final long serialVersionUID = 1L;
	
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane plane = new Plane("images/plane.jpg", 100, 100);
	ArrayList<Bullet> bulletList = new ArrayList<>();
	
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		plane.draw(g);
		
		
		for(int i=0; i<bulletList.size(); i++) {
			Bullet b = bulletList.get(i);
			b.draw(g);
			
			//碰撞检测
			boolean peng = plane.getRect().intersects(b.getRect());
			if(peng) {
				plane.live = false;
				if(plane.endTime == null)
					plane.endTime = new Date();
			}
		}
	}
	
	public void launchFrame() {
		//增加键盘监听
		addKeyListener(new KeyMonitor());
		super.launchFrame();
		
		//生成一堆子弹
		for(int i=0; i<Constant.BULLET_NUM; i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		
		plane.startTime = new Date();
	}
	
	//定义为内部类，可以方便的使用外部类的普通属性
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}
	
	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}
}
