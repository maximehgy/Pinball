package tsp.flipper;

public class Vector2Dspeed {

	public double x;
	public double y;
    
	//CONSTRUCTEUR
    public Vector2Dspeed (double x, double y) {	
        this.x = x;
        this.y = y;
    }
    
    //SETTER
    public void set(Vector2Dspeed v) {
        this.x = v.x;
        this.y = v.y;
    }
    
    //GETTER
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
    
    //produit scalaire
    public double dot (Vector2Dposition v) {
    	return(this.x*v.x + this.y*v.y);
    }
    
    //inverse les coordonnées
    public void reverse() {
    	this.x *= -1;
    	this.y *= -1;
    }
    
    //retourne l'angle par rapoort à un autre vecteur
    public double getAnglewith (Vector2Dposition v) {
    	double angle1 =this.getAngle() - v.getAngle();
    	return angle1;
    }
    
    //retourne l'angle que fait ce vecteur avec l'origine
    public double getAngle() {
    	double angle = Math.atan2(y,x);
    	angle += Math.PI;
        return angle;
    }
    
    //tourne le vecteur d'un certain angle
    public void rotateBy(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double rx = x * cos - y * sin;
        y = (x * sin + y * cos) ;
        x = rx ;
    }
    
    //Crée un nouveau vecteur de taille et d'orientation voulue
    public static Vector2Dspeed toCartesian(double magnitude, double angle) {
        return new Vector2Dspeed(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }

    //tourne  le vecteur jusqu'à un certain angle
    public void rotateTo(double angle) {
        set(toCartesian(getLength(), angle));
    }
}