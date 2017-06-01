
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;




public class ObjectManager {
	ArrayList<GameObject> objects;
	public  static BufferedImage alienImg;
	public  static BufferedImage rocketImg;
	public  static BufferedImage bulletImg;
	public static boolean hasImages = false;
	
	private int score = 0;
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	
	public ObjectManager() {
		objects = new ArrayList<GameObject>();

			try {
				alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
				rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
				bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
				hasImages = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				hasImages = false;
			}

		}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
		}
		
		purgeObjects();	
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void manageEnemies(){
		if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
			addObject(new Alien(new Random().nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
			enemyTimer = System.currentTimeMillis();
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);
				
				if(o1.collisionBox.intersects(o2.collisionBox)){
					if((o1 instanceof Alien && o2 instanceof Projectile) ||
					   (o2 instanceof Alien && o1 instanceof Projectile)){
						score++;
						o1.isAlive = false;
						o2.isAlive = false;
					}
					else if((o1 instanceof Alien && o2 instanceof Rocketship) ||
							(o2 instanceof Alien && o1 instanceof Rocketship)){
						o1.isAlive = false;
						o2.isAlive = false;
					}
	
				}
			}
		}
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public void reset(){
		objects.clear();
		score = 0;
	}
}
