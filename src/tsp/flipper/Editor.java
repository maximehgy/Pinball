package tsp.flipper;



import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Editor {

	public ArrayList<String> lines;
	public Path chemin;
	public Group groupe;
	public GameInfo infos;

	public Editor (Group g, GameInfo gi) {
		this.groupe=g;
		this.infos = gi;
	}

	public void createmap(String path) throws IOException {
		lines = new ArrayList<String>();
		if(infos.numniveau=="2") {
			lines.add("Type ");
		}
		lines.add("Lau 610 650 ");
		lines.add("Bal 640 680 0 0 0 680 800 ");
		lines.add("Rp 600 224 ");
		lines.add("M 12 0 12 560 ");
		lines.add("M 0 545 165 680 ");
		lines.add("MR 588 120 588 530 -1 0 ");
		lines.add("M 588 520 588 700 ");
		lines.add("M 590 520 390 680 ");
		lines.add("BD 390 680 ");
		lines.add("BG 160 682 ");
		chemin = Paths.get("/Users/maximeheguy/Documents/flipperfinal/pro3600-20-ran-1/ressources/"+path+".txt");
		Files.write(chemin,lines, StandardCharsets.UTF_8);
	}

	public void editing (GameInfo informations) {
		Button launch = new Button("enregistrer et jouer"); launch.setTranslateX(560);
		launch.setOnAction(e -> {
			informations.groupe.getChildren().clear();
			informations.groupe.getChildren().add(informations.canvas);
			informations.changeLevel( groupe, informations.numniveau);	
			informations.musicmanager.mediaplayer.pause();
		});
		informations.groupe.getChildren().addAll(launch);
		informations.gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		informations.gc.setFill(Color.BISQUE);
		informations.gc.setStroke(Color.RED);
		informations.gc.setLineWidth(1);
		String txt = "Champignons";
		informations.gc.fillText(txt, 20, 700 );
		informations.gc.strokeText(txt,20, 700 );

		Battant bg = new Battant("battant.png",new Point (160,682), true);
		Battant bd = new Battant("battantdroit.png",new Point (390,680), false);
		this.groupe.getChildren().add(bg.imageview);
		this.groupe.getChildren().add(bd.imageview);
		Mur m1r = new Mur(12,0,12,560,"Mur" + informations.numniveau + ".png");
		Mur m2r = new Mur(0,545,165,680,"Mur" + informations.numniveau + ".png");
		Mur m3r = new Mur(588,520,588,700,"Mur" + informations.numniveau + ".png");
		Mur m4r = new Mur(590, 520, 390, 680, "Mur" + informations.numniveau + ".png");
   		Mur_Reversible m5r = new Mur_Reversible (588,120,588,530,"reversible"+informations.numniveau+".png",-1,0);
		m1r.draw(informations.gc, informations.groupe);
		m2r.draw(informations.gc, informations.groupe);
		m3r.draw(informations.gc, informations.groupe);
		m4r.draw(informations.gc, informations.groupe);
		m5r.draw(informations.gc, informations.groupe);
		Rampe_lancement rampe = new Rampe_lancement("rampe"+informations.numniveau+".png",600,224 );
		rampe.draw(informations.gc, informations.groupe);

		Image champi = new Image("Champignon" + informations.numniveau +".png",80,80,true,false);
		ImageView champiv = new ImageView(champi);
		champiv.setTranslateX(40);
		champiv.setTranslateY(710);
		informations.groupe.getChildren().add(champiv);
		champiv.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				//the event will be passed only to the circle which is on front
				me.consume();
				ImageView champi2v = new ImageView(champi);
				champi2v.setTranslateX(10);
				champi2v.setTranslateY(10);
				informations.groupe.getChildren().addAll(champi2v);
				champi2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						me.consume();
					}
				});
				
				champi2v.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						//when mouse is pressed, store initial position
						Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


						champi2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
							public void handle(MouseEvent me) {
								double dragX =me.getSceneX() - dragAnchor.x;
								double dragY =me.getSceneY() - dragAnchor.y;
								//calculate new position of the circle
								double newXPosition = dragX;
								double newYPosition = dragY;
								//if new position do not exceeds borders of the rectangle, translate to this positio
								champi2v.setTranslateX(newXPosition);
								champi2v.setTranslateY(newYPosition);
								champi2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
									public void handle(MouseEvent me) {
										double x= dragX;
										double y= dragY;
										ImageView champif = new ImageView(champi);
										informations.groupe.getChildren().removeAll(champi2v);
										champif.setTranslateX(champi2v.getTranslateX());
										champif.setTranslateY(champi2v.getTranslateY());
										informations.groupe.getChildren().addAll(champif);
										lines.add("C " + String.valueOf((int)Math.floor(x+45)) +" "+ String.valueOf((int)Math.floor(y+20))+ " 1 10 ");
										try {
											Files.write(chemin,lines, StandardCharsets.UTF_8);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								});
							} });
					}
				});
			} });
	String txt1 = "Battants";
	informations.gc.fillText(txt1, 200, 700 );
	informations.gc.strokeText(txt1,200, 700 );
	Image battantg = new Image("battant"+".png",95,47,false,false);
	ImageView battantgv = new ImageView(battantg);
	battantgv.setTranslateX(150);
	battantgv.setTranslateY(710);
	informations.groupe.getChildren().add(battantgv);
	battantgv.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			//the event will be passed only to the circle which is on front
			me.consume();
			ImageView battantg2v = new ImageView(battantg);
			battantg2v.setTranslateX(10);
			battantg2v.setTranslateY(10);
			informations.groupe.getChildren().addAll(battantg2v);
			battantg2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					me.consume();
				}
			});
			
			battantg2v.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					//when mouse is pressed, store initial position
					Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


					battantg2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me) {
							double dragX =me.getSceneX() - dragAnchor.x;
							double dragY =me.getSceneY() - dragAnchor.y;
							//calculate new position of the circle
							double newXPosition = dragX;
							double newYPosition = dragY;
							//if new position do not exceeds borders of the rectangle, translate to this positio
							battantg2v.setTranslateX(newXPosition);
							battantg2v.setTranslateY(newYPosition);
							battantg2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									double x= dragX;
									double y= dragY;
									ImageView battantgf = new ImageView(battantg);
									informations.groupe.getChildren().removeAll(battantg2v);
									battantgf.setTranslateX(battantg2v.getTranslateX());
									battantgf.setTranslateY(battantg2v.getTranslateY());
									informations.groupe.getChildren().addAll(battantgf);
									lines.add("BG " + String.valueOf((int)Math.floor(x+10)) +" "+ String.valueOf((int)Math.floor(y+10))+" ");
									try {
										Files.write(chemin,lines, StandardCharsets.UTF_8);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} });
				}
			});
		};
	});

	Image battantd = new Image("battantdroit"+".png",95,47,false,false);
	ImageView battantdv = new ImageView(battantd);
	battantdv.setTranslateX(250);
	battantdv.setTranslateY(710);
	informations.groupe.getChildren().add(battantdv);
	battantdv.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			//the event will be passed only to the circle which is on front
			me.consume();
			ImageView battantd2v = new ImageView(battantd);
			battantd2v.setTranslateX(10);
			battantd2v.setTranslateY(10);
			informations.groupe.getChildren().addAll(battantd2v);
			battantd2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					me.consume();
				}
			});
			
			battantd2v.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					//when mouse is pressed, store initial position
					Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


					battantd2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me) {
							double dragX =me.getSceneX() - dragAnchor.x;
							double dragY =me.getSceneY() - dragAnchor.y;
							//calculate new position of the circle
							double newXPosition = dragX;
							double newYPosition = dragY;
							//if new position do not exceeds borders of the rectangle, translate to this positio
							battantd2v.setTranslateX(newXPosition);
							battantd2v.setTranslateY(newYPosition);
							battantd2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									double x= dragX;
									double y= dragY;
									ImageView battantdf = new ImageView(battantd);
									informations.groupe.getChildren().removeAll(battantd2v);
									battantdf.setTranslateX(battantd2v.getTranslateX());
									battantdf.setTranslateY(battantd2v.getTranslateY());
									informations.groupe.getChildren().addAll(battantdf);
									lines.add("BD " + String.valueOf((int)Math.floor(x+77)) +" "+ String.valueOf((int)Math.floor(y+10))+" ");
									try {
										Files.write(chemin,lines, StandardCharsets.UTF_8);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} });
				}
			});
		};
	});
	String txt2 = "Cible";
	informations.gc.fillText(txt2, 500, 700 );
	informations.gc.strokeText(txt2,500, 700 );
	Image cible = new Image("Cible" + informations.numniveau +".png",50,50,false,false);
	ImageView ciblev = new ImageView(cible);
	ciblev.setTranslateX(500);
	ciblev.setTranslateY(710);
	informations.groupe.getChildren().add(ciblev);
	ciblev.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			//the event will be passed only to the circle which is on front
			me.consume();
			ImageView cible2v = new ImageView(cible);
			cible2v.setTranslateX(10);
			cible2v.setTranslateY(10);
			informations.groupe.getChildren().addAll(cible2v);
			cible2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					me.consume();
				}
			});
			
			cible2v.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					//when mouse is pressed, store initial position
					Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


					cible2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me) {
							double dragX =me.getSceneX() - dragAnchor.x;
							double dragY =me.getSceneY() - dragAnchor.y;
							//calculate new position of the circle
							double newXPosition = dragX;
							double newYPosition = dragY;
							//if new position do not exceeds borders of the rectangle, translate to this positio
							cible2v.setTranslateX(newXPosition);
							cible2v.setTranslateY(newYPosition);
							cible2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									double x= dragX;
									double y= dragY;
									ImageView ciblef = new ImageView(cible);
									informations.groupe.getChildren().removeAll(cible2v);
									ciblef.setTranslateX(cible2v.getTranslateX());
									ciblef.setTranslateY(cible2v.getTranslateY());
									informations.groupe.getChildren().addAll(ciblef);
									lines.add("T " + String.valueOf((int)Math.floor(x+10)) +" "+ String.valueOf((int)Math.floor(y+10))+" ");
									try {
										Files.write(chemin,lines, StandardCharsets.UTF_8);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} });
				}
			});
		};
	});
	String txt3 = "tunnel";
	informations.gc.fillText(txt3, 400, 580 );
	informations.gc.strokeText(txt3,400, 580 );
	Image tunnel = new Image("tunnel"+informations.numniveau +".png",90,230,true,false);
	ImageView tunnelv = new ImageView(tunnel);
	tunnelv.setTranslateX(400);
	tunnelv.setTranslateY(600);
	informations.groupe.getChildren().add(tunnelv);
	tunnelv.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			//the event will be passed only to the circle which is on front
			me.consume();
			ImageView tunnel2v = new ImageView(tunnel);
			tunnel2v.setTranslateX(10);
			tunnel2v.setTranslateY(10);
			informations.groupe.getChildren().addAll(tunnel2v);
			tunnel2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					me.consume();
				}
			});
			
			tunnel2v.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					//when mouse is pressed, store initial position
					Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


					tunnel2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me) {
							double dragX =me.getSceneX() - dragAnchor.x;
							double dragY =me.getSceneY() - dragAnchor.y;
							//calculate new position of the circle
							double newXPosition = dragX;
							double newYPosition = dragY;
							//if new position do not exceeds borders of the rectangle, translate to this positio
							tunnel2v.setTranslateX(newXPosition);
							tunnel2v.setTranslateY(newYPosition);
							tunnel2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									double x= dragX;
									double y= dragY;
									ImageView tunnelf = new ImageView(tunnel);
									informations.groupe.getChildren().removeAll(tunnel2v);
									tunnelf.setTranslateX(tunnel2v.getTranslateX());
									tunnelf.setTranslateY(tunnel2v.getTranslateY());
									informations.groupe.getChildren().addAll(tunnelf);
									lines.add("R " + String.valueOf((int)Math.floor(x+10)) +" "+ String.valueOf((int)Math.floor(y+10))+" ");
									try {
										Files.write(chemin,lines, StandardCharsets.UTF_8);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} });
				}
			});
		};
	});
	String txt4 = "tournant";
	informations.gc.fillText(txt4, 555, 710 );
	informations.gc.strokeText(txt4,555, 710 );
	Image tournant = new Image("tournant"+informations.numniveau +".png",140,30,true,false);
	ImageView tournantv = new ImageView(tournant);
	tournantv.setTranslateX(550);
	tournantv.setTranslateY(740);
	informations.groupe.getChildren().add(tournantv);
	tournantv.setOnMouseClicked(new EventHandler<MouseEvent>() {
		public void handle(MouseEvent me) {
			//the event will be passed only to the circle which is on front
			me.consume();
			ImageView tournant2v = new ImageView(tournant);
			tournant2v.setTranslateX(10);
			tournant2v.setTranslateY(10);
			informations.groupe.getChildren().addAll(tournant2v);
			tournant2v.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					me.consume();
				}
			});
			
			tournant2v.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent me) {
					//when mouse is pressed, store initial position
					Vector2Dposition dragAnchor = new Vector2Dposition((int)me.getX(),(int)me.getY());


					tournant2v.setOnMouseDragged(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent me) {
							double dragX =me.getSceneX() - dragAnchor.x;
							double dragY =me.getSceneY() - dragAnchor.y;
							//calculate new position of the circle
							double newXPosition = dragX;
							double newYPosition = dragY;
							//if new position do not exceeds borders of the rectangle, translate to this position
							tournant2v.setTranslateX(newXPosition);
							tournant2v.setTranslateY(newYPosition);
							tournant2v.setOnMouseReleased(new EventHandler<MouseEvent>() {
								public void handle(MouseEvent me) {
									double x= dragX;
									double y= dragY;
									ImageView tournantf = new ImageView(tournant);
									informations.groupe.getChildren().removeAll(tournant2v);
									tournantf.setTranslateX(tournant2v.getTranslateX());
									tournantf.setTranslateY(tournant2v.getTranslateY());
									informations.groupe.getChildren().addAll(tournantf);
									lines.add("PT " + String.valueOf((int)Math.floor(x+10)) +" "+ String.valueOf((int)Math.floor(y+10))+" ");
									try {
										Files.write(chemin,lines, StandardCharsets.UTF_8);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						} });
				}
			});
		};
	});
}
}