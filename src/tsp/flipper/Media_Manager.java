package tsp.flipper;


import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;


public class Media_Manager {
MediaPlayer mediaplayer;
Status etat = null;
Media musique;
Media fx;

//joue une musique
//on va la chercher dans le dossier ad�quat
//on la joue � l'infini
//on r�duit son volume
//on lance la musique
public void playSound(String path) {
	musique = new Media(Paths.get("ressources", "Audio", path).toUri().toString());
	mediaplayer = new MediaPlayer(musique);
	mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
	mediaplayer.setVolume(0.30);
	mediaplayer.play();

}

//permet de jouer un effet sp�cial en m�me temps
public void playFX(String path) {
	fx = new Media(Paths.get("ressources", "Audio", path).toUri().toString());
	mediaplayer = new MediaPlayer(fx);
	mediaplayer.play();
}

}
