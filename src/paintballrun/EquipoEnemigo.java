package paintballrun;

import javafx.scene.layout.Pane;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EquipoEnemigo {
	
	private Enemigo enemigos[];
	private int position[];

	public EquipoEnemigo(int n,Muro[] muroCancha) throws MalformedURLException {
		enemigos = new Enemigo[n];
		File file = new File("resources/enemy.png");
		String localUrl = file.toURI().toURL().toString();
		Image img = new Image(localUrl);

		position = new int[enemigos.length];

		for (int i = 0; i < enemigos.length; i++) {
			position[i] = positionRandomizer();
			for (int l = 0; l < i; l++) {
				if (position[l] == position[i]) {
					i--;
				}
			}

		}

		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i] = new Enemigo(position[i],muroCancha);
			enemigos[i].setFill(new ImagePattern(img));
		}
	}

	public void respawn() {
		for (int i = 0; i < enemigos.length; i++) {
			position[i] = positionRandomizer();
			for (int l = 0; l < i; l++) {
				if (position[l] == position[i]) {
					i--;
				}
			}
		}
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i].setPosition(position[i]);
		}
	}

	public int positionRandomizer() {
		Random r = new Random();
		int randomPosition = r.nextInt(12);
		return randomPosition;
	}

	public void addPanel(Pane paneCancha, Bala[] balaCancha) throws MalformedURLException {
		for (int i = 0; i < enemigos.length; i++) {
			paneCancha.getChildren().add(enemigos[i]);
			enemigos[i].crearBala(paneCancha, balaCancha,i);
		}
	}

	public void disparar() {

		for (int i = 0; i < enemigos.length; i++) {
			for(int delay =Integer.MIN_VALUE;delay<Integer.MAX_VALUE;delay++);
			enemigos[i].disparar();
		}
	}

	public void angulotiempo(double posx, double posy) {
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i].calcularAngulo(posx, posy);
		}
	}

}
