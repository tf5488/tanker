package tanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RobotTank {
	private static final int X_SPEED = 5;
	private static final int Y_SPEED = 5;
	private static final int TANK_R = 25; // 坦克的半径

	private int tank_x = 0;
	private int tank_y = 0;
	private tanke.Direction direction = tanke.Direction.Down;

	private TankFrame client = null;

	// 机器人坦克的构造方法
	public RobotTank(int x, int y, TankFrame client) {
		this.tank_x = x;
		this.tank_y = y;
		this.client = client;
	}

	// tanke的显示方法
	public void draw(Graphics g) {
		// 获取上下文的颜色
		Color c = g.getColor();
		g.setColor(Color.blue);
		g.fillOval(tank_x, tank_y, TANK_R * 2, TANK_R * 2);

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
	}

	// 坦克外面构建一个的矩形
	public Rectangle getRectangle() {
		return new Rectangle(tank_x, tank_y, TANK_R * 2, TANK_R * 2);
	}
}
