package tanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class tanke {
	// 移动速度 每次移动的距离
	private static final int X_SPEED = 5;
	private static final int Y_SPEED = 5;
	private static final int TANK_R = 25; // 坦克的半径

	// tanke的坐标(初始)
	private int tank_x = 0;
	private int tank_y = 0;
	// 初始的方向
	private Direction direction = Direction.Up;// tanke的方向

	private TankFrame client = null;
	private boolean isMoving = false;

	// 方向的枚举
	public enum Direction {
		Righr, Left, Up, Down
	}

	// tanke的显示方法
	public void draw(Graphics g) {
		// 获取上下文的颜色
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		// xy 中间点的坐标 后面为左右半径
		g.fillOval(tank_x, tank_y, TANK_R * 2, TANK_R * 2);
		g.setColor(c);

		int x1, x2, y1, y2;
		x1 = x2 = tank_x + 25; // 修正炮管的位置
		y1 = y2 = tank_y + 25; // 修正炮管的位置
		switch (direction) {
		case Up:
			y1 -= TANK_R;
			y2 -= TANK_R * 2;
			break;
		case Down:
			y1 += TANK_R;
			y2 += TANK_R * 2;
			break;
		case Left:
			x1 -= TANK_R;
			x2 -= TANK_R * 2;
			break;
		case Righr:
			x1 += TANK_R;
			x2 += TANK_R * 2;
			break;
		}
		// x1,y1 起始位置 x2,y2终止位置
		g.drawLine(x1, y1, x2, y2);
		g.setColor(c);
		// 坦克移动的方法
		move();
	}

	// tanke的按键方法
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			direction = Direction.Up;
			isMoving = true;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.Down;
			isMoving = true;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.Left;
			isMoving = true;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.Righr;
			isMoving = true;
			break;
		case KeyEvent.VK_CONTROL:
			// 添加一个子弹进去
			fire();
			break;
		}
	}

	// 坦克移动方法
	private void move() {
		// 查看锁状态
		if (!isMoving)
			return; // 如果isMoving为false,则结束该方法
		switch (direction) {
		case Up:
			tank_y -= Y_SPEED;
			break;
		case Down:
			tank_y += Y_SPEED;
			break;
		case Left:
			tank_x -= X_SPEED;
			break;
		case Righr:
			tank_x += X_SPEED;
			break;
		}
		if (tank_x < 10)
			tank_x = 10;
		if (tank_y < 30)
			tank_y = 30;
		if (tank_x >= TankFrame.FRAME_W - TANK_R * 2) {
			tank_x = TankFrame.FRAME_W - TANK_R * 2;
		}
		if (tank_y >= TankFrame.FRAME_H - TANK_R * 2) {
			tank_y = TankFrame.FRAME_H - TANK_R * 2;
		}

	}

	// 构建一个子弹模型
	public void fire() {
		Cannonball c = new Cannonball(tank_x + 20, tank_y + 20, direction,
				client);
		client.addCannonball(c);
	}

	// 构造方法
	public tanke(int x, int y, TankFrame client) {
		this.tank_x = x;
		this.tank_y = y;
		this.client = client;
	}

	// 按键松开 (按键松开坦克移动)
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			// 释放锁
			isMoving = false;
			break;
		}
	}

}
