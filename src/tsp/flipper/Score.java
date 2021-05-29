package tsp.flipper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.Label;


public class Score {
	private List<String> list = new ArrayList<String>();

	public File score;
	public GameInfo infos;

	public Score (GameInfo inf) {
		this.infos=inf;
	}

	public void createscore() {
		File score = new File ("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/score.txt"); //Cr?ation du fichier score
		this.score=score;
	}
	//Demande au joueur son pseuso et le m�morise dans GameInfos
	public void getclassement() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Quel est votre pseudo ?"); //Demande au joueur son pseudo
			String pseudo = sc.nextLine(); //Enregistre la r�ponse du joueur
			infos.pseudo = pseudo;
			/*int scoreencours = infos.score;
			if (score.length()==0) {
				BufferedWriter ecrire = new BufferedWriter(new FileWriter(System.getProperty("user.home")+ "/eclipse-workspace/pro3600-20-ran-1/ressources/score.txt", true));
				ecrire.write(pseudo + " " + scoreencours);
				ecrire.close();
				System.exit(1);
			}*/
		}
		catch (Exception e) {
		}
	}

	public void organise() throws IOException {
		int scoreencours = infos.score;
		BufferedWriter ecrire2 = new BufferedWriter(new FileWriter("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/score.txt", true));
		ecrire2.write("\n" + infos.pseudo + " " + scoreencours);
		ecrire2.close();
		list = new ArrayList<String>();
		BufferedReader lire = new BufferedReader(new FileReader("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/score.txt"));
		String line = lire.readLine();  

		//On parcourt le fichier txt et on cr?e une liste qui contient les ?l?ments du fichier
		while (line != null) { //Tant qu'il y a des lignes dans le fichier
			String delims = "[ ]";
			String[] separate = line.split(delims); //Les ?l?ments de la ligne sont s?par?s au niveau des espaces, et contenus dans le tableau separateur
			list.add(separate[0]);
			list.add(separate[1]);
			line = lire.readLine();
		}

		lire.close();

		for (int i=list.size(); i>0; i--){
			if ((i % 2 == 1) && (i>1) && (Integer.valueOf(list.get(i)) > Integer.valueOf(list.get(i-2)))) {
				Collections.swap(list, i-3, i-1);
				Collections.swap(list, i-2, i);
				//Clear le fichier
				PrintWriter pw = new PrintWriter("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/score.txt");
				pw.close();

				//Ecriture, dans l'ordre, des 5 meilleurs scores
				BufferedWriter ecrire = new BufferedWriter(new FileWriter("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/score.txt", true));
				ecrire.write(list.get(0) + " " + list.get(1));
				int n = list.size();
				for (int j=1; j <= Math.min(4, (n-2)/2); j++) {
					ecrire.write("\n" + list.get(2*j) + " " + list.get(2*j+1));

				}
				ecrire.close();

			}
		}
	}
	

	public void createclassement() {
			infos.score1=new Label(list.get(0) + "  " + list.get(1));
			infos.score2=new Label(list.get(2) + "  " + list.get(3));
			infos.score3=new Label(list.get(4) + "  " + list.get(5));
			infos.score4=new Label(list.get(6) + "  " + list.get(7));
			infos.score5=new Label(list.get(8) + "  " + list.get(9));
			list.clear();
	}

	}
	