package tsp.flipper;

public class Bande_Battant extends Bande{

	public boolean actif;
	
	//CONSTRUCTEUR
	public Bande_Battant(Point point1, Point point2, boolean activation) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		this.actif = activation;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.1;
		this.seuil=7;
	}

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on joue le bruitage du rebond
	@Override
	public void collision(Balle b, GameInfo inf) {
		// TODO Auto-generated method stub
		  if (b.readyBounce >seuil) { 
		  if (Hitbox.onLine(this, b)) {
				inf.fxmanager.playFX("Flipperhit.mp3");
			  if(this.actif) { 	//la collision apporte de la vitesse à la balle si le battant est actif id est en mouvement
				  b.speedVector.x+=this.boost*this.getXdirection();
				  b.speedVector.y+=this.boost*this.getYdirection();
			  }
			  else {//sinon c'est un rebond normal
				  b.speedVector.x*=0.85;
				  b.speedVector.y*=0.85;
			  }
			  b.getDirBounce(this);//on récupère la direction du rebond
			  b.readyBounce = 0;
			  inf.score +=0;
		  }
	}
}
	//active les battants(ie signale qu'ils sont en mouvements)
	public void activation(boolean a) {
		this.actif=a;
	}
}
