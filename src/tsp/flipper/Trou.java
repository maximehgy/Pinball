package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Trou extends Obstacle{
	Point p1;
	Point p2;
	Image image;
	Bande_Teleport tp1;
	Bande_Teleport tp2;
	Bande_Classique b1;
	Bande_Classique b2;
	Bande_Classique b7;
	Bande_Classique b8;
	Bande_Classique b9;
	Bande_Classique b14;
	
	//CONSTRUCTEUR
	public Trou(String path, int x1, int y1, int x2, int y2) {
		super(x1,y1);
		Point p11 = new Point (x1-96,y1);
		Point p12 = new Point (x1+96,y1);
		Point p13 = new Point (x1-96,y1+128);
		Point p14 = new Point (x1+96,y1+128);
		Point p15 = new Point (x1-96,y1+76);
		Point p17 = new Point (x1+96,y1+76);
		tp1= new Bande_Teleport(p15,p17);
		b1 = new Bande_Classique(p11,p12);
		b2 = new Bande_Classique(p11,p13);
		b7 = new Bande_Classique(p12,p14);
		
		Point p21 = new Point (x2-96,y2);
		Point p22 = new Point (x2+96,y2);
		Point p23 = new Point (x2-96,y2+128);
		Point p24 = new Point (x2+96,y2+128);
		Point p25 = new Point (x2-96,y2+76);
		Point p27 = new Point (x2+96,y2+76);
		tp2= new Bande_Teleport(p25,p27);
		b8 = new Bande_Classique(p21,p22);
		b9 = new Bande_Classique(p21,p23);
		b14= new Bande_Classique(p22,p24);
		
		this.p1 = p11;
		this.p2 = p21;
		image = new Image(path, 50, 50, true, true);
		//Associe chaque bande avec sa bande associée
		tp1.getTP(tp2);
		tp2.getTP(tp1);
		
	}
	
	@Override
	//dessine les deux trous associés
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image, p1.x, p1.y);
		gc.drawImage(image, p2.x, p2.y);
	}
	
	//retourne les bandes, notamment de téléportation
	@Override
	public Bande[] getBandes() {
		Bande[] listebandes ={this.tp1, this.tp2,b1,b2,b7,b8,b9,b14};
		return listebandes;
	}
}
