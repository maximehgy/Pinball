package tsp.flipper;

public class Bande_Target extends Bande_Classique {
	
	private Cible target;
	
	//CONSTRUCTEUR
	public Bande_Target (Point point1, Point point2, Cible target) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//rebond classique
	//la cible se désactive
	public void collision(Balle b, GameInfo inf) {
		if (b.readyBounce >this.seuil) { 
			if (Hitbox.onLine(this, b)) {
				b.getDirBounce(this);
				b.speedVector.x*=0.85;
				b.speedVector.y*=0.85;
				b.readyBounce = 0;
				inf.score +=100;
				target.activation();
			}
		}
	}
}

