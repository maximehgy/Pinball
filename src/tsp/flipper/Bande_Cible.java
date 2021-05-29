package tsp.flipper;

public class Bande_Cible extends Bande_Classique {
	
	private Button bouton;
	
	//CONSTRUCTEUR
	public Bande_Cible (Point point1, Point point2, Button bouton) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.bouton = bouton;
	}
	
	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//rebond classique
	//la cible est activée
	public void collision(Balle b, GameInfo inf) {
		if (b.readyBounce >this.seuil) { 
			if (Hitbox.onLine(this, b)) {
				b.getDirBounce(this);
				b.speedVector.x*=0.85;
				b.speedVector.y*=0.85;
				b.readyBounce = 0;
				inf.score +=20;
				bouton.activation();
			}
		}
	}
}

