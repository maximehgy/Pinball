package tsp.flipper;

public class Bande_Classique extends Bande{

	//CONSTRUCTEUR
	public Bande_Classique(Point point1, Point point2) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.0;
		this.seuil=4;
	}

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on récupère la direction du rebond
	//on rebondit avec une petite perte d'energie
	public void collision(Balle b, GameInfo inf) {
		if (b.readyBounce >this.seuil) { 
			if (Hitbox.onLine(this, b)) {
				b.getDirBounce(this);
				b.speedVector.x*=0.85;
				b.speedVector.y*=0.85;
				b.readyBounce = 0;
				inf.score +=0;			
			}
		}
	}
}