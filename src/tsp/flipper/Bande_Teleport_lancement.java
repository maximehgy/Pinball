package tsp.flipper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Bande_Teleport_lancement extends Bande_Teleport {

	private double speedcache;
	private Vector2Dposition launcher;
	private Bande_Teleport_lancement tp1;
	private Timeline timeline;
	private Vector2Dspeed memspeed;
	public int seuilvitesse;
	private Rampe rampe;
	
	//CONSTRUCTEUR
	public Bande_Teleport_lancement(Point point1, Point point2, double launcherx, double launchery, Rampe rampe) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		launcher = new Vector2Dposition(launcherx,launchery);
		this.seuil=0;
		memspeed = new Vector2Dspeed (0,0);
		this.rampe=rampe;
	}

	//on récupère la vitesse de la balle (à l'entrée du tunnel)
	public void getBallSpeed(Balle b) {
		speedcache = b.speedVector.getLength();
		memspeed.x=b.speedVector.x;
		memspeed.y=b.speedVector.y;
	}
	
	//on associe à une bande sa bande associée
	public void getTP (Bande_Teleport_lancement b) {
		this.tp1=b;
	}

	//permet de poursuivre le processus de téléportation
	public void pursuit (Balle b) {
		if (speedcache>seuilvitesse) {	//si la vitesse permet de franchir le tunnel
				b.setPosition((tp1.p1.x+tp1.p2.x)/2, (tp1.p1.y+tp1.p2.y)/2);	//on positionne la balle au milieu de l'arrête associée
				b.setSpeed(tp1.launcher.x*speedcache, tp1.launcher.y*speedcache);	//on lui donne une vitesse proportionnelle à la vitesse d'entrée mais dans la direction imposée par l'arrête associée
			}
			//sinon la balle retourne à l'entrée du tunnel
			else {
				b.setPosition((this.p1.x+this.p2.x)/2, (this.p1.y+this.p2.y)/2);
				b.setSpeed(this.launcher.x*speedcache, this.launcher.y*speedcache);
			}
			b.readyBounce=0;
			b.visible=true;//on réaffiche la balle
			Timeline activation = new Timeline(
					new KeyFrame(Duration.seconds(0.4), e -> rampe.desactive())
	);
			activation.setCycleCount(1);
			activation.play();
		}
	

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on cache la balle
	//on recupere sa vitesse
	//on attends une seconde et on poursuit le processus
	@Override
	public void collision(Balle ball, GameInfo inf) {
		if (this.rampe.actif) {
		// TODO Auto-generated method stub
			if (Hitbox.onLine(this, ball)) {
				rampe.desactive();
				ball.visible=false;
				getBallSpeed(ball);
				ball.setSpeed(0, 0);
				timeline = new Timeline(
						new KeyFrame(Duration.seconds(1), e -> pursuit(ball))
		);
				timeline.setCycleCount(1);
				timeline.play();
			}
		}
	}
	}
