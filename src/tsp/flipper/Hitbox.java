package tsp.flipper;

public class Hitbox {


	// retourne la distance entre 2 points
	public static double distance (Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
	}

	//vérifie si un point c est sur une arrete [a b] si distance AC + CB = AB, alors le point est sur l'arrete
	public static boolean onLine (Bande b, Balle bal) {

		if (distance(b.p1, bal.location) + distance(bal.location, b.p2) - distance(b.p1, b.p2) <= Math.sqrt(bal.speedVector.x*bal.speedVector.x + bal.speedVector.y*bal.speedVector.y)+1) {
			return true; // C is on the line.
		}
		return false;    // C is not on the line.	
	}
}