package tsp.flipper;

public class Bande_Teleport extends Bande{
	Bande tp;

	//CONSTRUCTEUR
	public Bande_Teleport(Point point1, Point point2) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.08;
		this.seuil=3;
	}
	
	//associe une bande pour savoir ou téléporter la balle
	public void getTP (Bande b) {
		this.tp=b;
	}

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on téléporte la balle au milieu de l'arrete associée dans sa direction normale
	@Override
	public void collision(Balle ball, GameInfo inf) {
		// TODO Auto-generated method stub
		if (ball.readyBounce >this.seuil) { 
			if (Hitbox.onLine(this, ball)) {
				inf.fxmanager.playFX("Scoring.mp3");
				ball.setPosition((tp.p1.x+tp.p2.x)/2, (tp.p1.y+tp.p2.y)/2);
				ball.setSpeed(tp.getXdirection(), tp.getYdirection());
				ball.readyBounce = 0;
				inf.score +=100;
			}
		}
	}
}