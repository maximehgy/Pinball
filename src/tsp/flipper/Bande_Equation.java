package tsp.flipper;

import javafx.animation.AnimationTimer;

public class Bande_Equation extends Bande{

	private double accX;
	private double accY;
	private double speedX;
	private double speedY;
	private AnimationTimer updateAcc;
	private double omegat;

	public Bande_Equation(Point point1, Point point2,double accX, double accY, double speedX, double speedY) {
		//CONSTRUCTEUR
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		this.accX=accX;
		this.accY=accY;
		this.speedX=speedX;
		this.speedY=speedY;
		boost=0.07;
		this.seuil=15;
	}
	
	public void circleacceleration (double bonus, Balle b,double normalize) {
	updateAcc = new AnimationTimer() {
		public void handle(long arg0) {
			omegat+=bonus;
			b.accX=-0.5*Math.cos(-omegat);
			b.accY=-0.7*Math.sin(-omegat);
			System.out.println(omegat);
			System.out.println(normalize);
			if (omegat >= (2*normalize-1.2)) {
				this.stop();
				b.accX=0;
				b.accY=0.12;
				b.speedlimit=10;
				b.speedVector.x *=0.65;
				b.speedVector.y *=0.65;
			}
		}
	};
	updateAcc.start();
}
	
	public void stopcircleacceleration() {
		updateAcc.stop();
	}
	
	//si la balle est prête a rebondir
	//si la balle est sur l'arrete
	//On set les parametres physiques de la balle pour lui donner l'équation voulue
	public void collision(Balle b, GameInfo inf) {
		if (b.readyBounce >seuil) { 
			if (Hitbox.onLine(this, b)) {
				b.readyBounce = 0;
				double normalize = b.speedVector.getLength()/10;
				circleacceleration (0.03,b,normalize);
				}
			}
		}
	}
