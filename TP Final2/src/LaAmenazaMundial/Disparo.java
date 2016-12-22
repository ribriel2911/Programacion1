package LaAmenazaMundial;

import java.awt.Color;
import entorno.Entorno;

public class Disparo {
	
	//vartiable de entorno;
	Entorno entorno;

	//variables del rectangulo;
	double x;
	double y;
	int altura;
	int base;
	double angulo;
	Color color;
	
	//velocidad del disparo;
	int velocidad;
	
	
	Disparo(Nave nave, Entorno entorno,Color color){
		
		//entorno del juego;
		this.entorno = entorno;
		
		//punto de inicio del disparo;
		this.x = nave.x+(nave.base*2*Math.cos(nave.angulo));
		this.y = nave.y+(nave.base*2*Math.sin(nave.angulo));
		
		//tamañod el disparo;
		this.altura = 20;
		this.base = 2;
		
		//direccion del disparo;
		this.angulo = nave.angulo;
		
		//color y velocidad;
		this.color = color;
		this.velocidad = 4;
		
	}
	
	//mueve el disparo en su direccion y a su velocidad;
	void moverDisparo(){
		
		this.x = this.x+(Math.cos(this.angulo)*this.velocidad);
		this.y = this.y+(Math.sin(this.angulo)*this.velocidad);
	}
	
	//dibuja el disparo en pantalla;
	void dibujarDisparo(){
		
		this.entorno.dibujarRectangulo(this.x, this.y, this.altura, this.base, this.angulo, this.color);
	}
}
