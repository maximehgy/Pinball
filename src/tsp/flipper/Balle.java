package tsp.flipper;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Balle {

  public Image image;
  public double x;
  public double y;
  public Vector2Dspeed speedVector;
  private int width;
  private int height;
  public int maxX;
  public int maxY;
  public double accX;
  public double accY;
  public Point location;
  public int readyBounce;
  public double speedlimit;
  public boolean visible;

  //CONSTRUCTEUR
  public Balle(Point p,Vector2Dspeed vspeed, double accy, int maxX, int maxY) {
    image = new Image("balle.png", 30, 30, false, false);
    this.width = 30;
    this.height = 30;
    this.maxX = maxX;
    this.maxY = maxY;
    this.x =p.x;
    this.y =p.y;
    this.location= new Point(x, y);
    speedVector = vspeed;
    this.visible=true;
    this.readyBounce=20;
    this.setAcc(accy);
    this.setLimit(9.8);
  }

  //GETTER
  public int getwidth() {
    return width;
  }

  public int getheight() {
    return height;
  }
  
  //SETTER
  public void setPosition(double d, double e) {
    this.x = d;
    this.y = e;
    validatePosition();
  }

  public void setSpeed(double x, double y) {
    this.speedVector.x = x;
    this.speedVector.y = y;
  }
  
  public void setAcc(double d) { 
	  this.accX = 0;
	  this.accY = d;
  }
  
  public void setLimit(double limit) {
	  this.speedlimit=limit;
  }

  //UPDATE
  // change les champs physiques de la balle d'une frame à l'autre
  public void updatePosition() {
    x += speedVector.x;
    y += speedVector.y;
    this.location.setX(x);
    this.location.setY(y);
    validatePosition(); //on vérifie que la position obtenue est valide
  }

  //on s'assure que la vitesse ne déasse pas la vitesse limite
  public void updateSpeed() {
	  speedVector.x += accX;
	  speedVector.y += accY;
	  if (speedVector.x > speedlimit) {
	  speedVector.x = speedlimit;
	  }
	  if (speedVector.y > speedlimit) {
	  speedVector.y = speedlimit;
	  }
	  if (speedVector.x < -speedlimit) {
	  speedVector.x = -speedlimit;
	  }
	  if (speedVector.y < -speedlimit) {
	  speedVector.y = -speedlimit;
	  }
  }

  

  //AUTRES METHODES

  //détermine si la balle rebondit sur les bors de la fenêtre
  //si ses coordonnées + son rayon dépassent les abscisses limites, on inverse la vitesse
  //0.9 pour une petite perte d'énergie
  //de même pour les ordonnées
  public void validatePosition() { 
    if(x >= maxX) { 
      x = maxX;
      speedVector.x *= -0.9; 
    } else if(x < 0) {
      x = 0;
      speedVector.x *= -0.9;
    }

    if(y >= maxY) {
      y = maxY;
      speedVector.y *= -0.9;
    } else if(y < 0) {
      y = 0;
      speedVector.y *= -0.9;
    }
  }
  
  //augmente le compteur qui détermine si la balle peut rebondir
  public void increaseBounce () { 
	  this.readyBounce +=1;
  }
 
  //change la direction du vecteur vitesse lors d'une collision
  //on calcule l'angle formé avec la normale de l'arrête collisionée
  //on le multiplie par -2 tel que l'angle d'entrée et de sortie soient égaux par apport à la normale (rebond type Snell Descartes)
  //on inverse le vecteur vitesse
  //et on le tourne de l'angle précedemment calculé
  //on arrondit la vitesse pour éviter des bugs de collision
  public void getDirBounce (Bande b){
	  this.speedVector.reverse();
	  double anglenormal = b.direction.getAngle();
	  double anglein = this.speedVector.getAngle();
	  double angle = anglein - anglenormal;
	  angle *=-2; 
	  this.speedVector.rotateBy(angle);
  }
  
  //Gère les collisions avec une arrête donnée
  //la balle ne peut pas rebondir plusieurs fois à moins de 4 frames d'intervalle pour corriger des bugs de collision
  // On vérifie que la balle se situe sur l'arrête
  //dans le cas ou l'arrête engendre un rebond actif, on l'accélère selon la normale de l'arrête
  // on oriente le vecteur vitesse de manère adéquate
  // on réinitialise le compteur de rebond
  public void checkCollision (Bande b, GameInfo inf){
	  b.collision(this,inf);
  }


  // change la vitesse de la balle en fonction de la touche sur laquelle le joueur a appuyé
  public void changeSpeed(KeyCode code) {
    switch(code) {
    case Q:  speedVector.x--; break;
    case D: speedVector.x++; break;
    case Z:    speedVector.y--; break;
    case S:  speedVector.y++; break;
    default:
    }
  }


  // on affiche la balle à des coordonnées adaptées à son sprite
  public void render(GraphicsContext gc) {
	  if(visible) {
		  gc.drawImage(image, x-15, y-15);
	  }
  }
}