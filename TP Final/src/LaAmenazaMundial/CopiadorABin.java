package LaAmenazaMundial;

public class CopiadorABin {
        
	  public static void iniciar() {
		  
		  CopiarFicheros.copiar("fondo.jpg", "bin/fondo.jpg");
		  CopiarFicheros.copiar("gano.jpg", "bin/gano.jpg");
		  CopiarFicheros.copiar("imagen.png", "bin/imagen.png");
		  
		  for(int i=0;i<10;i++){
			  
			  if(i<4) CopiarFicheros.copiar("colesterol"+i+".png", "bin/colesterol"+i+".png");
			  
			  if(i<6) CopiarFicheros.copiar("fondoMalo"+i+".jpg", "bin/fondoMalo"+i+".jpg");
		  
			  CopiarFicheros.copiar("anticuerpo"+i+".png", "bin/anticuerpo"+i+".png");
			  CopiarFicheros.copiar("celula"+i+".png", "bin/celula"+i+".png");
			  CopiarFicheros.copiar("virus"+i+".png", "bin/virus"+i+".png");
		  }
	  }
}
