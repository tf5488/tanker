package tanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Cannonball {
	private int x = 0;
	private int y = 0;
	private tanke.Direction direction = null;
	private TankFrame client = null;
	private static final int X_SPEED = 10;
	private static final int Y_SPEED = 10;

	// ���췽�� �ṩһ���洢���ݵĶ���
	public Cannonball(int x, int y, tanke.Direction dir, TankFrame t) {
		this.x = x;
		this.y = y;
		this.direction = dir;
		this.client = t;
	}

	// �ӵ��Ļ��Ʒ���
	public void draw(Graphics g) {
		collide();
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);
		// �ӵ����ƶ�����
		move();
	}

	// �ӵ����ƶ�����
	private void move() {
		switch (direction) {
		case Up:
			y -= Y_SPEED;
			break;
		case Down:
			y += Y_SPEED;
			break;
		case Left:
			x -= X_SPEED;
			break;
		case Righr:
			x += X_SPEED;
			break;
		}
		if (x < 0 || y < 0 || x > client.FRAME_W || y > client.FRAME_H) {
			client.deleteCannonball(this);
		}
	}

	// �ӵ����湹��һ������
	public Rectangle getRectangle() {
		return new Rectangle(x, y, 10, 10);
	}

	// ������ײ�Ա�
	public void collide() {
		for (RobotTank rt : client.robots) {
			if (this.getRectangle().intersects(rt.getRectangle())) {
				client.deleteCannonball(this);
				client.robots.remove(rt);
			}
		}

	}

}
