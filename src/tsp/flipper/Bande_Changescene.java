package tsp.flipper;


public class Bande_Changescene extends Bande {
 private String niveau;
 private String num;

 
 	//CONSTRUCTEUR
	public Bande_Changescene(Point point1, Point point2,String niveau,String num) {
		super(point1, point2);
		// TODO Auto-generated constructor stub
		//on determine tous les paramètres de la balle pour notre prochaine scène
		this.niveau=niveau;
		this.num=num;
	}

	//si la balle est prête a rebondir
	 //si la balle est sur l'arrete
	//on efface les élements à l'écran
	//on réajoute le canvas pour réafficher des éléments
	//on change de niveau
	@Override
	public void collision(Balle b, GameInfo inf) {
		// TODO Auto-generated method stub
		if (b.readyBounce >this.seuil) { 
			if (Hitbox.onLine(this, b)) {
				inf.groupe.getChildren().clear();
				inf.groupe.getChildren().add(inf.canvas);
				inf.choixniveau= niveau+".txt";
				inf.interieur=true;
				inf.changeLevel(inf.groupe,num);
			}
		}
	}
}