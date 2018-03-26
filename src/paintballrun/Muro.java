package paintballrun;

import java.util.Random;

import javafx.scene.shape.Rectangle;

public class Muro extends Rectangle {

	private int[] posicionX = {200,50,320,160,270,380,100,140,300,50,200,350,80,260,200};
	private int[] posicionY = {100,230,400,360,220,200,100,240,100,400,100,300,330,320,180};
	
	Random rand = new Random();

	public Muro (int posicion)
	{
		this.setX(posicionX[posicion]);
		this.setY(posicionY[posicion]);
		this.setHeight(40);
		this.setWidth(40);
	}
	public void setPosition(int posicion) {
		this.setX(posicionX[posicion]);
		this.setY(posicionY[posicion]);
	}

}