package Practica1;

public class Circulo {

	double radio;
	Punto centro;

	Circulo (double centro_x, double centro_y, double radio){

		this.radio = radio;
		this.centro = new Punto(centro_x,centro_y);
	}

	void imprimir (){

		System.out.println("El circulo esta ubicado en el punto: x = "+this.centro.x+" y = "+this.centro.y);
		System.out.println("y su radio es: "+this.radio);
	}

	double perimetro(){

		double perimetro = 2 * Math.PI * this.radio;

		return perimetro;
	}

	double area(){

		double area = Math.PI * (this.radio*this.radio);

		return area;
	}

	void escalar(double factor){
		
		this.radio=this.radio*factor;

	}

	void desplazar(double desp_x, double desp_y){

		this.centro.desplazar(desp_x, desp_y);
	}

	static double distancia(Circulo c1, Circulo c2){

		double base = Math.max(c1.centro.x, c2.centro.x) - Math.min(c1.centro.x, c2.centro.x);
		double altura = Math.max(c1.centro.y, c2.centro.y) - Math.min(c1.centro.y, c2.centro.y);
		double hipotenusa2 = (base*base)+(altura*altura);
		double hipotenusa = Math.sqrt(hipotenusa2);

		double distancia = hipotenusa - c1.radio - c2.radio;

		if (distancia<0){

			distancia = 0;
		}

		return distancia;
	}

	static boolean seTocan(Circulo c1, Circulo c2){

		double base = Math.max(c1.centro.x, c2.centro.x) - Math.min(c1.centro.x, c2.centro.x);
		double altura = Math.max(c1.centro.y, c2.centro.y) - Math.min(c1.centro.y, c2.centro.y);
		double hipotenusa2 = (base*base)+(altura*altura);
		double hipotenusa = Math.sqrt(hipotenusa2);

		double distancia = hipotenusa - c1.radio - c2.radio;

		if (distancia<0){
			
			return true;
		}

		return false;
	}

	boolean loContiene(Circulo otro){

		double base = Math.max(this.centro.x, otro.centro.x) - Math.min(this.centro.x, otro.centro.x);
		double altura = Math.max(this.centro.y, otro.centro.y) - Math.min(this.centro.y, otro.centro.y);
		double hipotenusa2 = (base*base)+(altura*altura);

		double hipotenusa = Math.sqrt(hipotenusa2);
		
		if((hipotenusa+otro.radio)<=this.radio){
			
			return true;
		}

		return false;
	}

}
