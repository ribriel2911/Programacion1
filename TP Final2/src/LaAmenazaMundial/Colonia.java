package LaAmenazaMundial;

import java.awt.Color;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
import java.util.ArrayList;
import java.util.List;


public class Colonia {
	
	//conjunto de minions;
	List <Minion> colonia;
	
	//variables exteriores;
	int puntaje;
	Entorno entorno;
	Nave nave;
	Disparos disDestructores;
	Disparos disDuplicadores;
	boolean alentar;
	
	int cuentaFPS;
	int contador;
	int numIm;
	int incrementador;
	
	int cuentaCelulas;
	boolean celulasInicializadas;
	//variable random
	Random azar;
	
	//finalizador de juego;
	boolean juegoTerminado;
	
	Colonia(int cantidad, Entorno entorno, Nave nave, Disparos destructores, Disparos duplicadores){
		
		//inicializa arreglo;
		this.colonia = new ArrayList<Minion>();
		this.puntaje = 0;
		this.entorno = entorno;
		this.nave = nave;
		this.disDestructores = destructores;
		this.disDuplicadores = duplicadores;
		
		this.cuentaCelulas = 0;
		this.celulasInicializadas = false;
		
		this.cuentaCelulas = 0;
		this.contador = 0;
		this.incrementador = -1;
		this.numIm = 0;
		
		
		//random a azar;po
		this.azar = new Random();
		
		//inicializa finalizador en false;
		this.juegoTerminado = false;
		
	}
	
	//inicializa los minions y controla su comportamiento y su influencia en el juego;
	void activarMinions(){
		
		this.contador+=1;
		if(this.colonia.isEmpty()&&this.cuentaFPS<300){
			this.cuentaFPS+=1;
		}
		//recorre los minions y corrobora si hay alguno inicializado;
		for (int i2 = 0; i2 < this.colonia.size(); i2++){
			
				
			//	virusActivo = true;
				
				if (this.contador>=10){

					if (this.numIm==9||this.numIm==0){
						this.incrementador = this.incrementador*(-1);
					}
					this.numIm += incrementador;
					this.contador = 0;
				}
				
				this.entorno.dibujarImagen(Herramientas.cargarImagen("virus"+this.numIm+".png"),this.colonia.get(i2).x,this.colonia.get(i2).y,this.colonia.get(i2).rotacion,(double)this.colonia.get(i2).diametro/160);
				
				if(this.colonia.get(i2).realentizar){
					
					this.colonia.get(i2).rotacion+=0.01;
					this.colonia.get(i2).moverMinion();
					this.colonia.get(i2).realentizar = false;
				}
				else{
					this.colonia.get(i2).realentizar = true;
				}
				
				
				//si el minion se encuentra cerca de la nave activa el finalizador;
				if (this.colonia.get(i2).x>=this.nave.x-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).x<=this.nave.x+(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y>=this.nave.y-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y<=this.nave.y+(this.colonia.get(i2).diametro/2)){
					this.juegoTerminado=true;
				}
				
				//recorre los disparos activos;
				for (int i3 = 0; i3<3; i3++){
					
					
					if (this.disDestructores.disparos[i3]!=null){
						
						//si esta cerca del minion borra el disparo y al minion y suma los puntos;
						if (this.colonia.get(i2).x>=this.disDestructores.disparos[i3].x-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).x<=this.disDestructores.disparos[i3].x+(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y>=this.disDestructores.disparos[i3].y-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y<=this.disDestructores.disparos[i3].y+(this.colonia.get(i2).diametro/2)){

							Herramientas.play("destruye.wav");
							
							this.puntaje += 5;
							this.disDestructores.disparos[i3] = null;
							
							if (21 > this.colonia.get(i2).diametro/2) {
								this.colonia.remove(i2);
							}
							else{
								this.colonia.get(i2).diametro = this.colonia.get(i2).diametro/2;
								this.colonia.add(new Minion(this.colonia.get(i2),this.entorno));
								this.colonia.get(this.colonia.size()-1).direccion = this.colonia.get(this.colonia.size()-1).direccion*(-Math.PI);								
							}
							break;
					}
				}
			}
		}
		for (int i2 = 0; i2 < this.colonia.size(); i2++){
			
			for (int i3 = 0; i3<3; i3++){
				
				if (this.disDuplicadores.disparos[i3]!=null){
					
					if (this.colonia.get(i2).x>=this.disDuplicadores.disparos[i3].x-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).x<=this.disDuplicadores.disparos[i3].x+(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y>=this.disDuplicadores.disparos[i3].y-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y<=this.disDuplicadores.disparos[i3].y+(this.colonia.get(i2).diametro/2)){
						
						Herramientas.play("divide.wav");
						
						this.disDuplicadores.disparos[i3] = null;
						
						if(41 < this.colonia.get(i2).diametro/2){
							this.colonia.get(i2).diametro = this.colonia.get(i2).diametro/2;
							this.colonia.add(new Minion(this.colonia.get(i2),this.entorno));
							this.colonia.get(this.colonia.size()-1).direccion = this.colonia.get(this.colonia.size()-1).direccion*(-Math.PI);
						}
						else{
							this.colonia.get(i2).diametro = this.colonia.get(i2).diametro*2;
						}
					}
				}
			}
		}
		
		//variable random para que los virus aparezcan de manera aleatoria;
		int provabilidad = this.azar.nextInt(500);
		
		//si se da la provabilidad o si no hay ningun virus en pantalla buscara un lugar para un nuevo virus;
		if (provabilidad==0||this.cuentaFPS==300){
			
			//si encuentra un lugar libre en el arreglo inicializa un nuevo minion y sale del ciclo;
				this.colonia.add(new Minion(this.nave,this.entorno,Color.RED));
				this.cuentaFPS = 0;

		}
		
	}
	
