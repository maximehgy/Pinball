package tsp.flipper;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//centralise les informations du jeu et permet de g�rer la partie
public class GameInfo {
	public String choixniveau = "";
	public int score =0;
	public Media_Manager fxmanager = new Media_Manager();
	public Media_Manager musicmanager = new Media_Manager();
	public AnimationTimer timer;
	public Stage stage;
	public Scene scene;
	public InitialisationMap init;
	public Image bg;
	public Group groupe;
	public Canvas canvas;
	public boolean interieur = false;
	public String pseudo="Empty";
	public Label score1=new Label ("empty");
	public Label score2=new Label ("empty");
	public Label score3=new Label ("empty");
	public Label score4=new Label ("empty");
	public Label score5=new Label ("empty");
	public String numniveau="";
	public GraphicsContext gc;
	public File file;
	public String tit="";
	
	//d�termine le niveau en m�moire ie celui qui sera charg� en cas de changement de niveau
	public void setChoixNiveau( String path) {
		choixniveau = path;
	}
	
	public void setnumniveau (String num) {
		numniveau=num;
	}
	
	//change de niveau
	public void changeLevel(Group g,String num) {
		// TODO Auto-generated method stub
		this.score=0;
		timer.stop();//stop le jeu
		init.listeObstacle = new ArrayList<Obstacle>(); //cr�e une nouvelle liste d'obstacles
		init.listeBattant = new ArrayList<Battant>();
    	init.listeBalle = new ArrayList<Balle>();
    	init.listeLauncher = new ArrayList<Launcher>();
		//donne � la balle les propri�t�s physiques voulues et cr�e ses battants
		this.setnumniveau(num);
		init.createList(this);//remplit la liste d'obstacle correspondant au nouveau niveau
		System.out.println(this.numniveau);
		bg = new Image ("bg"+this.numniveau+".png",680,800,true, false);//d�finit l'image correspondant au nouveau niveau
		this.musicmanager.mediaplayer.pause();//met la musique en pause pour que le gestionnaire de musique puisse changer de musique
		timer.start();//relance le jeu
		for(Battant bat : init.listeBattant) {
			g.getChildren().add(bat.imageview);
		}
		}
		
	}
