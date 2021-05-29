package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Cible extends Obstacle {

	public boolean actif;
	public Image image1;
	public Bande b1;
	public Bande b2;
	public Bande b3;
	public Bande b4;
	public int x;
	public int y;	
	public Point p1;
	public Point p2;
	public Point p3;
	public Point p4;
	private int width;
	private int height;


	//CONSTRUCTEUR
	//cr�e des arr�tes tout autour des coordonn�es du boutton
	public Cible(String path, int x, int y) {
		super(x, y);
		this.x=x;
		this.y=y;
		this.width=50;
		this.height=50;
		image1 = new Image(path, width, height, true, false);
		this.p1 = new Point (x, y);
		this.p2 = new Point (x + width, y);
		this.p3 = new Point (x+width, y+height );
		this.p4 = new Point (x, y+ height);

		this.b1 = new Bande_Target (p2,p1,this);
		this.b2 = new Bande_Target (p3,p2,this);
		this.b3 = new Bande_Target (p4,p3,this);	
		this.b4 = new Bande_Target (p1,p4,this);
		this.actif = true;
	}
	
	//dessine une image ou une autre si le boutton est activ� ou non
	@Override
	public void draw(GraphicsContext gc, Group g) {
		if (actif) {
		gc.drawImage(image1, x, y);
	}}
	
	//retourne la liste de bandes de cet obstacle
	public Bande[] getBandes() {
		if (this.actif) {
		Bande[] listebandes ={this.b1,this.b2,this.b3,this.b4};
		return listebandes;
		}
		else {
			return null;
		}
	}
	
	
	//inverse l'activation du boutton
	//s'il est actif ainsi que son boutton li�, ouvre la porte li�e
	public void activation() {
		if(this.actif) {
		this.actif =! actif;
		}
	}
}