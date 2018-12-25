package tanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class RobotTank {
	private static final int X_SPEED = 5;
	private static final int Y_SPEED = 5;
	private static final int TANK_R = 25; // ̹�˵İ뾶

	private int tank_x = 0;
	private int tank_y = 0;
	private tanke.Direction direction = tanke.Direction.Down;

	private TankFrame client = null;

	// ������̹�˵Ĺ��췽��
	public RobotTank(int x, int y, TankFrame client) {
		this.tank_x = x;
		this.tank_y = y;
		this.client = client;
	}

	// tanke����ʾ����
	public void draw(Graphics g) {
		// ��ȡ�����ĵ���ɫ
		Color c = g.getColor();
		g.setColor(Color.blue);
		g.fillOval(tank_x, tank_y, TANK_R * 2, TANK_R * 2);

		int x1, x2, y1, y2;
		x1 = x2 = tank_x + 25; // �����ڹܵ�λ��
		y1 = y2 = tank_y + 25; // �����ڹܵ�λ��
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
		// x1,y1 ��ʼλ�� x2,y2��ֹλ��
		g.drawLine(x1, y1, x2, y2);
		g.setColor(c);
	}

	// ̹�����湹��һ���ľ���
	public Rectangle getRectangle() {
		return new Rectangle(tank_x, tank_y, TANK_R * 2, TANK_R * 2);
	}
}
