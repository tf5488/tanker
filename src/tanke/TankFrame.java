package tanke;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

class TankFrame extends Frame {
	/**
     *
     */
	private static final long serialVersionUID = 1L;
	// 窗口的宽高
	public final static int FRAME_H = 600;
	public final static int FRAME_W = 800;
	// 一个集合用于接受多个子弹
	private ArrayList<Cannonball> cannonballs = new ArrayList<Cannonball>();
	// 用于接受过个机器人对象
	public ArrayList<RobotTank> robots = new ArrayList<RobotTank>();
	// 坦克模型
	tanke myTank = new tanke(200, 200, this);

	// 窗口的构造方法
	public TankFrame(String s) {
		super(s);
	}

	// 窗口的初始化
	public void launchFrame() {
		setLocation(100, 100); // 窗口的位置
		setSize(FRAME_W, FRAME_H); // 窗口的大小
		setVisible(true); // 窗口的可见属性
		// 窗口关闭事件
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// 加载键盘监听
		this.addKeyListener(new KeyMonitor());
		// 加载刷新线程
		new Thread(new PaintThread()).start();

		// 添加一个机器人对象
		robots.add(new RobotTank(400, 300, this));
	}

	// 绘制界面的方法
	public void paint(Graphics g) {
		g.drawString("cannnonballs number:" + cannonballs.size(), 50, 50);
		// 绘制坦克
		myTank.draw(g);
		// 通过遍历 ,绘制所有子弹
		for (Cannonball c : cannonballs) {
			c.draw(g);
		}

		// 通过遍历 ,绘制所有子弹
		for (RobotTank robot : robots) {
			robot.draw(g);
		}
	}

	// 定时刷新
	private class PaintThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				// 刷新界面的办法
				repaint();
				try {
					// 50毫秒运行一次
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 键盘的监听
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// 当键盘被触发的时候获取一个事件,并执行以下语句
			myTank.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			// 当按键被释放时,执行以下语句
			myTank.keyReleased(e);
		}
	}

	// 发射的时候添加子弹到集合中
	public void addCannonball(Cannonball c) {
		cannonballs.add(c);
	}

	// 将超过边际的子弹去除集合
	public void deleteCannonball(Cannonball c) {
		cannonballs.remove(c);
	}

}
