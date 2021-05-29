package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Champignon extends Obstacle {

	public Image image;
	public Bande b1;
	public Bande b2;
	public Bande b3;
	public Bande b4;
	public Bande b5;
	public Bande b6;
	public int x;
	public int y;
	public Point p1;
	public Point p2;
	public Point p3;
	public Point p4;
	public Point p5;
	public Point p6;

	//CONSTRUCTEUR
	//crée des arrêtes tout autour des coordonnées du champignon pour modéliser sa hitbox
	public Champignon(String path, int width, int height, int x, int y, double boost, double angle) {
		super(x, y);
		image = new Image(path, width, height, true, false);
		this.p1 = new Point (x, y-40);
		this.p2 = new Point (x-40, y-20);
		this.p3 = new Point (x-40, y+20 );
		this.p4 = new Point (x, y+40);
		this.p5 = new Point (x+40, y+20);
		this.p6 = new Point (x+40, y-20 );	
		this.b1 = new Bande_Active (p2,p1);
		this.b2 = new Bande_Active (p3,p2);
		this.b3 = new Bande_Active (p4,p3);	
		this.b4 = new Bande_Active (p5,p4);
		this.b5 = new Bande_Active (p6,p5);
		this.b6 = new Bande_Active (p1,p6);	
	}
	//dessine l'image du champignon
	@Override
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image, p1.x-40, p1.y+20);
	}
	
	//retourne la liste de bandes associées à cet obstacle
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1,this.b2,this.b3,this.b4,this.b5,this.b6};
		return listebandes;
	}
}