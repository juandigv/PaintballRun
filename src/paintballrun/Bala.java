package paintballrun;

import javafx.scene.shape.Circle;

public class Bala extends Circle {
	private double angulo=90;
	private double direccion;

	public Bala(double x, double y) {

		super();
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(5);
		this.direccion = 1.5;
		
		
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	
	
	public void mover() {

		double x = this.getCenterX();
		double y = this.getCenterY();

		if (angulo == 90)
			y += direccion;
		if (angulo == -90)
			y -= direccion;
		if (angulo == 0)
			x += direccion;
		if (angulo == 180)
			x -= direccion;
		if (angulo >0 && angulo <45) {
			x += direccion;
			y += direccion*Math.tan(Math.toRadians(angulo));
		}
		if (angulo >44 && angulo <90) {
			y += direccion;
			x += direccion/Math.tan(Math.toRadians(angulo));
		}
		if (angulo >90 && angulo <135) {
			y += direccion;
			x -= direccion*Math.tan(Math.toRadians((angulo)-90));
		}
		if (angulo >134 && angulo <180) {
			x -= direccion;
			y += direccion/Math.tan(Math.toRadians((angulo)-90));
		}
		if (angulo >-45 && angulo <0) {
			x += direccion;
			y -= direccion*Math.tan(Math.toRadians(Math.abs(angulo)));
		}
		if (angulo >-90 && angulo <-44) {
			x += direccion/Math.tan(Math.toRadians(Math.abs(angulo)));
			y -= direccion;
		}
		if (angulo <-90 && angulo>-135) {
			x -= direccion*Math.tan(Math.toRadians(Math.abs(angulo)-90));
			y -= direccion;
		}
		if (angulo >-180 && angulo <-134) {
			x -= direccion;
			y -= direccion/Math.tan(Math.toRadians(Math.abs(angulo)-90));
		}		
	
		this.setCenterX(x);
		this.setCenterY(y);
		//System.out.println(x + "" + y);
	}

}
