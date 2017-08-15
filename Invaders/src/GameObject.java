import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean isAlive = true;
	Rectangle collisionBox;

	boolean isAlien = false;
	boolean isProjectile = false;
	boolean isRocket = false;

	GameObject(int gX, int gY, int gWidth, int gHeight) {
		x = gX;
		y = gY;
		width = gWidth;
		height = gHeight;
		collisionBox = new Rectangle(x, y, width, height);
	}

	public boolean scoreHit(GameObject o) {
		return false;
	}

	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isProjectile() {
		return (isAlive() && isProjectile);
	}
	public boolean isAlien() {
		return (isAlive() && isAlien);
	}
	public boolean isRocket() {
		return (isAlive() && isRocket);
	}


	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics graphics) {
	}
}
