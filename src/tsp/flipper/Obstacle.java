package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public abstract class Obstacle {

	private int x;
	private int y;
	
	//CONSTRUCTEUR
	public Obstacle (int x, int y) {
		this.x = x;
		this.y = y;
	} 

	//GETTER
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	//SETTER
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	//AUTRES METHODES
	
	//permet d'afficher l'obstacle
	public void draw(GraphicsContext gc, Group g) {
		
	}

	//renvoie les bandes associées à l'obstacle
	public Bande[] getBandes() {
		return null;
		
	} 
	
}
