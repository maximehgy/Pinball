package tsp.flipper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Battant {
	public Image image;
	public ImageView imageview;
	public Rotate rot1;
	public Rotate rot2;
	public Point p1;
	public Point p2;
	public Bande_Battant hitbox;
	private Timeline timeline1;
	private Timeline timeline2;
	private int orientationg;
	private int orientationgsetter;
	private int orientationd;
	private int orientationdsetter;
	private double angleg;
	public boolean gauche;
	private Media_Manager effectmanager;

	//CONSTRUCTEUR
	public Battant (String path, Point p1, boolean g) {
		image = new Image(path,95,47,false,false);
		this.p1=p1;
		effectmanager = new Media_Manager();
		this.gauche=g;
		imageview = new ImageView(image);

		if (gauche) {//si le battant est un battant gauche
		imageview.setTranslateX(p1.x-10);
		imageview.setTranslateY(p1.y-10);
		imageview.setRotate(20);
		p2= new Point (p1.x+100,p1.y);
		hitbox = new Bande_Battant (p1,p2,false);
		hitbox.rotateBy(25*Math.PI/180);
		}
		else {//si le battant est un battant droit
			p2 = new Point (p1.x-100,p1.y);
			hitbox = new Bande_Battant (p1,p2,false);
			hitbox.boost*=-1;//on inverse son boost pour que la balle rebondisse dans la bonne direction
			imageview.setTranslateX(p1.x-80);
			imageview.setTranslateY(p1.y);
			imageview.setRotate(-20);
			hitbox.rotateBy(-25*Math.PI/180);
		}

		timeline1 = new Timeline(//permet d'animer le battant progressivement lors d'une rotation
				new KeyFrame(Duration.millis(10), e -> rotateGauche()),    
				new KeyFrame(Duration.millis(20), e -> rotateGauche()),
				new KeyFrame(Duration.millis(30), e -> rotateGauche()),
				new KeyFrame(Duration.millis(40), e -> rotateGauche()),
				new KeyFrame(Duration.millis(50), e -> rotateGauche()),
				new KeyFrame(Duration.millis(60), e -> rotateGauche()),
				new KeyFrame(Duration.millis(70), e -> rotateGauche()),
				new KeyFrame(Duration.millis(80), e -> rotateGauche()),
				new KeyFrame(Duration.millis(90), e -> rotateGauche()),
				new KeyFrame(Duration.millis(100), e -> rotateGauche()),    
				new KeyFrame(Duration.millis(110), e -> rotateGauche()),
				new KeyFrame(Duration.millis(120), e -> rotateGauche()),
				new KeyFrame(Duration.millis(130), e -> rotateGauche()),
				new KeyFrame(Duration.millis(140), e -> rotateGauche()),
				new KeyFrame(Duration.millis(150), e -> rotateGauche()),
				new KeyFrame(Duration.millis(160), e -> rotateGauche()),
				new KeyFrame(Duration.millis(170), e -> rotateGauche()),
				new KeyFrame(Duration.millis(180), e -> rotateGauche())
				);
		timeline1.setCycleCount(1);

		timeline2 = new Timeline(
				new KeyFrame(Duration.millis(10), e -> rotateDroite()),    
				new KeyFrame(Duration.millis(20), e -> rotateDroite()),
				new KeyFrame(Duration.millis(30), e -> rotateDroite()),
				new KeyFrame(Duration.millis(40), e -> rotateDroite()),
				new KeyFrame(Duration.millis(50), e -> rotateDroite()),
				new KeyFrame(Duration.millis(60), e -> rotateDroite()),
				new KeyFrame(Duration.millis(70), e -> rotateDroite()),
				new KeyFrame(Duration.millis(80), e -> rotateDroite()),
				new KeyFrame(Duration.millis(90), e -> rotateDroite()),
				new KeyFrame(Duration.millis(100), e -> rotateDroite()),    
				new KeyFrame(Duration.millis(110), e -> rotateDroite()),
				new KeyFrame(Duration.millis(120), e -> rotateDroite()),
				new KeyFrame(Duration.millis(130), e -> rotateDroite()),
				new KeyFrame(Duration.millis(140), e -> rotateDroite()),
				new KeyFrame(Duration.millis(150), e -> rotateDroite()),
				new KeyFrame(Duration.millis(160), e -> rotateDroite()),
				new KeyFrame(Duration.millis(170), e -> rotateDroite()),
				new KeyFrame(Duration.millis(180), e -> rotateDroite())
				);
		timeline2.setCycleCount(1);
		orientationgsetter=1;//compteurs permettant de gérer la rotation convenablement
		orientationdsetter=1;
		orientationg = 0;
		orientationd = 0;
		angleg = 3*Math.PI/180;
	}

	//AUTRES METHODES

	public void pressLeft () {
		timeline1.stop();//on stop l'animation en cours
		orientationgsetter=1;	//on définit l'état du battant avec le compteur
		if (hitbox.actif == false) { //pour ne jouer l'effet sonore qu'une seule fois
			effectmanager.playFX("Flippermove.mp3");
		}
		hitbox.actif=true;//on fait passer le battant actif ie en mouvement
		rot1 = new Rotate(-3, 0, 10, 0, Rotate.Z_AXIS); //on définit la rotation
		timeline1.play();//on lance l'animation

	}

	//mme logique pour les autres mthodes "press" et "release"
	public void pressRight () {
		if (hitbox.actif == false) {
			effectmanager.playFX("Flippermove.mp3");
		}
		hitbox.actif = true;
		timeline2.stop();
		orientationdsetter=1;
		rot2 = new Rotate(3, 110, 10, 0, Rotate.Z_AXIS);
		timeline2.play();
	}

	public void releaseLeft () {
		hitbox.actif = false;
		timeline1.stop();
		orientationgsetter=-1;
		rot1 = new Rotate(3, 0, 10, 0, Rotate.Z_AXIS);
		timeline1.play();
	}

	public void releaseRight () {
		hitbox.actif = false;
		timeline2.stop();
		orientationdsetter=-1;
		rot2 = new Rotate(-3, 110, 10, 0, Rotate.Z_AXIS);
		timeline2.play();
	}

	//effectue une rotation infinitésimale si la rotation n'est pas déja terminée
	// met à jour les compteurs
	public void rotateGauche() {//définit si la rotation se fait dans un sens
		if (orientationgsetter >= 0) {
			if (orientationg < 18) {
				hitbox.rotateBy(-angleg);
				imageview.getTransforms().add(rot1);
				orientationg += (1*orientationgsetter);
			}

		}
		else {// ou dans l'autre
			if (orientationg > 0) {
				hitbox.rotateBy(angleg);
				imageview.getTransforms().add(rot1);
				orientationg += (1*orientationgsetter);
			}
		}
	}

	//même logique que rotation gauche mais si le battant est de type battant droite
	public void rotateDroite() {
		if (orientationdsetter >= 0) {
			if (orientationd < 18) {
				hitbox.rotateBy(angleg);
				imageview.getTransforms().add(rot2);
				orientationd += (1*orientationdsetter);
			}

		}
		else {
			if (orientationd > 0) {
				hitbox.rotateBy(-angleg);
				imageview.getTransforms().add(rot2);
				orientationd += (1*orientationdsetter);
			}
		}
	}

}
