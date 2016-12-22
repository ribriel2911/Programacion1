package Practica1;


public class Punto {

	double x;
	double y;

	Punto(){

		this.x = 0;
		this.y = 0;
	}

	Punto(double x, double y){

		this.x = x;
		this.y = y;
	}

	void imprimir(){

		System.out.println("x = "+this.x);
		System.out.println("y = "+this.y);
	}

	void desplazar(double desp_x, double desp_y){

		this.x += desp_x;
		this.y += desp_y;
	}

	static double distancia(Punto p1, Punto p2){

		double base = Math.max(p1.x,p2.x)-Math.min(p1.x,p2.x);
		double altura = Math.max(p1.y,p2.y)-Math.min(p1.y,p2.y);

		double hipotenusa2 = (base*base)+(altura*altura);

		double hipotenusa = Math.sqrt(hipotenusa2);

		return hipotenusa;
	}
}

