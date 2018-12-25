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

	// 构造方法 提供一个存储数据的对象
	public Cannonball(int x, int y, tanke.Direction dir, TankFrame t) {
		this.x = x;
		this.y = y;
		this.direction = dir;
		this.client = t;
	}

	// 子弹的绘制方法
	public void draw(Graphics g) {
		collide();
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, 10, 10);
		g.setColor(c);
		// 子弹的移动方法
		move();
	}

	// 子弹的移动方法
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

	// 子弹外面构建一个矩形
	public Rectangle getRectangle() {
		return new Rectangle(x, y, 10, 10);
	}

	// 进行碰撞对比
	public void collide() {
		for (RobotTank rt : client.robots) {
			if (this.getRectangle().intersects(rt.getRectangle())) {
				client.deleteCannonball(this);
				client.robots.remove(rt);
			}
		}

	}

}
