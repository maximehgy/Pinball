package tsp.flipper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer.Status;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;



public class Flipper extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) {
		//on d�finit la taille de notre fen�tre et son nom
		stage.setTitle("Flipper");
		int WIDTH = 680;
		int HEIGHT = 800;
		
		//On cr�e les �l�ments de gestion de jeu
		Media_Manager mediamanager = new Media_Manager();
		GameInfo infos = new GameInfo();
		InitialisationMap init = new InitialisationMap ();
		Score scoremanager = new Score(infos);
		//scoremanager.getclassement();	
		
		//on initialise les diff�rents �l�ments du menu de d�marrage et le groupe les contenants
		VBox rootintro = new VBox();
		rootintro.setPrefSize(WIDTH, HEIGHT);
		Image univers = new Image("BGtitle.jpg", WIDTH, HEIGHT, true, false);//image de fond
		Label label1 = new Label("Parviendrez-vous à entrer dans le temple ?"); label1.setTranslateX(150); label1.setFont(Font.font("Supernatural Knight", 20));label1.setTextFill(Color.rgb(255, 255, 255));
		Label label2 = new Label("Saurez-vous utiliser vos 4 flippers correctement"); label2.setTranslateX(140); label2.setFont(Font.font("Supernatural Knight", 20));label2.setTextFill(Color.rgb(255, 255, 255));
		Label label3 = new Label("Créez votre propre niveau !"); label3.setTranslateX(200); label3.setFont(Font.font("Supernatural Knight", 20));label3.setTextFill(Color.rgb(255, 255, 255));
		Text text = new Text("Choix du niveau :"); text.setTranslateX(180); text.setFill(Color.rgb(255, 255, 255)); text.setFont(Font.font("Supernatural Knight", FontWeight.BOLD, 45));
		Button button1 = new Button("Le temple de Brocéliande ", new ImageView(new Image("templeforet.jpg", 150,150,true,false))); button1.setStyle("-fx-font-size: 30;-fx-base: #b5e7c2;");
		Button button2 = new Button("Le Donjon d'Hyrule", new ImageView(new Image("chateaumini.png", 150,100,true,false))); button2.setStyle("-fx-font-size: 30;-fx-base: #b7e7c2;");
		Button button3 = new Button("Editeur de tables"); button3.setStyle("-fx-font-size: 30;-fx-base: #b7e7c2;"); 
		button1.setMaxSize(680, 400); button2.setMaxSize(680, 400); button3.setMaxSize(680, 400);
		BackgroundImage myBI= new BackgroundImage(univers,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

		rootintro.getChildren().addAll(text, button1, label1, button2, label2, button3, label3);
		rootintro.setBackground(new Background(myBI));
		rootintro.setPadding(new Insets(50, 10, 50, 10));
		rootintro.setSpacing(50);
		
		//on rajoute des effets graphiques lorsque la souris passe sur les diff�rents labels
		label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label1.setScaleX(1.5);
				label1.setScaleY(1.5);
			}
		});
		label1.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label1.setScaleX(1);
				label1.setScaleY(1);
			}
		});label2.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label2.setScaleX(1.5);
				label2.setScaleY(1.5);
			}
		});
		label2.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label2.setScaleX(1);
				label2.setScaleY(1);
			}
		});label3.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label3.setScaleX(1.5);
				label3.setScaleY(1.5);
			}
		});
		label3.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent e) {
				label3.setScaleX(1);
				label3.setScaleY(1);
			}
		});
				
		
		Scene sceneintro = new Scene(rootintro, WIDTH,HEIGHT);
	
		VBox rootpseudo = new VBox();
		rootpseudo.setPrefSize(WIDTH,HEIGHT);
		TextField pseudo = new TextField();
		Label question = new Label("Quel est votre pseudo ?"); question.setFont(Font.font("Supernatural Knight", 15));question.setTextFill(Color.rgb(0, 0, 0));
		Button launch = new Button("jouer"); launch.setTranslateX(280);
		launch.setOnAction(e -> {
			infos.pseudo=pseudo.getText();
			stage.setScene(sceneintro);
			mediamanager.playSound("Select.mp3");
		});
		rootpseudo.getChildren().addAll(pseudo,question,launch);
		Scene scenepseudo = new Scene(rootpseudo,WIDTH,HEIGHT);
		rootpseudo.setPadding(new Insets(50, 10, 50, 10));
		rootpseudo.setSpacing(20);
		Image pseudoim = new Image ("pseudo.jpg",680,800, true,true);
		BackgroundImage pseudobg= new BackgroundImage(pseudoim ,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		rootpseudo.setBackground(new Background(pseudobg));

		stage.setScene(scenepseudo);
		stage.show();
		stage.setResizable(false);

		//on cr�e le groupe correspondant � la sc�ne de jeu et on lui ajoute les �l�ments de gestion graphique
		Group root = new Group();
		Scene scene = new Scene(root);
		Canvas canvas = new Canvas(WIDTH, HEIGHT);		
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//on initialise les diff�rents �l�ments du menu de score et le groupe les contenants
				VBox scorebox = new VBox();
				scorebox.setPrefSize(WIDTH, HEIGHT);
				Button retour = new Button("retour"); button1.setStyle("-fx-font-size: 30;-fx-base: #b5e7c2;");
				retour.setOnAction(e -> {
					stage.setScene(sceneintro);
					mediamanager.playSound("Select.mp3");
				});
				retour.setTranslateY(-35);
				Text classement = new Text("Top5"); classement.setTranslateX(250); classement.setFill(Color.BLACK); classement.setFont(Font.font("Cambria", FontWeight.BOLD, 45));				
				scorebox.getChildren().addAll(retour,classement);
				scorebox.setBackground(new Background(myBI));
				scorebox.setPadding(new Insets(50, 10, 50, 10));
				scorebox.setSpacing(50);
				Scene scenescore = new Scene(scorebox, WIDTH, HEIGHT);

		//On donne les informations au gestionnaire de jeu
		infos.init=init;
		infos.stage=stage;
		infos.canvas=canvas;
		infos.groupe = root;
		infos.musicmanager = mediamanager;


		stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			switch (event.getCode()) {
			case LEFT:
				for(Battant b : init.listeBattant) {
					if (b.gauche) {
						b.releaseLeft();
					}
				}
				break;
			case RIGHT:
				for(Battant b : init.listeBattant) {
					if (b.gauche==false) {
						b.releaseRight();
					}
				}
				break;
			case SPACE:
				for (Balle balle : init.listeBalle) {
					for (Launcher lanceur : init.listeLauncher){
						lanceur.launch(balle);
					}
				}
				break;
			default:
				break;
			}});

		//on initialise les diff�rents �l�ments du menu de game over et le groupe les contenants
		VBox rootfinal = new VBox();
		rootfinal.setPrefSize(WIDTH, HEIGHT);
		Button restart = new Button("Retour Menu",new ImageView(new Image("Champignon1.png", 50,50,false,false))); restart.setStyle("-fx-font-size: 30;-fx-base: #b7e7c2;");
		Button quit = new Button("Quitter",new ImageView(new Image("Champignon1.png", 50,50,false,false))); quit.setStyle("-fx-font-size: 30;-fx-base: #b7e7c2;");
		Button scores = new Button("Scores",new ImageView(new Image("Champignon1.png", 50,50,false,false))); scores.setStyle("-fx-font-size: 30;-fx-base: #b7e7c2;");
		restart.setMaxSize(680, 400); quit.setMaxSize(680, 400); scores.setMaxSize(680,400);
		Text textfinal = new Text("Game Over"); textfinal.setTranslateX(200); textfinal.setFill(Color.BLACK); textfinal.setFont(Font.font("Cambria", FontWeight.BOLD, 45));

		rootfinal.getChildren().addAll(textfinal, restart, quit,scores);
		rootfinal.setBackground(new Background(myBI));
		rootfinal.setPadding(new Insets(50, 10, 50, 10));
		rootfinal.setSpacing(50);
		Scene scenefinal = new Scene(rootfinal, WIDTH, HEIGHT);

		//on d�finit les actions des bouttons du menu de game over
		restart.setOnAction(new EventHandler<ActionEvent>() {  
			@Override 
			public void handle(ActionEvent actionEvent) {
				stage.setScene(sceneintro);

			}
		});

		quit.setOnAction(new EventHandler<ActionEvent>() {  
			@Override 
			public void handle(ActionEvent actionEvent) {
				stage.close();
				quit.setCancelButton(true);
			};
		});

		scores.setOnAction(new EventHandler<ActionEvent>() {  
			@Override
			public void handle(ActionEvent actionEvent) {
				stage.close();
				try {
					scoremanager.organise();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				scoremanager.createclassement();
				infos.score1.setFont(Font.font("Supernatural Knight", 30)); infos.score1.setTextFill(Color.rgb(255, 255, 255)); infos.score1.setTranslateX(250);
				infos.score2.setFont(Font.font("Supernatural Knight", 30)); infos.score2.setTextFill(Color.rgb(255, 255, 255)); infos.score2.setTranslateX(250);
				infos.score3.setFont(Font.font("Supernatural Knight", 30)); infos.score3.setTextFill(Color.rgb(255, 255, 255)); infos.score3.setTranslateX(250);
				infos.score4.setFont(Font.font("Supernatural Knight", 30)); infos.score4.setTextFill(Color.rgb(255, 255, 255)); infos.score4.setTranslateX(250);
				infos.score5.setFont(Font.font("Supernatural Knight", 30)); infos.score5.setTextFill(Color.rgb(255, 255, 255)); infos.score5.setTranslateX(250);
				scorebox.getChildren().addAll(infos.score1, infos.score2, infos.score3, infos.score4, infos.score5);
				stage.close();
				stage.setScene(scenescore);
				stage.show();
				stage.setResizable(false);
			}
		});	

		//boucle infinie � la base du programme     
		AnimationTimer boucle = new AnimationTimer() {      
			public void handle(long arg0) {
				Status etat  = mediamanager.mediaplayer.getStatus();//on regarde si la musique est deja en cours
				System.out.println(etat);
				if (etat == Status.PAUSED)
				{
				mediamanager.playSound("BGmusic.mp3");
				}
				Image univers = infos.bg;//on r�cup�re l'image de fond associ�e au niveau
				gc.drawImage(univers, 0, 0);
				for (Balle balle : init.listeBalle) {

					for(Obstacle o : init.listeObstacle) {//on parcourt la liste d'obstacle
						if (o != null) {
							o.draw(gc, root);//on les affiche
						}
					}
					for(Obstacle o : init.listeObstacle) {//on v�rifie si la balle les collisionne
						if (o != null) {
							if (o.getBandes() != null) {
								for(Bande b : o.getBandes()) {
									balle.checkCollision(b,infos);
								}
							}
						}
					}
					balle.increaseBounce(); //on augmente le compteur de la balle
					for(Battant b : init.listeBattant) {
						balle.checkCollision(b.hitbox, infos);//on v�rifie les collisions avec les battants
					}

					//on v�rifie si la balle ne tombe hors jeu
					if (balle.y > 790) {
						this.stop(); 
						stage.close();
						stage.setScene(scenefinal);
						stage.show();
						stage.setResizable(false);
						mediamanager.mediaplayer.pause();
						mediamanager.playSound("Select.mp3");
					}

					balle.updateSpeed();//mise � jour des propri�t�s m�caniques
					balle.updatePosition();
					balle.render(gc);

					gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 24)); //on affiche le score
					gc.setFill(Color.ANTIQUEWHITE);
					gc.setStroke(Color.ANTIQUEWHITE);
					gc.setLineWidth(1);
					String txt = "Score: " + infos.score;
					gc.fillText(txt, 520, 36 );
					gc.strokeText(txt, 520, 36 );
					//gc.strokeLine(balle.batd.hitbox.p1.x, balle.batd.hitbox.p1.y, balle.batd.hitbox.p2.x ,balle.batd.hitbox.p2.y);
					//gc.strokeLine(balle.batg.hitbox.p1.x, balle.batg.hitbox.p1.y, balle.batg.hitbox.p2.x ,balle.batg.hitbox.p2.y);
					gc.stroke();
				}
			}
		};
		

		//on met en place un listener pour detecter quand le joueur appuie et relache les fl�ches de gauche et de droite
		//on active es battants en cons�quence
		stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
			case LEFT:
				for(Battant b : init.listeBattant) {
					if (b.gauche) {
						b.pressLeft();
					}
				}
				break;
			case RIGHT:
				for(Battant b : init.listeBattant) {
					if (b.gauche ==false) {
						b.pressRight();
					}
				}
				break;
			case SPACE:
				for (Launcher lanceur : init.listeLauncher){
					lanceur.accumulate();
				}
				break;
			case A:// temporaire pour tester le changement de niveau
				root.getChildren().clear();
				root.getChildren().add(canvas);
				infos.choixniveau = "donjon.txt";
				infos.changeLevel(root,"2");
				break;
			case ESCAPE:
				boucle.stop(); 
				stage.close();
				stage.setScene(scenefinal);
				stage.show();
				stage.setResizable(false);
				mediamanager.mediaplayer.pause();
				mediamanager.playSound("Select.mp3");
			default:
				break;
			}});


		//bouton 1 -> niveau 1
		button1.setOnAction(new EventHandler<ActionEvent>() {  
			@Override 
			public void handle(ActionEvent actionEvent) {
				infos.timer=boucle;
				stage.close();
				root.getChildren().clear();
				root.getChildren().add(canvas);
				infos.choixniveau = "Brosseliandre.txt";
				infos.changeLevel(root,"1");
				boucle.start();
				stage.setScene(scene);
				stage.show();
				mediamanager.mediaplayer.pause();
			};
		});

		//bouton 2 -> niveau 2
		button2.setOnAction(new EventHandler<ActionEvent>() {  
			@Override 
			public void handle(ActionEvent actionEvent) {
				infos.timer=boucle;
				stage.close();
				root.getChildren().clear();
				root.getChildren().add(canvas);
				infos.choixniveau = "chateau.txt";
				infos.changeLevel(root,"2");
				boucle.start();
				stage.setScene(scene);
				stage.show();
				mediamanager.mediaplayer.pause();
			};
		});

		//bouton 3 -> niveau 3
		button3.setOnAction(new EventHandler<ActionEvent>() {  
			@Override 
			public void handle(ActionEvent actionEvent) {
				Editor editor = new Editor(root,infos);
				infos.numniveau="1";
				infos.timer = boucle;
				stage.close();
				VBox rootperso = new VBox();
				rootperso.setPrefSize(WIDTH, HEIGHT);
				TextField tf = new TextField("nom de niveau");
				Button type1 = new Button ("Forêt", new ImageView(new Image("bg1.png", 50,50,true,false))); type1.setTranslateX(350);
				Button type2 = new Button ("Donjon", new ImageView(new Image("bg2.png", 50,50,true,false))); type2.setTranslateX(150);type2.setTranslateY(-75);
				type1.setOnAction(e -> {
					infos.numniveau="1";
					});
				type2.setOnAction(e -> {
					infos.numniveau="2";
					});
				Button create = new Button("Créer ma table de jeu"); create.setTranslateX(260);
				create.setOnAction(e -> {
					root.getChildren().clear();
					root.getChildren().add(canvas);
					try {
						
						editor.createmap(tf.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String tit = tf.getText();
					stage.close();
					stage.setScene(scene);
					stage.show();
					gc.drawImage(new Image("bg"+infos.numniveau+".png",680,800,false,false),0,0);
					infos.tit=tit;
					infos.choixniveau =infos.tit + ".txt";
					infos.gc=gc;
					editor.editing(infos);
					BufferedWriter niveauwriter = null;
					try {
						niveauwriter = new BufferedWriter(new FileWriter("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/listeniveaux.txt", true));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						niveauwriter.write("\n"+ infos.tit);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						niveauwriter.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				rootperso.setPadding(new Insets(50, 10, 50, 10));
				rootperso.setSpacing(20);
				BufferedReader lire = null;
				try {
					lire = new BufferedReader(new FileReader("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/listeniveaux.txt"));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String line = null;
				Button temp = null;
			    ArrayList<Button> listebouton = new  ArrayList<Button>();
				try {
					line = lire.readLine();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (line != null) { //Tant qu'il y a des lignes dans le fichier
					System.out.println(line);
					temp = new Button(line);
					listebouton.add(temp);
					try {
						line = lire.readLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				rootperso.getChildren().addAll(tf,create,type1,type2);
				for (Button b : listebouton) {
					if (b.getText() != "") {
					b.setOnAction(new EventHandler<ActionEvent>() {  
						@Override 
						public void handle(ActionEvent actionEvent) {
							root.getChildren().clear();
							root.getChildren().add(canvas);
							infos.timer=boucle;
							stage.close();
							infos.choixniveau = b.getText()+ ".txt";
							infos.changeLevel(root,"1");
							boucle.start();
							stage.setScene(scene);
							stage.show();
						};
					});
					rootperso.getChildren().add(b);
				}
				}
				Scene sceneperso = new Scene(rootperso, WIDTH,HEIGHT);
				Image univers = new Image("creation.png", WIDTH, HEIGHT, true, false);
				BackgroundImage myBI= new BackgroundImage(univers,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
				rootperso.setBackground(new Background(myBI));
				stage.setScene(sceneperso);
				stage.show();
				stage.setResizable(false);
			}});
	};
;
};;
