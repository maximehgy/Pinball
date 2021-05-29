package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Button extends Obstacle {

	public boolean actif;
	public Image imagea;
	public Image imagep;
	private Button linkedbutton;
	private Porte porte;	
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


	//CONSTRUCTEUR
	//crée des arrêtes tout autour des coordonnées du boutton
	public Button(int width, int height, int x, int y, double boost, double angle) {
		super(x, y);
		this.x=x;
		this.y=y;
		imagea = new Image("BouttonA.png", width, height, true, false);
		imagep = new Image("BouttonP.png", width, height, true, false);
		this.p1 = new Point (x, y);
		this.p2 = new Point (x + width, y);
		this.p3 = new Point (x+width, y+height );
		this.p4 = new Point (x, y+ height);

		this.b1 = new Bande_Cible (p2,p1,this);
		this.b2 = new Bande_Cible (p3,p2,this);
		this.b3 = new Bande_Cible (p4,p3,this);	
		this.b4 = new Bande_Cible (p1,p4,this);

	}
	
	//dessine une image ou une autre si le boutton est activé ou non
	@Override
	public void draw(GraphicsContext gc, Group g) {
		if (actif) {
		gc.drawImage(imagea, x, y);
	}
		else {
			gc.drawImage(imagep, x, y);
		}
	}
	
	//retourne la liste de bandes de cet obstacle
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1,this.b2,this.b3,this.b4};
		return listebandes;
	}
	
	//associe un autre boutton et une porte à ce boutton
	public void getlink (Button b, Porte p) {
		this.linkedbutton=b;
		this.porte = p;
	}
	
	//inverse l'activation du boutton
	//s'il est actif ainsi que son boutton lié, ouvre la porte liée
	public void activation() {
		if (!porte.ouvert) {
		this.actif = !this.actif;
		if (linkedbutton.actif){
			porte.ouverture();
		}
		}
	}
}