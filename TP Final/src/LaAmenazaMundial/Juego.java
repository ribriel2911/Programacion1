package LaAmenazaMundial;

import entorno.Herramientas;
import entorno.InterfaceJuego;
import entorno.Entorno;
import java.awt.Color;
import javax.sound.sampled.Clip;
import java.util.Random;

public class Juego extends InterfaceJuego {

	private Entorno entorno;

	// Variables y metodos propios de cada grupo
	
	//Sujetos del juego;
	private boolean gano;
	private Nave nano;
	private Colonia celulas;
	private Colonia virus;
	private Disparos disDestructores;
	private Disparos disDuplicadores;
	private Anticuerpo anticuerpo;
	private boolean anticuerpoActivo;
	private Colesterol colesterol;
	private boolean colesterolActivo;
	//variable de entorno;
	private int puntaje;
	private boolean play;
	private boolean estaApretadaX;
	private boolean estaApretadaZ;
	private Clip music;
	private Clip fin;
	private int imFinal;
	//Controladores;
	private Random azar;
	private int cuentaFPS;
	private int cuentaFPSdisparo;
	private int cuentaFPSanticuerpo;
	private int cuentaFPScolesterol;
	
	Juego() {
		// Inicializa el objeto entorno;
		entorno = new Entorno(this, "NanoBot Arena - Grupo X - v0.01", 800, 600);
		
		// Inicializar lo que haga falta para el juego;
		init();

		// Inicia el juego;
		this.entorno.iniciar();
		
	}
	private void init() {
		
		this.gano = false;
		this.azar = new Random();
		this.nano = new Nave(this.entorno);
		this.disDestructores = new Disparos(this.nano,this.entorno,Color.red);
		this.disDuplicadores = new Disparos(this.nano,this.entorno,Color.GREEN);
		this.celulas = new Colonia(5,this.entorno,this.nano,this.disDestructores,this.disDuplicadores);
		this.virus = new Colonia(10,this.entorno,this.nano,this.disDestructores,this.disDuplicadores);
		this.anticuerpoActivo = false;
		this.colesterolActivo = false;
		this.puntaje = 0;
		this.estaApretadaX = false;
		this.estaApretadaZ = false;
		this.music = Herramientas.cargarSonido("music.wav");
		this.fin = Herramientas.cargarSonido("final.wav");
		this.play = false;
		this.imFinal = azar.nextInt(6);
		this.cuentaFPS = 0;
		this.cuentaFPSdisparo = 0;
		this.cuentaFPSanticuerpo = 0;
		this.cuentaFPScolesterol = 0;
		
		//corrige ubicacion de imagenes para evitar conflictos con git.
		CopiadorABin.iniciar();

	}

