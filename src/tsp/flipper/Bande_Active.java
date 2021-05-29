package tsp.flipper;

public class Bande_Active extends Bande{

	public Bande_Active(Point point1, Point point2) {
		//CONSTRUCTEUR
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.07;
		this.seuil=2;
	}

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on joue le bruitage du rebond
	//la collision apporte de la vitesse à la balle
	// la balle récupère la direction dans laquelle elle doit rebondir
	public void collision(Balle b, GameInfo inf) {
		if (b.readyBounce >seuil) { 
			if (Hitbox.onLine(this, b)) {
				inf.fxmanager.playFX("Bounce1.mp3");
				b.speedVector.x+=this.boost*this.getXdirection();
			b.speedVector.y+=this.boost*this.getYdirection();
			b.getDirBounce(this);
			b.readyBounce = 0;
			inf.score +=30;
		}
		}
	}
}