package tsp.flipper;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Trapball extends Obstacle{
	
	private int xinit;
	private int yinit;
	private Bande_Active b1;
	private Bande_Active b2;
	private Bande_Active b3;
	private Bande_Active b4;
	private Bande_Active b5;
	private Bande_Active b6;
	private Image image;
	private int width;
	private int height;
	private Point localisation;

	public Trapball(String path,int x, int y) {
		super(x, y);
		xinit=x;
		yinit=y;
		localisation = new Point(x,y);
		width = 100;
		height = 100;
		image = new Image(path, width, height, true, false);
		Point p1= new Point(x+width/4,y);
		Point p2= new Point(x+3*width/4,y);
		Point p3= new Point(x+width,y+height/2);
		Point p4= new Point(x+3*width/4,y+height);
		Point p5= new Point(x+width/4,y+height);
		Point p6= new Point(x,y+height/2);
		b1 = new Bande_Active (p1,p2);
		b2 = new Bande_Active (p2,p3);
		b3 = new Bande_Active (p3,p4);
		b4 = new Bande_Active (p4,p5);
		b5 = new Bande_Active (p5,p6);
		b6 = new Bande_Active (p6,p1);
		Timeline animation = new Timeline(
				new KeyFrame(Duration.seconds(5), e -> refresh())
);
		animation.setCycleCount(animation.INDEFINITE);
		animation.play();
		
		AnimationTimer moveanimation = new AnimationTimer() {
			public void handle(long arg0) {
				b1.p1.x +=1;
				b1.p2.x +=1;
				b2.p1.x +=1;
				b2.p2.x +=1;
				b3.p1.x +=1;
				b3.p2.x +=1;
				b4.p1.x +=1;
				b4.p2.x +=1;
				b5.p1.x +=1;
				b5.p2.x +=1;
				b6.p1.x +=1;
				b6.p2.x +=1;
				localisation.x+=2;
			}
		};
		moveanimation.start();
	}
		private Object refresh() {
		// TODO Auto-generated method stub
			localisation.x = xinit;
			localisation.y = yinit;
			b1.p1.x=xinit+width/4;
			b1.p1.y=yinit;
			b1.p2.x=xinit+3*width/4;
			b1.p2.y=yinit;
			b2.p1.x=xinit+3*width/4;
			b2.p1.y=yinit;
			b2.p2.x=xinit+width;
			b2.p2.y=yinit+height/2;
			b3.p1.x=xinit+width;
			b3.p1.y=yinit+height/2;
			b3.p2.x=xinit+3*width/4;
			b3.p2.y=yinit+height;
			b4.p1.x=xinit+3*width/4;
			b4.p1.y=yinit+height;
			b4.p2.x=xinit+width/4;
			b4.p2.y=yinit+height;
			b5.p1.x=xinit+width/4;
			b5.p1.y=yinit+height;
			b5.p2.x=xinit;
			b5.p2.y=yinit+height/2;
			b6.p1.x=xinit;
			b6.p1.y=yinit+height/2;
			b6.p2.x=xinit+width/4;
			b6.p2.y=yinit;
		return null;
	}
		
	@Override
	public void draw(GraphicsContext gc, Group g) {
		gc.drawImage(image,localisation.x,localisation.y);
		gc.strokeLine(b1.p1.x, b1.p1.y, b1.p2.x, b1.p2.y);
	}
	
	//retourne la liste de bandes associées à cet obstacle
	public Bande[] getBandes() {
		Bande[] listebandes ={this.b1,this.b2,this.b3,this.b4,b5,b6};
		return listebandes;
	}

}
