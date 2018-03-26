package paintballrun;

import java.util.Random;
import javafx.scene.shape.Circle;

public class Jugador extends Circle {
	private Random r;

	public Jugador() {

		super();
		r = new Random();

		this.setCenterX(r.nextInt(380) + 30);
		this.setCenterY(475);
		this.setRadius(15);

	}

	public void respawn() {
			this.setCenterX(r.nextInt(380) + 30);
			this.setCenterY(475);
		}
	}

