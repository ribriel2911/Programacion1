package LaAmenazaMundial;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;

public class Minion {
	
	//variable de entorno;
	Entorno entorno;
	//variable nave;
	Nave nave;
	
	//variable random;
	Random azar;
	
	//variables del circulo;
	double x;
	double y;
	int diametro;
	double direccion;
	Color color;
	
	//variable de velocidad;
	double velocidad;
	boolean realentizar;
	double rotacion;
	
	
	Minion(Entorno entorno,Color color){
		
		this.entorno = entorno;
		this.azar = new Random();
		
		this.diametro = 80;

		int elegirLado = azar.nextInt(4);
		
		switch (elegirLado){
		
		case 0 : this.x = 0 ; this.y = azar.nextInt(this.entorno.alto()) ; break ;
		case 1 : this.x = this.entorno.ancho() ; this.y = azar.nextInt(this.entorno.alto()) ; break ;
		case 2 : this.x = azar.nextInt(this.entorno.ancho()) ; this.y = 50+(this.diametro/2) ; break ;
		case 3 : this.x = azar.nextInt(this.entorno.ancho()) ; this.y = this.entorno.alto() ; break ;
	}
		
		this.direccion = azar.nextInt();
	
		this.rotacion = 0;
		this.color = color;
		this.velocidad = 2;
		this.realentizar = true;
	}
		
	
	
	Minion(Nave nave,Entorno entorno,Color color){   
		
		//inicializo los valores;
		
		//entorno y nave del juego;
		this.entorno = entorno;
		this.nave = nave;
		
		
		boolean estaCerca = true;
		
		while (estaCerca){
			
			estaCerca = false;
			
			//random a azar;
			this.azar = new Random();
			
			int elegirLado = azar.nextInt(4);
			
			//dimension del nuevo virus;
			this.diametro = 80;
			
			switch (elegirLado){
			
				case 0 : this.x = 0 ; this.y = azar.nextInt(this.entorno.alto()) ; break ;
				case 1 : this.x = this.entorno.ancho() ; this.y = azar.nextInt(this.entorno.alto()) ; break ;
				case 2 : this.x = azar.nextInt(this.entorno.ancho()) ; this.y = 50+(this.diametro/2) ; break ;
				case 3 : this.x = azar.nextInt(this.entorno.ancho()) ; this.y = this.entorno.alto() ; break ;
			}
			
			
		
			//si esta serca de la nave se reasigna un nuevo valor al azar a X e Y;
			if ((this.x>=this.nave.x-(this.diametro/2) && this.x<=this.nave.x+(this.diametro/2))||(this.y>=this.nave.y-(this.diametro/2)  && this.y<=nave.y+(this.diametro/2)  && this.y<50+(this.diametro/2))) {
				estaCerca = true;
			}
		}
		
		//angulo de direccion de movimiento al azar del nuevo virus;
		this.direccion = azar.nextInt();
		
		this.rotacion = 0;
		
		//color y velocidad de movimiento del nuevo virus;
		this.color = color;
		this.velocidad = 2;
		this.realentizar = true;
	}
	
	Minion(Minion celula,Entorno entorno){
		
		this.entorno = entorno;
		
		this.x = celula.x;
		this.y = celula.y;
		this.direccion = celula.direccion;
		this.diametro = celula.diametro;
		this.rotacion = 0;
		
		this.color = Color.RED;
		this.velocidad = 2;
		this.realentizar = true;
	}
	void moverMinion(){			//Realiza el movimiento de los virus;
		
		//guardo el valor anterior de X;
		double xAnt = this.x;
		
		//incremento el eje X de la posicion sumandole el coseno del angulo de direecion multicplicado por la velocidad;
		this.x+=Math.cos(this.direccion)*this.velocidad;
		
		if (this.x==xAnt){
			
			this.direccion = azar.nextInt();
		}
		
		//Si se supera el limite de la pantalla, reubica el minion en el lado opuesto de la misma;
		
		if (this.x<0){
			this.x = this.entorno.ancho();
		}
		if (this.x>this.entorno.ancho()){
			this.x = 0;
		}
		
		//guardo el valor anterior de Y;
		double yAnt = this.y;
		
		//incremento el eje Y de la posicion sumandole el seno del angulo de direecion multicplicado por la velocidad;
		this.y+=Math.sin(this.direccion)*this.velocidad;
		
		//si por algun motivo se llegara a producir una igualdad con el valor anterior de Y, se reasigna un nuevo valor al azar;
		if (this.y==yAnt){
			
			this.direccion = azar.nextInt();
		}
		
		//Si se supera el limite de la pantalla, reubica el minion en el lado opuesto de la misma;
		if (this.y<50+(this.diametro/2)){
			this.y = this.entorno.alto();
		}
		if (this.y>this.entorno.alto()){
			this.y = 50+(this.diametro/2);
		}
	}
}
