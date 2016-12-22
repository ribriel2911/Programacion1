package LaAmenazaMundial;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Anticuerpo {
	
	Minion anticuerpo;
	Colonia virus;
	Colonia celulas;
	Entorno entorno;
	Nave nano;
	boolean finalizar;
	int contador;
	int incrementador;
	int numIm;
	
	Anticuerpo(Colonia virus,Colonia celulas,Nave nano,Entorno entorno){
		
		this.anticuerpo = new Minion(nano,entorno,Color.GREEN);
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
		
		if (this.contador>=10){

			if (this.numIm==9||this.numIm==0){
				this.incrementador = this.incrementador*(-1);
			}
			this.numIm += incrementador;
			this.contador = 0;
		}
		
		this.entorno.dibujarImagen(Herramientas.cargarImagen("anticuerpo"+this.numIm+".png"), this.anticuerpo.x, this.anticuerpo.y, this.anticuerpo.rotacion ,(double)this.anticuerpo.diametro/160);
			
		for (int i4 = 0;i4<this.virus.colonia.size();i4++){
			
		//si la celula se encuentra cerca de un minion la conviente en minion;
			
			if (this.anticuerpo.x+(this.anticuerpo.diametro/2)>=this.virus.colonia.get(i4).x-(this.virus.colonia.get(i4).diametro/2) && this.anticuerpo.x-(this.anticuerpo.diametro/2)<=this.virus.colonia.get(i4).x+(this.virus.colonia.get(i4).diametro/2) && this.anticuerpo.y+(this.anticuerpo.diametro/2)>=this.virus.colonia.get(i4).y-(this.virus.colonia.get(i4).diametro/2) && this.anticuerpo.y-(this.anticuerpo.diametro/2)<=this.virus.colonia.get(i4).y+(this.virus.colonia.get(i4).diametro/2)){
					
				Herramientas.play("cura.wav");
					
				this.celulas.colonia.add(new Minion(this.virus.colonia.get(i4),this.entorno));
				this.virus.colonia.remove(i4);
				this.finalizar = true;
			}
		}
		
		if(this.anticuerpo.realentizar){
			
			this.anticuerpo.rotacion+=0.01;
			
			if(!this.entorno.estaPresionada('C')||!estaCerca()){
				
				this.anticuerpo.moverMinion();
			}
			this.anticuerpo.realentizar = false;
		}
		else{
			this.anticuerpo.realentizar = true;
		}
	}
	
	void frenar(){
		
		this.anticuerpo.x = this.nano.x+(this.nano.base*2*Math.cos(this.nano.angulo));
		this.anticuerpo.y =  this.nano.y+(this.nano.base*2*Math.sin(this.nano.angulo));
		this.anticuerpo.direccion = this.nano.angulo;
	}
	
	boolean estaCerca(){
		
		if (this.anticuerpo.x>=this.nano.x-(this.anticuerpo.diametro/2) && this.anticuerpo.x<=this.nano.x+(this.anticuerpo.diametro/2) && this.anticuerpo.y>=this.nano.y-(this.anticuerpo.diametro/2) && this.anticuerpo.y<=this.nano.y+(this.anticuerpo.diametro/2)){
			return true;
		}
		
		return false;
	}
}

