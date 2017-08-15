import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Alien extends GameObject {

	Random r = new Random();

	Alien(int aX, int aY, int aWidth, int aHeight) {
		super(aX, aY, aWidth, aHeight);
		isAlien = true;
	}
	public boolean isAlien() {
		return isAlive();
	}
	public boolean scoreHit(GameObject o) {
		if (isAlive() && (o.isProjectile || o.isRocket)) {
			setAlive(false);
			o.setAlive(false);
			return true;
		} else
			return false;
	}

	void update() {

		int xMove = r.nextInt(9);
		int xDir = r.nextInt(2);

		if (xDir == 0) {
			x = x + xMove;
		} else {
			x = x - xMove;
		}
		y++;
		super.update(); // collision box
	}

	void draw(Graphics graphics) {
		if (ObjectManager.hasImages) {

			graphics.drawImage(ObjectManager.alienImg, x, y - height / 2, width, height, null);
		} else {

			graphics.setColor(Color.YELLOW);
			graphics.fillRect(x, y, width, height);
		}
	}
}
