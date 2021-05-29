package tsp.flipper;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class Mur_Reversible extends Obstacle{

	public Point p1;
	public Point p2;
	public Bande b1;
	public Image image;
	public ImageView imagev;
	private boolean notinscene;
	private int longueur;
	private int ratio;
	private int width;
	private int height;

	//CONSTRUCTEUR
	public Mur_Reversible( int x1, int y1, int x2, int y2, String path, int xpassage, int ypassage) {
		super(x1, y1);
		// TODO Auto-generated constructor stub
		width=32;
		height=32;
		//on crée une image de taille adéquate à partir d'une texture pixel par pixel
		longueur = (int) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		image = new Image(path,width,height,false,true);
		ratio = longueur/height;
		PixelReader reader = image.getPixelReader();
		WritableImage imagew = new WritableImage(width, longueur);
		PixelWriter writer = imagew.getPixelWriter();
		imagev = new ImageView (imagew);
		this.p1 = new Point (x1,y1);
		this.p2 = new Point (x2,y2);
		b1 = new Bande_Reversible (p1, p2,xpassage, ypassage);//la bande reversible ne laisse passer la balle que dans certaines directions
		notinscene =true;
			for(int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Color c = reader.getColor(i, j);
					for (int k = 1; k <= ratio; k++) {
					writer.setColor(i, j+(k-1)*height, c);

				}
			}
		}
		double angle = b1.support.getAngle();
		imagev.getTransforms().add(new Rotate(angle*180/Math.PI+90, 0, 0, 0, Rotate.Z_AXIS));
		imagev.setTranslateX(x1-16);
		imagev.setTranslateY(y1);

		
	}
	@Override
	//on ajoute le noeud grapgique au groupe
	public void draw(GraphicsContext gc, Group g) {
		if (notinscene) {
		g.getChildren().add(imagev);
		notinscene = false;
		}
		//gc.moveTo(p1.x,p1.y);
		//gc.lineTo(p2.x,p2.y);
	}
	
	//on retourne une bande
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1};
		return listebandes;
	}
}