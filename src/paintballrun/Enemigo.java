package paintballrun;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Enemigo extends Circle {
	private Muro[] muroCancha;
	private Bala bala;
	private double x, y;
	private int[] positionx = { 50, 150, 400, 230, 350, 50, 170, 350, 280, 170, 270, 280, 110 };
	private int[] positiony = { 180, 190, 130, 250, 250, 350, 330, 370, 180, 130, 120, 290, 300 };

	public Enemigo(int random, Muro[] muroCancha) {

		super();
		this.muroCancha=muroCancha;
		this.setCenterX(positionx[random]);
		this.setCenterY(positiony[random]);
		this.setRadius(15);
	}

	public void setPosition(int random) {
		this.setCenterX(positionx[random]);
		this.setCenterY(positiony[random]);
	}

	public void crearBala(Pane paneCancha, Bala[] balaCancha,int n) throws MalformedURLException {
		bala = new Bala(this.getCenterX(), this.getCenterY());
		File file = new File("resources/orb.gif");
		String localUrl = file.toURI().toURL().toString();
		Image img = new Image(localUrl);
		bala.setFill(new ImagePattern(img));
		paneCancha.getChildren().add(bala);
		balaCancha[n]=bala;
	
	}

	public void calcularAngulo(double posx, double posy) {
		x = posx;
		y = posy;
		int angulo = (int) (Math.toDegrees(Math.atan2((posy - this.getCenterY()), (posx - this.getCenterX()))));
		if (bala.getCenterX() == this.getCenterX() || bala.getCenterX() == this.getCenterX())
			bala.setAngulo(angulo);
	}

	public boolean impacto() {

		if (bala.getCenterX() > 490 || bala.getCenterX() < 10) {
			return true;
		}

		for (int i = 0; i < muroCancha.length; i++) {
			long distanciaX = Math.abs(Math.round(bala.getCenterX() - (muroCancha[i].getX()+20)));
			long distanciaY = Math.abs(Math.round(bala.getCenterY() - (muroCancha[i].getY()+20)));

			if (distanciaX < 25 && distanciaY < 25) {

				return true;
			}
		}

		if (bala.getCenterX() < x + 20 && bala.getCenterX() > x - 20 && bala.getCenterY() < y + 20
				&& bala.getCenterY() > y - 20) {
			return true;
		}

		if (bala.getCenterY() > 440 || bala.getCenterY() < 10) {
			return true;
		}

		else {
			return false;
		}
	}

	public void disparar() {
		if (impacto()) {
			
			bala.setCenterX(this.getCenterX());
			bala.setCenterY(this.getCenterY());
		} else {
			bala.mover();
		}
	}
}
