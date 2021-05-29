package tsp.flipper;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Porte extends Obstacle {

	public boolean ouvert;
	public Image imageo;
	public Image imagef;
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
	private Button bt1;
	private Button bt2;
	private Bande_Changescene tp;
	private Media_Manager mm;

	//CONSTRUCTEUR
	public Porte (int width, int height, int x, int y, double boost, double angle,int x1, int y1, int x2, int y2, ArrayList<Obstacle> liste) {
		super(x, y);
		this.x=x;
		this.y=y;
		imageo = new Image("PorteO.png", width, height, true, false);
		imagef = new Image("PorteF.png", width, height, true, false);
		this.p1 = new Point (x+10, y);
		this.p2 = new Point (x + width-10, y);
		this.p3 = new Point (x+width-10, y+height-10 );
		this.p4 = new Point (x+10, y+height-10);
		this.b1 = new Bande_Classique (p2,p1);
		this.b2 = new Bande_Classique (p3,p2);
		this.b3 = new Bande_Classique (p4,p3);	
		this.b4 = new Bande_Classique (p1,p4);
		bt1 = new Button(60,60,x1,y1,1,10);
		bt2 = new Button(60,60,x2,y2,1,10);
		bt1.getlink(bt2, this); //on associe le boutton1 avec le boutton2 et cette porte
		bt2.getlink(bt1, this);// on associe le boutton2 avec le boutton1 et cette porte
		liste.add(bt1);//on ajoutes les 2 boutons à la liste d'obstacle
		liste.add(bt2);
		tp = new Bande_Changescene(p3,p4,"donjon","2");
		mm = new Media_Manager();

	}
	//Dessine une image ou l'autre si la porte est ouverte ou fermée
	@Override
	public void draw(GraphicsContext gc, Group g) {
		if (ouvert) {
		gc.drawImage(imageo, x, y);
	}
		else {
			gc.drawImage(imagef, x, y);
		}
	}
	
	//retourne les bandes associées à l'obstacle
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1,this.b2,this.b3,this.b4};
		return listebandes;
	}
	
	//change le statut de la porte comme ouvert
	//transforme une bande classique en une bande qui permet d'aller dans un autre niveau
	public void ouverture() {
		this.ouvert= true;
		this.b3=tp;
		mm.playFX("decouverte.mp3");
	}
}