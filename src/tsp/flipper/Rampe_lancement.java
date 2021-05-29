package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rampe_lancement extends Obstacle{
 private Bande_Equation b;
 private int x;
 private int y;
 private Image image;
 private Point p1;
 private Point p2;
 
 public Rampe_lancement (String path, int x, int y) {
	 super(x,y);
	 image = new Image(path, 350, 800, true, false);
	 this.p1 = new Point (x,y);
	 this.p2 = new Point (x+200,y);
	 b = new Bande_Equation(p1,p2,0,0,0,-10);
 }
	@Override
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image,x+362,y-20);
	}
	
	//retourne la liste de bandes associées à cet obstacle
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b};
		return listebandes;
	}
}
