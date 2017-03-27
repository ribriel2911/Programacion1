package LaAmenazaMundial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopiarFicheros {
	
	  public static void copiar(String ori,String des) {

          
          File origen = new File(ori);
          File destino = new File(des);

          try {
                  InputStream in = new FileInputStream(origen);
                  OutputStream out = new FileOutputStream(destino);
                          
                  byte[] buf = new byte[1024];
                  int len;

                  while ((len = in.read(buf)) > 0) {
                          out.write(buf, 0, len);
                  }
          
                  in.close();
                  out.close();
          } catch (IOException ioe){
                  ioe.printStackTrace();
          }
          

  }

}
