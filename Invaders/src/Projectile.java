import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {

	int projSpeed = 10;

	Projectile(int pX, int pY, int pHeight, int pWidth) {
		super(pX, pY, pHeight, pWidth);
	}

	void update() {
		super.update();
		y -= projSpeed;
		if (y < 0) {
			isAlive = false;
		}
	}

	void draw(Graphics graphics) {
		if (ObjectManager.hasImages) {
			graphics.drawImage(ObjectManager.bulletImg, x, y - height / 2, width, height, null);
		} else {

			graphics.setColor(Color.BLUE);
			graphics.fillRect(x, y, width, height);
		}
	}
}
