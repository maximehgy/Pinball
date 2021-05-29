package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;

public class Teleporteur extends Obstacle{

	public Point p1;
	public Point p2;
	public Bande b1;
	
	//CONSTRUCTEUR
	public Teleporteur( int x1, int y1, int x2, int y2,String niveau,String num) {
		super(x1, y1);
		// TODO Auto-generated constructor stub
		this.p1 = new Point (x1, y1);
		this.p2 = new Point (x2, y2);
		b1 = new Bande_Changescene (p1, p2,niveau,num);
	}
	//invisible
	@Override
	public void draw(GraphicsContext gc, Group g) {
		//gc.moveTo(p1.x,p1.y);
		//gc.lineTo(p2.x,p2.y);
	}
	
	//retourne la bande de téléportation
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1};
		return listebandes;
	}
}