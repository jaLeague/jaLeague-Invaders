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
	
	GameObject (int gX, int gY, int gWidth, int gHeight) {
		x=gX;
		y=gY;
		width=gWidth;
		height=gHeight;
		collisionBox = new Rectangle(x, y, width, height);
	}
	
	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
	void draw(Graphics graphics) {
	}
}
