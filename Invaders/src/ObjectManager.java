
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

			objects.get(i).update();
		}
		
		purgeObjects();	
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
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
				if(objects.get(i).collisionBox.intersects(objects.get(j).collisionBox)){	
					//not all collisions score points
					if (objects.get(i).scoreHit(objects.get(j))) {
						score++;
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
