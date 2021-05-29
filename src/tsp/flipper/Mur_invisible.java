package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Mur_invisible extends Obstacle{

	public Point p1;
	public Point p2;
	public Bande b1;
	
	//CONSTRUCTEUR
	public Mur_invisible( int x1, int y1, int x2, int y2) {
		super(x1, y1);
		// TODO Auto-generated constructor stub
		this.p1 = new Point (x1, y1);
		this.p2 = new Point (x2, y2);
		b1 = new Bande_Classique (p1, p2);		
	}
	//mur invisible donc on ne dessine rien
	@Override
	public void draw(GraphicsContext gc, Group g) {
		//gc.moveTo(p1.x,p1.y);
		//gc.lineTo(p2.x,p2.y);
	}
	
	//on retourne quand même une bande car on est un mur
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1};
		return listebandes;
	}
}