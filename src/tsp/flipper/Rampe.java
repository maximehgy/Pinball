package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rampe extends Obstacle{

	Point p;
	Image image;
	Bande_Teleport_lancement tp1;
	Bande_Teleport_lancement tp2;
	Bande_Classique b1;
	Bande_Classique b2;
	public boolean actif;

	//CONSTRUCTEUR
	public Rampe(String path, int x1, int y1) {
		super(x1,y1);
		this.actif = true;
		image = new Image(path, 90, 230, true, true);
		Point p11= new Point (x1+10,y1+220);
		Point p12= new Point (x1+80,y1+220);
		tp2 = new Bande_Teleport_lancement (p11,p12,0,1,this);
		tp2.seuilvitesse=6;
		Point p21 = new Point (x1+10,y1);
		Point p22 = new Point (x1+80,y1);
		tp1 = new Bande_Teleport_lancement (p21,p22,0,-1,this);
		tp1.seuilvitesse=0;
		tp1.getTP(tp2);
		tp2.getTP(tp1);
		p = new Point (x1,y1);
		b1 = new Bande_Classique(p21,p11);
		b2 = new Bande_Classique(p22,p12);
	}

	public void desactive() {
		this.actif =! this.actif;
	}
	//affiche l'image
	@Override
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image, p.x, p.y);
		//gc.strokeLine(tp1.p1.x, tp1.p1.y, tp1.p2.x ,tp1.p2.y);
		//gc.strokeLine(tp2.p1.x, tp2.p1.y, tp2.p2.x ,tp2.p2.y);
	}

	//renvoie des bandes de téléportation car c'est un tunnel
	@Override
	public Bande[] getBandes() {
		Bande[] listebandes ={this.tp1, this.tp2,b1,b2};
		return listebandes;
	}
}