	public void tick() {
	// Procesamiento de un instante de tiempo;
		if (this.cuentaFPS<3000){
			this.cuentaFPS+=1;
		}
		if (this.cuentaFPSdisparo<100){
			
			this.cuentaFPSdisparo +=1;
		}
		if (this.cuentaFPSanticuerpo<500){
			
			this.cuentaFPSanticuerpo +=1;
		}
		if (this.cuentaFPScolesterol<1200){
			
			this.cuentaFPScolesterol +=1;
		}

		if(!this.virus.juegoTerminado&&!this.gano){
			
			if(this.cuentaFPS==3000){
				this.gano = true;
			}
			
			//dibuja imagen de fondo;
			this.entorno.dibujarImagen(Herramientas.cargarImagen("fondo.jpg"), this.entorno.ancho()/2, ((int)this.entorno.alto()/2)+29, 0);
			
			//activa la musica del juego;
			if (!this.play){
				this.music.loop(Clip.LOOP_CONTINUOUSLY);
				this.play = true;
			}
			//actualiza el puntaje;
			this.puntaje = this.virus.puntaje;
			//dibuja la linea superior;
			this.entorno.dibujarRectangulo(this.entorno.ancho()/2, 50, 2, this.entorno.ancho(), Math.PI/2, Color.WHITE);
			//dibuja puntaje;
			
			this.entorno.cambiarFont("",14,Color.ORANGE);
			this.entorno.escribirTexto("TIEMPO: "+this.cuentaFPS/100, 20, 25);
			
			this.entorno.cambiarFont("",14,Color.BLUE);
			this.entorno.escribirTexto("MUNICION SUERO", 200, 25);
			
			this.entorno.cambiarFont("",14,Color.YELLOW);
			this.entorno.escribirTexto("PUNTAJE: "+this.puntaje, 700, 25);
			//dibuja municion;
			this.entorno.cambiarFont("",14,Color.BLUE);
			this.entorno.escribirTexto("ENERGIA LAZER", 400, 25);
			//dibuja nave;
			this.nano.dibujarNave();
			//inicializa los virus y celulas, controla su comportamiento y su influencia en el juego;
			this.virus.activarMinions();
			this.celulas.activarCelulas(this.virus);
			if (this.cuentaFPSanticuerpo==500){
				
				this.anticuerpo = new Anticuerpo(this.virus,this.celulas,this.nano,this.entorno);
				this.anticuerpoActivo = true;
			}
			if(this.anticuerpoActivo){
				this.anticuerpo.activar();
				this.cuentaFPSanticuerpo = 0;
				if(this.anticuerpo.finalizar){
					this.anticuerpoActivo = false;
				}
			}
			if (this.cuentaFPScolesterol==300){
				this.nano.velocidad = 2;
			}
			
			if (this.cuentaFPScolesterol==800){
				
				this.colesterol = new Colesterol(this.virus,this.celulas,this.nano,this.entorno);
				this.colesterolActivo = true;
			}
			if (this.cuentaFPScolesterol==1200){
				this.colesterolActivo = false;
				this.cuentaFPScolesterol = 0;
			}
			
			if(this.colesterolActivo){
				this.colesterol.activar();
				if(this.colesterol.finalizar){
					this.colesterolActivo = false;
				}
			}
			//dibuja y mueve los disparos activos;
			this.disDestructores.seguirDisparo();
			this.disDuplicadores.seguirDisparo();
			//si se presiona 'X' activa un nuevo disparo y desactiva el boton hasta q se suelte;
			if (this.entorno.estaPresionada('X')){
					
				if(!this.estaApretadaX||this.cuentaFPSdisparo>=100){
					
					this.disDestructores.disparar();	
					this.estaApretadaX = true;
					this.cuentaFPSdisparo = 0;
				}
			}
			//si se suelta activa el boton;	
			else{
				
				this.estaApretadaX = false;
			}
			
			if (this.entorno.estaPresionada('C')&&this.anticuerpo.estaCerca()){
				
					this.anticuerpo.frenar();	
			}
			//si se suelta activa el boton;	
			else{
					
			}
			
			if (this.entorno.estaPresionada('Z')){
				
				if(!this.estaApretadaZ||this.cuentaFPSdisparo>=100){
					
					this.disDuplicadores.disparar();	
					this.estaApretadaZ = true;
					this.cuentaFPSdisparo = 0;
				}
			}
			//si se suelta activa el boton;	
			else{
				
				this.estaApretadaZ = false;
			}
			
			//actualiza la cantidad de municiones disponibles;
			this.disDestructores.mostrarMunicion(500);
			this.disDuplicadores.mostrarMunicion(310);
			//gira nave hacia la izquierda;
			if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)){
				this.nano.girarIzquierda();
			}
			//gira nave hacia derecha;
			if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)){
				this.nano.girarDerecha();
			}
			//hace avanzar nave;
			if (this.entorno.estaPresionada(this.entorno.TECLA_ARRIBA)){
				this.nano.avanzar();
			}
		}
		else{
			
			if(!this.gano){
				//pantalla de finalizacion;
				
				//detiene la musica del juego e inicia la de la pantalla final;
				if (this.play){
					this.music.stop();
					this.music.close();
					Herramientas.play("grito.wav");
					this.fin.loop(Clip.LOOP_CONTINUOUSLY);
					this.play = false;
				}
				
				//dibuja imagen de fondo, puntaje y demas;
				this.entorno.dibujarImagen(Herramientas.cargarImagen("fondoMalo"+this.imFinal+".jpg"), this.entorno.ancho()/2, this.entorno.alto()/2, 0);
				this.entorno.cambiarFont("",20,Color.RED);
				this.entorno.escribirTexto("JUEGO TERMINADO",20, 50);
				this.entorno.cambiarFont("",20,Color.YELLOW);
				this.entorno.escribirTexto("SU PUNTAJE: "+this.puntaje, 20, 100);
				
				this.entorno.cambiarFont("",20,Color.ORANGE);
				this.entorno.escribirTexto("Los cientificos fallaron....", 20,this.entorno.alto()/2 );
				this.entorno.escribirTexto("la tierra ahora es de los zombies.", 20,this.entorno.alto()/2+30 );
				
				this.entorno.cambiarFont("",20,Color.blue);
				this.entorno.escribirTexto("PRESIONE ENTER PARA VOLVER A JUGAR", this.entorno.ancho()-450, this.entorno.alto()-30);
				
				//si el usuario presiona ENTER finaliza la musica e inicia de nuevo el juego;
				if (this.entorno.estaPresionada(this.entorno.TECLA_ENTER)){
					this.fin.stop();
					this.fin.close();
					init();
				}
			}
			
			else{
				if (this.play){
					this.music.stop();
					this.music.close();
					Herramientas.play("victoria.wav");
					this.play = false;
				}
				
				//dibuja imagen de fondo, puntaje y demas;
				this.entorno.dibujarImagen(Herramientas.cargarImagen("gano.jpg"), this.entorno.ancho()/2, this.entorno.alto()/2, 0);
				this.entorno.cambiarFont("",20,Color.RED);
				this.entorno.escribirTexto("JUEGO TERMINADO",20, 50);
				this.entorno.cambiarFont("",20,Color.YELLOW);
				this.entorno.escribirTexto("SU PUNTAJE: "+this.puntaje, 20, 100);
				
				this.entorno.cambiarFont("",20,Color.ORANGE);
				this.entorno.escribirTexto("La solucion de los cientificos", 20,this.entorno.alto()-100 );
				this.entorno.escribirTexto("a la infeccion fue todo un exito.", 20,this.entorno.alto()-80 );
				
				this.entorno.cambiarFont("",20,Color.blue);
				this.entorno.escribirTexto("PRESIONE ENTER PARA VOLVER A JUGAR", this.entorno.ancho()-450, this.entorno.alto()-30);
				
				//si el usuario presiona ENTER finaliza la musica e inicia de nuevo el juego;
				if (this.entorno.estaPresionada(this.entorno.TECLA_ENTER)){
					init();
				}
			
			}
		}
			
	}
	public static void main(String[] args){
		
		Juego juego = new Juego();
		
	}
}
