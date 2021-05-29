package tsp.flipper;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Launcher extends Obstacle{

	private int x;
	private int y;
	public boolean notused;
	public Balle balle;
	public Image image;
	public double launchvalue;
	private AnimationTimer accumulation = new AnimationTimer() {
		public void handle(long arg0) {
			launchvalue+=0.3;
		}
	};

	//CONSTRUCTEUR
	public Launcher(int x, int y) {
		super(x,y);
		this.x=x;
		this.y=y;
		image = new Image("Launcher.png",60,60,false,true);
		notused = true;
	}

	public void launch(Balle balle) {
		// TODO Auto-generated method stub
		if(notused) {//si on ne l'a pas déja utilisé
			balle.setLimit(15);
			accumulation.stop();//on arrête d'accumuler l'energie
			balle.setSpeed(0, Math.min(-15,-launchvalue));//on donne la vitesse à la balle
			launchvalue = 0;//on réinitialise l'energie accumulée
			notused = false;//on a utilisé le launcher
			balle.setAcc(0.12);//on applique la gravité à la balle
		}
	}

	//accumule de l'energie à chaque frame
	public void accumulate() {
		if(notused) {
			// TODO Auto-generated method stub
			accumulation.start();
		}

	}
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image, x, y);
	}
}
