package tsp.flipper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class InitialisationMap {

    public ArrayList<Obstacle> listeObstacle;
    public ArrayList<Battant> listeBattant;
    public ArrayList<Balle> listeBalle;
    public ArrayList<Launcher> listeLauncher;
    
    //CONSTRUCTEUR
    public InitialisationMap () {
    	listeObstacle = new ArrayList<Obstacle>();
    	listeBattant = new ArrayList<Battant>();
    	listeBalle = new ArrayList<Balle>();
    	listeLauncher = new ArrayList<Launcher>();
    }
	
    // AUTRES METHODES   
    public void createList (GameInfo informations) {
    	listeObstacle = new ArrayList<Obstacle>();
    	listeBattant = new ArrayList<Battant>();
    	listeBalle = new ArrayList<Balle>();
    	listeLauncher = new ArrayList<Launcher>();
    BufferedReader textFile = null;
	try {
		textFile = new BufferedReader(new FileReader("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/" + informations.choixniveau));//d�fnit ou vont etre lus les fichiers
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

 		ArrayList<ArrayList<String>> tableau = new ArrayList<ArrayList<String>>();
 		ArrayList<String> liste = new ArrayList<String>();
 		String mot = "";
 		try {
			for (String line = textFile.readLine() ; line != null ; line = textFile.readLine()) {
				liste = new ArrayList<String>();
				for(int i=0; i<line.length(); i++) {
					char lettre = line.charAt(i);
					if(lettre != ' ') {
						mot = mot.concat(String.valueOf(lettre));
					} else { 
						liste.add(mot);
						mot = ""; }
				}
				tableau.add(liste);
			}
			//Cr�ation des obstacles en parcourant la liste en fonction de la lettre rencontr�e
			Obstacle obstacle;
	 		ArrayList<String> courant;
	 		for (int i = 0; i<tableau.size(); i++) {
	 			courant = tableau.get(i);
	 			switch(courant.get(0)){
	 			case "C" : obstacle = new Champignon("Champignon" + informations.numniveau + ".png", 80, 80, Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)) ,Integer.parseInt(courant.get(3)), Integer.parseInt(courant.get(4)));
	 			break;
	 			case "M" : obstacle = new Mur(Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)) ,Integer.parseInt(courant.get(3)), Integer.parseInt(courant.get(4)),"Mur" + informations.numniveau + ".png");
	 			break; 
	 			case "P" : obstacle = new Porte(80,120,Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)) ,Integer.parseInt(courant.get(3)), Integer.parseInt(courant.get(4)),
	 					Integer.parseInt(courant.get(5)), Integer.parseInt(courant.get(6)) ,Integer.parseInt(courant.get(7)), Integer.parseInt(courant.get(8)),listeObstacle);
	 			break; 
	 			case "MR" : obstacle = new Mur_Reversible(Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)) ,Integer.parseInt(courant.get(3)), Integer.parseInt(courant.get(4)),
	 					"reversible"+informations.numniveau+".png", Integer.parseInt(courant.get(5)) ,Integer.parseInt(courant.get(6)));
	 			break; 
	 			case "MI" : obstacle = new Mur_invisible(Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)) ,Integer.parseInt(courant.get(3)), Integer.parseInt(courant.get(4)));
	 			break; 
	 			case "T" : obstacle = new Cible("Cible"+informations.numniveau +".png", Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			break;
	 			case "R" : obstacle = new Rampe ("tunnel"+informations.numniveau+".png", Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			break;
	 			case "BD" :	listeBattant.add(new Battant("battantdroit.png",new Point(Integer.parseInt(courant.get(1)),Integer.parseInt(courant.get(2))),false));
	 			obstacle = null;
	 			break;
	 			case "BG" :	listeBattant.add(new Battant("battant.png",new Point(Integer.parseInt(courant.get(1)),Integer.parseInt(courant.get(2))),true));
	 			obstacle = null;
	 			break;
	 			case "TP" : obstacle = new Teleporteur (Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)),Integer.parseInt(courant.get(3)),Integer.parseInt(courant.get(4)),courant.get(5),courant.get(6));
	 			break;
	 			case "Bal" : double acc = Integer.parseInt(courant.get(5));
	 						 acc/=100;
	 						 listeBalle.add(new Balle (new Point(Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2))),new Vector2Dspeed(Integer.parseInt(courant.get(3)),Integer.parseInt(courant.get(4))),
	 						acc,Integer.parseInt(courant.get(6)),Integer.parseInt(courant.get(7))));
	 			obstacle = null;
	 			break;
	 			case "Lau" : Launcher lanceur = new Launcher(Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			listeLauncher.add(lanceur);
	 			obstacle = lanceur;
	 			break;
	 			case "Rp" : obstacle = new Rampe_lancement ("rampe"+informations.numniveau+".png",Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			break;
	 			case "PT" : obstacle = new Piegetournant("tournant"+informations.numniveau+".png","Mur2.png",Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			break;
	 			case "TB" : obstacle = new Trapball("trapball.png",Integer.parseInt(courant.get(1)), Integer.parseInt(courant.get(2)));
	 			break;
	 			case "Type" : informations.numniveau = "2";
	 			obstacle = null;
	 			break;
	 			default : obstacle = null;
	 			break;
	 			} 
	 			listeObstacle.add(obstacle);
	 		}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		try {
			textFile.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}
}

