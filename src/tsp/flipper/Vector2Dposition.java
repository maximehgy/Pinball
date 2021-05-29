package tsp.flipper;


public class Vector2Dposition {

	public double lenght;
	public double x;
	public double y;
    

	//CONSTRUCTEUR
    public Vector2Dposition (double x2, double y2) {

        this.x = x2;
        this.y = y2;
        lenght = this.getLength();

    }
    
    // retourne la longueur du vecteur
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
    
    //effectue le produit sclaire avec un autre vecteur
    public double dot (Vector2Dposition v) {
    	return(this.x*v.x + this.y*v.y);
    }

    //renvoie un vecteur normal à l'arrete et de meme norme
    public Vector2Dposition getPerp() {
    	return new Vector2Dposition (-y, x);
    }
    
    //détermine l'angle formé par le vecteur avec l'axe des abscisses
    public double getAngle() {
    	double angle = Math.atan2(y, x);
    	angle += Math.PI;
        return angle;
    }
    
    public void reverse() {
    	this.x *= -1;
    	this.y *= -1;
    }
    

}