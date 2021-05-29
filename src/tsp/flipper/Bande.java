package tsp.flipper;


public abstract class Bande {
	public Point p1;
	public Point p2;
	public Vector2Dposition direction;
	public Vector2Dposition support;
	public double boost;
	int seuil;

	//CONSTRUCTEUR
	public Bande (Point point1, Point point2) {
		this.p1 = point1;
		this.p2 = point2;
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		support = new Vector2Dposition(x, y);
		this.direction = (support.getPerp());
		boost=0.08;
	}
	
	//retourne la coordonnée x du vecteur normal
	public double getXdirection () {
		return (direction.x);
	}
	
	//retourne la coordonnée y du vecteur normal
	public double getYdirection () {
		return (direction.y);
	}
	
	//permet de tourner une bande
    public void rotateBy (double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double newX = 0;
        double newY = 0;
        newX = p1.x + (p2.x- p1.x)*cos - (p2.y- p1.y)*sin;

        newY = p1.y + (p2.x-p1.x)*sin + (p2.y-p1.y)*cos;
        p2.x = newX;
        p2.y = newY;
        support.x= p2.x - p1.x;
        support.y= p2.y - p1.y;
        this.direction=(support.getPerp());
    }
    
    //définira ce que fait chaque bande en fonction du type d'obstacle
    abstract public void collision(Balle b, GameInfo inf);
    		
}