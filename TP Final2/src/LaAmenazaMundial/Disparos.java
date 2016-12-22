package LaAmenazaMundial;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Color;

public class Disparos {
	
	Disparo[] disparos;
	Entorno entorno;
	Nave nave;
	Color color;
	
	Disparos(Nave nave,Entorno entorno,Color color){
		
		this.disparos = new Disparo[3];
		this.entorno = entorno;
		this.nave = nave;
		this.color = color;
	}
	
	void mostrarMunicion(int x){
		
		int y=0;
		
		for (int i= 0;i<this.disparos.length;i++){

			if (this.disparos[i]==null){
	
				entorno.dibujarRectangulo(x+21, y+20, 20, 20, Math.PI, this.color );
				x = x+21;
				
			}
		}
	}
	
	void disparar(){
		
		int i = 0;
		
		while (i<3 && i>-1){
			
			if (this.disparos[i]==null){
				
				Herramientas.play("lazer.wav");
				this.disparos[i] = new Disparo(this.nave,this.entorno,this.color);
				this.disparos[i].dibujarDisparo();
				i=-1;
			}
			else{
				i++;
			}
		}
	}
	
	void seguirDisparo(){
		
		for (int i = 0; i < 3; i++){
			
			if (this.disparos[i]!=null){
			
				if (this.disparos[i].x>800 || this.disparos[i].y>600 || this.disparos[i].y<62 || this.disparos[i].x<0){
					
					this.disparos[i] = null;
				}
				
				else{
					this.disparos[i].moverDisparo();
					this.disparos[i].dibujarDisparo();
				}
			}
		}
	}

}
