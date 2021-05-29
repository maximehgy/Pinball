package tsp.flipper;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class Piegetournant extends Obstacle {

	private int x;
	private int y;
	public Image image2;
	public Bande_Classique b;
	private Image image;
	public ImageView imagev;
	public boolean notinscene;
	private int width;
	private int height;

	
	public Piegetournant(String path1, String path2,int x, int y) {
		super(x, y);
		this.x=x;
		this.y=y;
		width=140;
		height=30;
		notinscene = true;
		Point p1 = new Point (x,y+15);
		Point p2 = new Point (x+140,y+15);
		b = new Bande_Classique (p1,p2);
		image = new Image(path1,width,height,false,true);
		image2 = new Image(path2,35,35,false,true);
		imagev = new ImageView (image);
		imagev.setRotate(-5);
		imagev.setX(x);
		imagev.setY(y);
		// TODO Auto-generated constructor stub
		Rotate rot = new Rotate(1, x, y+15, 0, Rotate.Z_AXIS); //on définit la rotation
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long arg0) {
				b.rotateBy(1*Math.PI/180);
				imagev.getTransforms().add(rot);
			}
		};
		animation.start();
	}
	
	//on ajoute le noeud graphique au groupe
	@Override
	public void draw(GraphicsContext gc, Group g) {
		if (notinscene) {
		g.getChildren().add(imagev);
		notinscene = false;
		}
		gc.drawImage(image2, x-17, y);
		//gc.strokeLine(b.p1.x, b.p1.y, b.p2.x, b.p2.y);
	}
	
	//on retourne une bande
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b};
		return listebandes;
	}
}
