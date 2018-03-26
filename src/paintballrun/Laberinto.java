package paintballrun;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;

public class Laberinto {
	private Muro[] muros;
	private int posicion[];
	Random r = new Random();
	
	public Laberinto(Pane paneCancha,int n,Muro[] muroCancha) throws MalformedURLException {
		muros = new Muro[n];
		File fileM = new File("resources/muro.jpg");
		String localUrlM = fileM.toURI().toURL().toString();
		Image imgmuro = new Image(localUrlM);

		posicion = new int[muros.length];
		

		for (int i = 0; i < muros.length; i++) {
			int rand =r.nextInt(14);
			posicion[i] =  rand;
			
			for (int j = 0; j < i; j++) {
				
				if (posicion[j] == posicion[i]) {
					i--;
				}
			}
		}
		for (int i = 0; i < muros.length; i++) {
			muros[i] = new Muro((posicion[i]));
			System.out.println(posicion[i]);
			muros[i].setFill(new ImagePattern(imgmuro));
			paneCancha.getChildren().add(muros[i]);
			muroCancha[i] = muros[i];
		}
	}
	public void respawn() {
		for (int i = 0; i < muros.length; i++) {
			posicion[i] =  r.nextInt(9);
			for (int l = 0; l < i; l++) {
				if (posicion[l] == posicion[i]) {
					i--;
				}
			}
		}
		for (int i = 0; i < muros.length; i++) {
			muros[i].setPosition(posicion[i]);
		}
	}

}
