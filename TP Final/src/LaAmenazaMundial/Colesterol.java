package LaAmenazaMundial;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Colesterol {
	
	Minion colesterol;
	Colonia virus;
	Colonia celulas;
	Entorno entorno;
	Nave nano;
	boolean finalizar;
	int contador;
	int incrementador;
	int numIm;
	
	Colesterol(Colonia virus,Colonia celulas,Nave nano,Entorno entorno){
		
		this.colesterol = new Minion(nano,entorno,Color.GREEN);
		this.virus = virus;
		this.celulas = celulas;
		this.entorno = entorno;
		this.nano = nano;
		this.finalizar = false;
		this.contador = 0;
		this.incrementador = -1;
		this.numIm = 0;
	}
	
	void activar(){
		
		this.contador+=1;
		
		if (this.contador>=25){

			if (this.numIm==2||this.numIm==0){
				this.incrementador = this.incrementador*(-1);
			}
			this.numIm += incrementador;
			this.contador = 0;
		}
		
		this.entorno.dibujarImagen(Herramientas.cargarImagen("colesterol"+this.numIm+".png"), this.colesterol.x, this.colesterol.y, this.colesterol.rotacion ,(double)this.colesterol.diametro/160);

		if (this.colesterol.x>=this.nano.x-(this.colesterol.diametro/2) && this.colesterol.x<=this.nano.x+(this.colesterol.diametro/2) && this.colesterol.y>=this.nano.y-(this.colesterol.diametro/2) && this.colesterol.y<=this.nano.y+(this.colesterol.diametro/2)){
			
			this.nano.velocidad=this.nano.velocidad/2;
			this.finalizar = true;
		}
		
		if(this.colesterol.realentizar){
			
			this.colesterol.rotacion+=0.01;
			
				
			this.colesterol.moverMinion();
			
			this.colesterol.realentizar = false;
		}
		else{
			this.colesterol.realentizar = true;
		}

	}
	

}
