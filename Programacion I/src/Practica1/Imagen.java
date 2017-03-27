package Practica1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Practica1.Imagen;
import Practica1.Pixel;

public class Imagen {
	Pixel[][] pixels;
	int alto;
	int ancho;
	
	public Imagen(String archivo)
	{
		File file= new File(archivo);
		try {
			BufferedImage image = ImageIO.read(file);
			ancho = image.getWidth();
			alto = image.getHeight();
			pixels = new Pixel[alto][ancho];
			for(int i=0; i<alto; i++)
			{
				for(int j=0; j<ancho; j++)
				{
					pixels[i][j]=new Pixel(image.getRGB(j, i));
				}
			}
		} catch (IOException e) {
			System.err.println("No se encontró el archivo " + archivo);
			System.exit(0);
		}
	}


	/**
	 * Guarda la imagen en un archivo según el formato indicado
	 * @param archivo El nombre de archivo, e.g., "lena.bmp"
	 * @param formato El formato de imagen: "bmp", "png", "jpg", etc. 
	 */
	public void guardar(String archivo, String formato)
	{
		BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<alto; i++)
		{
			for(int j=0; j<ancho; j++)
				image.setRGB(j,i, pixels[i][j].aInt());
		}
		
		try {
			ImageIO.write(image, formato, new File(archivo));
		} catch (IOException e) {
			System.err.println("Error al guardar en el archivo " + archivo);
			e.printStackTrace();
		}
	}
	
	public void enrojecer(int cant){
		
		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length; i2++){
				
				if (this.pixels[i][i2].rojo+cant>255){
					
					this.pixels[i][i2].rojo = 255;
				}
				
				else{
					
					this.pixels[i][i2].rojo+=cant;
				}
			}
		}
	}
	
	public void desenrojecer(int cant){
		
		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length; i2++){
				
				if (this.pixels[i][i2].rojo-cant<0){
					
					this.pixels[i][i2].rojo = 0;
				}
				
				else{
					
					this.pixels[i][i2].rojo-=cant;
				}
			}
		}
	}
	
	
	public void aumentarBrillo(int cant){
		
		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length; i2++){
				
				if (this.pixels[i][i2].rojo+cant>255){
					
					this.pixels[i][i2].rojo = 255;
				}
				
				else{
					
					this.pixels[i][i2].rojo+=cant;
				}
				
				if (this.pixels[i][i2].verde+cant>255){
					
					this.pixels[i][i2].verde = 255;
				}
				
				else{
					
					this.pixels[i][i2].verde+=cant;
				}
				
				if (this.pixels[i][i2].azul+cant>255){
					
					this.pixels[i][i2].azul = 255;
				}
				
				else{
					
					this.pixels[i][i2].azul+=cant;
				}
			}
		}
	}
	
	public void aGrises(){
		
		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length; i2++){
				
				this.pixels[i][i2].rojo = (int) ((this.pixels[i][i2].rojo* 0.3) + (this.pixels[i][i2].verde*0.6) + (this.pixels[i][i2].azul*0.1));
				this.pixels[i][i2].verde = this.pixels[i][i2].rojo;
				this.pixels[i][i2].azul = this.pixels[i][i2].rojo;
			}
		}
	}

	public void invertir(){
		
		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length; i2++){
				
				this.pixels[i][i2].rojo = 255-this.pixels[i][i2].rojo;
				this.pixels[i][i2].verde = 255-this.pixels[i][i2].verde;
				this.pixels[i][i2].azul = 255-this.pixels[i][i2].azul;
			}
		}
	}
	
	public void espejar(){

		for (int i = 0; i<this.pixels.length; i++){
			
			for (int i2 = 0; i2<this.pixels[i].length/2; i2++){
				
				int rojoAnt = this.pixels[i][i2].rojo;
				this.pixels[i][i2].rojo = this.pixels[i][this.pixels[i].length-1-i2].rojo;
				this.pixels[i][this.pixels[i].length-1-i2].rojo = rojoAnt;
				
				int verdeAnt = this.pixels[i][i2].verde;
				this.pixels[i][i2].verde = this.pixels[i][this.pixels[i].length-1-i2].verde;
				this.pixels[i][this.pixels[i].length-1-i2].verde = verdeAnt;
				
				int azulAnt = this.pixels[i][i2].azul;
				this.pixels[i][i2].azul = this.pixels[i][this.pixels[i].length-1-i2].azul;
				this.pixels[i][this.pixels[i].length-1-i2].azul = azulAnt;
			}
		}
	}
	
	public void girarDerecha(){
		
		Pixel[][] matriz;
		
		matriz = new Pixel[this.ancho][this.alto];

		for (int i = 0; i<matriz.length; i++){
			
			for (int i2 = 0; i2<matriz[i].length; i2++){
				
				matriz[i][i2] = this.pixels[this.alto-1-i2][i];

			}
		}
	}
	
	public void cortarImagen(int dim){
				
		int altoAbs = alto/dim;
		int anchoAbs = ancho/dim;
		
		int iniX = 0;
		int iniY = 0;
		int finX = anchoAbs;
		int finY = altoAbs;
		
		int cont = 0;
		
		for(int h=0;h<dim*dim;h++){
		
			BufferedImage image = new BufferedImage(anchoAbs, altoAbs, BufferedImage.TYPE_INT_RGB);
			
			int i2 = 0;
			for(int i=iniY; i<finY; i++)
			{
				int j2 = 0;
				for(int j=iniX; j<finX; j++){
					image.setRGB(j2,i2, pixels[i][j].aInt());
					j2++;
				}
				i2++;
			}
			
			try {
				ImageIO.write(image, "jpg", new File("part"+h+".jpg"));
			} catch (IOException e) {
				System.err.println("Error al guardar en el archivo " + "part"+h+".jpg");
				e.printStackTrace();
			}
			
			iniX += anchoAbs;
			finX += anchoAbs;
			
			cont++;
			
			if(cont>=dim){
				
				iniY += altoAbs;
				finY += altoAbs;
				iniX = 0;
				finX = anchoAbs;
				cont = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		
		Imagen imagen = new Imagen("F:/PC/Desktop/wea2.jpg");
		
		imagen.cortarImagen(3);
	}
}
	
