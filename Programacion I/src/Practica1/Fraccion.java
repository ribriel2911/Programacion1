package Practica1;

public class Fraccion {

	int numerador;
	int denominador;

	Fraccion (int numerador, int denominador){

		this.numerador = numerador;
		this.denominador = denominador;
	}

	void imprimir(){

		System.out.println(this.numerador+"/"+this.denominador);
	}

	void invertirSigno(){

		this.numerador = this.numerador*(-1);
	}

	void invertir(){

		int x = this.numerador;
		this.numerador = this.denominador;
		this.denominador = x;
	}
	
	double aDouble(){

		double den = this.denominador;
		double num = this.numerador;
		double resultado = num/den;

		return resultado;
	}

	static int mcd(int a, int b){

		if (a<0){
	
				a = a*(-1);
			}
	
			if (b<0){
	
				b = b*(-1);
			}
	
			if (b>a){
	
				int x;
				
				x = a;
				a = b;
				b = x;
			}
		
		if (b==0){
	
				return a;
			}

		int r = a%b;

		return mcd(b,r);
	}

	void reducir(){

		int reductor = mcd(this.numerador,this.denominador);

		this.numerador = this.numerador/reductor;
		this.denominador = this.denominador/reductor;
	}

	static Fraccion producto(Fraccion q1,Fraccion q2){

		q1.reducir();
		q2.reducir();

		int numx = q1.numerador*q2.numerador;
		int denx = q1.denominador*q2.denominador;

		Fraccion qx = new Fraccion(numx,denx);

		return qx;
	}

	static Fraccion suma(Fraccion q1, Fraccion q2){

		q1.reducir();
		q2.reducir();

		int denx = q1.denominador*q2.denominador;
		int numA = denx/q1.denominador*q1.numerador;
		int numB = denx/q2.denominador*q2.numerador;
		int numx = numA + numB;

		Fraccion qx = new Fraccion(numx,denx);

		return qx;
	}
}

