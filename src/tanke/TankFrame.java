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
	// ���ڵĿ��
	public final static int FRAME_H = 600;
	public final static int FRAME_W = 800;
	// һ���������ڽ��ܶ���ӵ�
	private ArrayList<Cannonball> cannonballs = new ArrayList<Cannonball>();
	// ���ڽ��ܹ��������˶���
	public ArrayList<RobotTank> robots = new ArrayList<RobotTank>();
	// ̹��ģ��
	tanke myTank = new tanke(200, 200, this);

	// ���ڵĹ��췽��
	public TankFrame(String s) {
		super(s);
	}

	// ���ڵĳ�ʼ��
	public void launchFrame() {
		setLocation(100, 100); // ���ڵ�λ��
		setSize(FRAME_W, FRAME_H); // ���ڵĴ�С
		setVisible(true); // ���ڵĿɼ�����
		// ���ڹر��¼�
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// ���ؼ��̼���
		this.addKeyListener(new KeyMonitor());
		// ����ˢ���߳�
		new Thread(new PaintThread()).start();

		// ���һ�������˶���
		robots.add(new RobotTank(400, 300, this));
	}

	// ���ƽ���ķ���
	public void paint(Graphics g) {
		g.drawString("cannnonballs number:" + cannonballs.size(), 50, 50);
		// ����̹��
		myTank.draw(g);
		// ͨ������ ,���������ӵ�
		for (Cannonball c : cannonballs) {
			c.draw(g);
		}

		// ͨ������ ,���������ӵ�
		for (RobotTank robot : robots) {
			robot.draw(g);
		}
	}

	// ��ʱˢ��
	private class PaintThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				// ˢ�½���İ취
				repaint();
				try {
					// 50��������һ��
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// ���̵ļ���
	class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// �����̱�������ʱ���ȡһ���¼�,��ִ���������
			myTank.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			// ���������ͷ�ʱ,ִ���������
			myTank.keyReleased(e);
		}
	}

	// �����ʱ������ӵ���������
	public void addCannonball(Cannonball c) {
		cannonballs.add(c);
	}

	// �������߼ʵ��ӵ�ȥ������
	public void deleteCannonball(Cannonball c) {
		cannonballs.remove(c);
	}

}
