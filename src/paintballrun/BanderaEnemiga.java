package paintballrun;

import java.util.Random;
import javafx.scene.shape.Circle;

public class BanderaEnemiga extends Circle {
	private Random r;

	public BanderaEnemiga() {

		super();
		r = new Random();
		this.setCenterX(r.nextInt(330) + 50);
		this.setCenterY(45);
		this.setRadius(40);
	}

	public void respawn() {
		this.setCenterX(r.nextInt(330) + 50);
		this.setCenterY(45);
	}
}
