package LaAmenazaMundial;

import entorno.Entorno;
import java.awt.Color;
import entorno.Herramientas;

public class Nave {
	
	//variable de entorno;
	Entorno entorno;
	
	//variables del triangulo;
	double x;
	double y;
	int altura;
	int base;
	double angulo;
	Color color;
	
	//variable control de velocidad;
	int velocidad;
	
	
	Nave(Entorno entorno){
		
		//inicializo los valores;
		
		//entorno del juego;
		this.entorno = entorno;
		
		//ubicacion (centro de la pantalla);
		this.x = this.entorno.ancho()/2;
		this.y = this.entorno.alto()/2;
		
		//dimension del triangulo;
		this.altura = 50;
		this.base = 20;
		
		//direccion inicial;
		this.angulo = Math.PI*3/2;
		
		//color y velocidad;
		this.color = color.WHITE;
		this.velocidad = 2;
		
	}
	void dibujarNave(){
		
		
		//creo triangulo;
		this.entorno.dibujarImagen(Herramientas.cargarImagen("imagen.png"), this.x, this.y, this.angulo, 0.5 );
	}
	void girarIzquierda(){
		
		//reduzco el angulo de direccion;
		this.angulo-=0.1;
	}
	
	void girarDerecha(){
		
		//incremento el angulo de direccion;
		this.angulo+=0.1;
	}
	
	void avanzar(){
		
		//incremento el eje X de la posicion sumandole el coseno del angulo de direecion multicplicado por la velocidad;
		this.x+=Math.cos(this.angulo)*this.velocidad;
		
		
		//Si se supera el limite de la pantalla, reubica la nave en el lado opuesto de la misma;
		
		if (this.x<0){
			this.x = this.entorno.ancho();
		}
		if (this.x>this.entorno.ancho()){
			this.x = 0;
		}
		
		//incremento el eje Y de la posicion sumandole el seno del angulo de direecion multicplicado por la velocidad;
		this.y+=Math.sin(this.angulo)*this.velocidad;
		
		if (this.y<50+this.base){
			this.y = this.entorno.alto();
		}
		if (this.y>this.entorno.alto()){
			this.y = 50+this.base;
		}
	}
}
