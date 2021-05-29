package tsp.flipper;

public class Bande_Reversible extends Bande{
	
private Vector2Dposition passage;

	//CONSTRUCTEUR
	public Bande_Reversible(Point point1, Point point2, int xpassage, int ypassage) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		this.p1 = point1;
		this.p2 = point2;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.08;
		this.seuil=4;
		passage = new Vector2Dposition(xpassage, ypassage);
	}

	//si la balle va dans la direction autorisée
	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//rebond classique
	public void collision(Balle b, GameInfo inf) {
		if (b.speedVector.dot(passage)<0){
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
}