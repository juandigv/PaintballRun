package paintballrun;

import java.io.File;
import java.net.MalformedURLException;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AnimationTimerBala extends AnimationTimer {
	private EquipoEnemigo misEnemigos;
	private Jugador miJugador;
	private Bala[] balaCancha;
	private int vidas = 3;
	private int nivelsuperado = 0;
	private int rondas = 5;
	private MediaPlayer mediaDead;
	private MediaPlayer mediaPlayerA;
	private MediaPlayer mediaPlayerWin;
	private BanderaEnemiga miBandera;
	private Pane paneGameOver;
	private Pane paneVictoria;
	private Laberinto miLaberinto;
	private Label roundLabel;
	private Label lifesLabel;
	private boolean ingame = true;

	public AnimationTimerBala(EquipoEnemigo misEnemigos, Jugador miJugador, BanderaEnemiga miBandera,
			Laberinto miLaberinto, Bala[] balaCancha, Pane paneGameOver, Pane paneVictoria, Label roundLabel,
			Label lifesLabel) throws MalformedURLException {
		this.miBandera = miBandera;
		this.misEnemigos = misEnemigos;
		this.miJugador = miJugador;
		this.balaCancha = balaCancha;
		this.miLaberinto = miLaberinto;
		this.roundLabel = roundLabel;
		this.lifesLabel = lifesLabel;
		this.paneGameOver = paneGameOver;
		this.paneGameOver.setVisible(false);
		this.paneVictoria = paneVictoria;
		this.paneVictoria.setVisible(false);

		multimediaStart();

	}

	public void multimediaStart() throws MalformedURLException {
		File fileMusicA = new File("resources/bg1.mp3");
		String localUrlMusicA = fileMusicA.toURI().toURL().toString();
		Media mediaA = new Media(localUrlMusicA);
		mediaPlayerA = new MediaPlayer(mediaA);
		mediaPlayerA.setVolume(10);
		mediaPlayerA.play();
		File fileMusicB = new File("resources/bgwin.mp3");
		String localUrlMusicB = fileMusicB.toURI().toURL().toString();
		Media mediaB = new Media(localUrlMusicB);
		mediaPlayerWin = new MediaPlayer(mediaB);
		mediaPlayerWin.setVolume(10);
		File fileGameOver = new File("resources/bgdead.mp3");
		String UrlMusicGO = fileGameOver.toURI().toURL().toString();
		Media gameover = new Media(UrlMusicGO);
		mediaDead = new MediaPlayer(gameover);
	}

	public void setRondas(int rondas) {
		this.rondas = rondas;
		reiniciar();
	}

	public void reiniciar() {
		ingame = true;
		vidas = 3;
		nivelsuperado = 0;
		miBandera.respawn();
		miJugador.respawn();
		misEnemigos.respawn();
		miLaberinto.respawn();
		roundLabel.setText("" + (nivelsuperado + 1));
		lifesLabel.setText("  x" + vidas);
		mediaDead.stop();
		mediaPlayerA.stop();
		mediaPlayerWin.stop();
		mediaPlayerA.play();
		paneGameOver.setVisible(false);
		paneVictoria.setVisible(false);
	}

	@Override
	public void handle(long arg0) {
		if (nivelsuperado < rondas) {
			roundLabel.setText("" + (nivelsuperado + 1));
			if (superadoJugador()) {

				nivelsuperado++;
				System.out.println("nivel: " + nivelsuperado);
				miBandera.respawn();
				miJugador.respawn();
				misEnemigos.respawn();
				miLaberinto.respawn();
			} else if (vidas > 0) {
				misEnemigos.disparar();
				misEnemigos.angulotiempo(miJugador.getCenterX(), miJugador.getCenterY());
				if (impactoJugadorBala()) {
					vidas--;
					lifesLabel.setText("  x" + vidas);
					miJugador.respawn();
				}
			} else {
				paneGameOver.toFront();
				paneGameOver.setVisible(true);
				mediaPlayerA.stop();
				mediaDead.play();
				ingame=false;
			}
		} else {
			mediaPlayerWin.play();
			mediaPlayerA.stop();
			paneVictoria.toFront();
			paneVictoria.setVisible(true);
		}
	}

	public boolean impactoJugadorBala() {
		long distanciaX;
		long distanciaY;
		boolean impacto = false;

		for (int i = 0; i < balaCancha.length; i++) {
			distanciaX = Math.abs(Math.round(balaCancha[i].getCenterX() - miJugador.getCenterX()));
			distanciaY = Math.abs(Math.round(balaCancha[i].getCenterY() - miJugador.getCenterY()));

			if (distanciaX < 20 && distanciaY < 20) {
				System.out.println("Player hit");

				impacto = true;
			}
		}
		return impacto;
	}

	public boolean superadoJugador() {
		long distanciaX;
		long distanciaY;
		boolean superado = false;
		if (ingame) {

			for (int i = 0; i < balaCancha.length; i++) {
				distanciaX = Math.abs(Math.round(miBandera.getCenterX() - miJugador.getCenterX()));
				distanciaY = Math.abs(Math.round(miBandera.getCenterY() - miJugador.getCenterY()));
				if (distanciaX < 25 && distanciaY < 25) {
					System.out.println("Player hit");
					superado = true;
				}
			}
		}
		return superado;
	}
}
