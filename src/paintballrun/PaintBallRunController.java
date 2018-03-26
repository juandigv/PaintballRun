package paintballrun;

import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyEvent;

public class PaintBallRunController {

	@FXML
	private Pane paneCancha;
	@FXML
	private Pane paneGameOver;
	@FXML
	private Pane paneVictoria;
	@FXML
	private Label roundLabel;
	@FXML
	private Label lifesLabel;
	@FXML
	private Button botonSalir;
	@FXML
	private Button botonReiniciar;
	@FXML
	private Button modeButton;

	private AnimationTimerBala miTimer;
	private Jugador miJugador;
	private EquipoEnemigo misEnemigos;
	private BanderaEnemiga bandera;
	private Bala[] balaCancha;
	private Muro[] muroCancha;

	private Laberinto miLaberinto;
	private boolean unlimited = false;
	private boolean muro = true;

	public PaintBallRunController() {
		balaCancha = new Bala[5];
		muroCancha = new Muro[7];
	}

	@FXML
	public void initialize() throws MalformedURLException {
		Rectangle clip = new Rectangle(0, 0, 0, 0);
		clip.widthProperty().bind(paneCancha.widthProperty());
		clip.heightProperty().bind(paneCancha.heightProperty());
		paneCancha.setClip(clip);

		miJugador = new Jugador();
		bandera = new BanderaEnemiga();
		anadirimg();
		paneCancha.getChildren().addAll(bandera, miJugador);
		miLaberinto = new Laberinto(paneCancha, muroCancha.length, muroCancha);
		misEnemigos = new EquipoEnemigo(balaCancha.length, muroCancha);
		misEnemigos.addPanel(paneCancha, balaCancha);

		miTimer = new AnimationTimerBala(misEnemigos, miJugador, bandera, miLaberinto, balaCancha, paneGameOver,
				paneVictoria, roundLabel, lifesLabel);
		miTimer.start();

	}

	public void anadirimg() throws MalformedURLException {
		File fileP = new File("resources/player.png");
		String localUrlP = fileP.toURI().toURL().toString();
		Image imgplayer = new Image(localUrlP);
		miJugador.setFill(new ImagePattern(imgplayer));
		File fileB = new File("resources/bandera.png");
		String localUrlB = fileB.toURI().toURL().toString();
		Image imgbandera = new Image(localUrlB);
		bandera.setFill(new ImagePattern(imgbandera));

	}

	@FXML
	public void modeSelect() {
		if (unlimited) {
			modeButton.setText("5 RONDAS");
			miTimer.setRondas(5);

		} else {
			modeButton.setText("MODO INFINITO");
			miTimer.setRondas(999);
		}
		unlimited = !unlimited;
	}

	@FXML
	public void botonSalirClick() {
		System.exit(0);
	}

	@FXML
	public void botonReiniciarClick() {
		miTimer.reiniciar();
	}

	@FXML
	public void keyMoveHnd(KeyEvent key) {
		double x = miJugador.getCenterX();
		double y = miJugador.getCenterY();

		switch (key.getCode()) {
		case UP:
		case KP_UP:
		case I:
			if (y >= 20)
				if (muroarriba())
					y -= 5;
			break;
		case DOWN:
		case KP_DOWN:
		case K:
			if (y <= 480)
				 if (muroabajo())
				y += 5;
			break;
		case LEFT:
		case KP_LEFT:
		case J:
			if (x >= 20)
				 if (muroizquierda())
				x -= 5;
			break;
		case RIGHT:
		case KP_RIGHT:
		case L:
			if (x < 430)
				if (muroderecha())
				x += 5;
			break;
		default:
			System.out.println("KeyMoveHnd:" + key.getCode());
			break;
		}

		key.consume();

		miJugador.setCenterX(x);
		miJugador.setCenterY(y);
	}

	public boolean muroarriba() {
		for (int i = 0; i < muroCancha.length; i++) {
			muro = true;
			long distanciaX = Math.abs(Math.round(miJugador.getCenterX() - (muroCancha[i].getX() + 20)));
			long distanciaY = Math.abs(Math.round(miJugador.getCenterY() - (muroCancha[i].getY())));
			if (distanciaX < 30 && distanciaY < 55) {
				muro = false;
				break;
			}
		}
		return muro;
	}

	public boolean muroderecha() {
		for (int i = 0; i < muroCancha.length; i++) {
			muro = true;
			long distanciaX = Math.abs(Math.round((muroCancha[i].getX())-miJugador.getCenterX() ));
			long distanciaY = Math.abs(Math.round((muroCancha[i].getY() +20)-miJugador.getCenterY() ));
			if (distanciaX < 15 && distanciaY < 30) {
				muro = false;
				break;
			}
		}
		return muro;
	}

	public boolean muroabajo() {
		for (int i = 0; i < muroCancha.length; i++) {
			muro = true;
			long distanciaX = Math.abs(Math.round((muroCancha[i].getX() + 20)-miJugador.getCenterX() ));
			long distanciaY = Math.abs(Math.round((muroCancha[i].getY())-miJugador.getCenterY() ));
			if (distanciaX < 30 && distanciaY < 15) {
				muro = false;
				break;
			}
		}
		return muro;
	}	
	
	public boolean muroizquierda() {
		for (int i = 0; i < muroCancha.length; i++) {
			muro = true;
			long distanciaX = Math.abs(Math.round(miJugador.getCenterX() - (muroCancha[i].getX())));
			long distanciaY = Math.abs(Math.round(miJugador.getCenterY() - (muroCancha[i].getY()+20)));
			if (distanciaX < 55 && distanciaY < 30) {
				muro = false;
				break;
			}
		}
		return muro;
	}
}
