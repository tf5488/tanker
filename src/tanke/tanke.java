package tanke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class tanke {
	// �ƶ��ٶ� ÿ���ƶ��ľ���
	private static final int X_SPEED = 5;
	private static final int Y_SPEED = 5;
	private static final int TANK_R = 25; // ̹�˵İ뾶

	// tanke������(��ʼ)
	private int tank_x = 0;
	private int tank_y = 0;
	// ��ʼ�ķ���
	private Direction direction = Direction.Up;// tanke�ķ���

	private TankFrame client = null;
	private boolean isMoving = false;

	// �����ö��
	public enum Direction {
		Righr, Left, Up, Down
	}

	// tanke����ʾ����
	public void draw(Graphics g) {
		// ��ȡ�����ĵ���ɫ
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		// xy �м������� ����Ϊ���Ұ뾶
		g.fillOval(tank_x, tank_y, TANK_R * 2, TANK_R * 2);
		g.setColor(c);

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
		// ̹���ƶ��ķ���
		move();
	}

	// tanke�İ�������
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
			// ���һ���ӵ���ȥ
			fire();
			break;
		}
	}

	// ̹���ƶ�����
	private void move() {
		// �鿴��״̬
		if (!isMoving)
			return; // ���isMovingΪfalse,������÷���
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

	// ����һ���ӵ�ģ��
	public void fire() {
		Cannonball c = new Cannonball(tank_x + 20, tank_y + 20, direction,
				client);
		client.addCannonball(c);
	}

	// ���췽��
	public tanke(int x, int y, TankFrame client) {
		this.tank_x = x;
		this.tank_y = y;
		this.client = client;
	}

	// �����ɿ� (�����ɿ�̹���ƶ�)
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			// �ͷ���
			isMoving = false;
			break;
		}
	}

}