	void activarCelulas(Colonia minions){
		
		this.contador+=1;
		
		if (this.cuentaCelulas>2){
			this.celulasInicializadas = true;
		}
		//recorre las celulas y corrobora si hay alguna inicializada;
		for (int i2 = 0; i2 < this.colonia.size(); i2++){
			
			//si encuentra una inicializada levanta bandera, la muestra en pantalla y la mueve;
				
				if (this.contador>=10){

					if (this.numIm==9||this.numIm==0){
						this.incrementador = this.incrementador*(-1);
					}
					this.numIm += incrementador;
					this.contador = 0;
				}
				
				this.entorno.dibujarImagen(Herramientas.cargarImagen("celula"+numIm+".png"),this.colonia.get(i2).x,this.colonia.get(i2).y,this.colonia.get(i2).rotacion,(double)this.colonia.get(i2).diametro/160);
				
				if(this.colonia.get(i2).realentizar){
					
					this.colonia.get(i2).rotacion+=0.01;
					this.colonia.get(i2).moverMinion();
					this.colonia.get(i2).realentizar = false;
				}
				else{
					this.colonia.get(i2).realentizar = true;
				}
				
				for (int i4 = 0;i4<minions.colonia.size();i4++){
					
				//si la celula se encuentra cerca de un minion la conviente en minion;
					if (!minions.colonia.isEmpty()){
					
						if (this.colonia.get(i2).x+(this.colonia.get(i2).diametro/2)>=minions.colonia.get(i4).x-(minions.colonia.get(i4).diametro/2) && this.colonia.get(i2).x-(this.colonia.get(i2).diametro/2)<=minions.colonia.get(i4).x+(minions.colonia.get(i4).diametro/2) && this.colonia.get(i2).y+(this.colonia.get(i2).diametro/2)>=minions.colonia.get(i4).y-(minions.colonia.get(i4).diametro/2) && this.colonia.get(i2).y-(this.colonia.get(i2).diametro/2)<=minions.colonia.get(i4).y+(minions.colonia.get(i4).diametro/2)){
							
							Herramientas.play("mordedura.wav");
							
							minions.colonia.add(new Minion(this.colonia.get(i2),this.entorno)) ;
							this.colonia.remove(i2);
							break;

							}
						}
					}
				//recorre los disparos activos;
				for (int i3 = 0; i3<3; i3++){
					
					if (this.disDestructores.disparos[i3]!=null){
						
						//si esta cerca del minion borra el disparo y al minion y suma los puntos;
						if (this.colonia.get(i2).x>=this.disDestructores.disparos[i3].x-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).x<=this.disDestructores.disparos[i3].x+(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y>=this.disDestructores.disparos[i3].y-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y<=this.disDestructores.disparos[i3].y+(this.colonia.get(i2).diametro/2)){
							
							Herramientas.play("destruye.wav");
							
							this.disDestructores.disparos[i3] = null;
							
							if (21 > this.colonia.get(i2).diametro/2) {
								this.colonia.remove(i2);
							}
							else{
								this.colonia.get(i2).diametro = this.colonia.get(i2).diametro/2;
								this.colonia.add(new Minion(this.colonia.get(i2),this.entorno));
								this.colonia.get(this.colonia.size()-1).direccion = this.colonia.get(this.colonia.size()-1).direccion*(-Math.PI);								
							}
							break;
							
						}
					}
				}
			}
		
		for (int i2 = 0; i2 < this.colonia.size(); i2++){
			
			for (int i3 = 0; i3<3; i3++){
				
				if (this.disDuplicadores.disparos[i3]!=null){
					
					if (this.colonia.get(i2).x>=this.disDuplicadores.disparos[i3].x-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).x<=this.disDuplicadores.disparos[i3].x+(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y>=this.disDuplicadores.disparos[i3].y-(this.colonia.get(i2).diametro/2) && this.colonia.get(i2).y<=this.disDuplicadores.disparos[i3].y+(this.colonia.get(i2).diametro/2)){
						
						Herramientas.play("divide.wav");
						
						minions.puntaje += 5;
						this.disDuplicadores.disparos[i3] = null;
						
						if(41 < this.colonia.get(i2).diametro/2){
							this.colonia.get(i2).diametro = this.colonia.get(i2).diametro/2;
							this.colonia.add(new Minion(this.colonia.get(i2),this.entorno));
							this.colonia.get(this.colonia.size()-1).direccion = this.colonia.get(this.colonia.size()-1).direccion*(-Math.PI);
						}
						else{
							this.colonia.get(i2).diametro = this.colonia.get(i2).diametro*2;
						}
					}
				}
			}
		}

		//variable random para que los virus aparezcan de manera aleatoria;
		int provabilidad = this.azar.nextInt(1000);
		
		
		//si se da la provabilidad o si no hay ningun virus en pantalla buscara un lugar para un nuevo virus;
		if ( provabilidad==0 || !this.celulasInicializadas){
			
			//si encuentra un lugar libre en el arreglo inicializa un nuevo minion y sale del ciclo;
				
				this.cuentaCelulas+=1;
				this.colonia.add(new Minion(this.entorno,Color.BLUE)) ;
		}
		
		boolean finalizador = true;
		
		if (!this.colonia.isEmpty()){
				
				finalizador = false;
			}
		
		if (finalizador){
			
			minions.juegoTerminado = true;
		}
	
	}
	
}

