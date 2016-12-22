package Practica1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Dibujador extends Panel {
	BufferedImage image;
	Circulo c1, c2;
	Rectangle2D enclosingRect;
	
	void enclosingRect()
	{
		enclosingRect = new Rectangle2D.Double();
		enclosingRect.add(new Point2D.Double(c1.centro.x-c1.radio,c1.centro.y));
		enclosingRect.add(new Point2D.Double(c1.centro.x+c1.radio,c1.centro.y));
		enclosingRect.add(new Point2D.Double(c1.centro.x,c1.centro.y+c1.radio));
		enclosingRect.add(new Point2D.Double(c1.centro.x,c1.centro.y-c1.radio));
		enclosingRect.add(new Point2D.Double(c2.centro.x-c2.radio,c2.centro.y));
		enclosingRect.add(new Point2D.Double(c2.centro.x+c2.radio,c2.centro.y));
		enclosingRect.add(new Point2D.Double(c2.centro.x,c2.centro.y+c2.radio));
		enclosingRect.add(new Point2D.Double(c2.centro.x,c2.centro.y-c2.radio));
		double eh = 0.01 * enclosingRect.getWidth();
		double ev = 0.01 * enclosingRect.getHeight();
		enclosingRect.setRect(enclosingRect.getX()-eh, enclosingRect.getY()-ev, enclosingRect.getWidth()+2*eh,enclosingRect.getHeight()+2*ev);
	}
	
	public void paint(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, null);
		} else {
			paintCirculo(g, c1);
			paintCirculo(g, c2);
		}
	}
	
	private void paintCirculo(Graphics g, Circulo c)
	{
		int x = (int) ((c.centro.x - enclosingRect.getX()-c.radio)/enclosingRect.getWidth() * getSize().getWidth());
		int y = (int) ((c.centro.y - enclosingRect.getY()-c.radio)/enclosingRect.getHeight() * getSize().getHeight());
		int height = (int) (c.radio * 2 /Math.max(enclosingRect.getHeight(),enclosingRect.getWidth()) * getSize().getHeight());
		int width = (int) (c.radio * 2 / Math.max(enclosingRect.getHeight(),enclosingRect.getWidth()) * getSize().getWidth());
		g.drawOval(x, y, width, height);
	}

	private void cargar(Imagen bmp) {
		image = new BufferedImage(bmp.ancho, bmp.alto,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < bmp.alto; i++) {
			for (int j = 0; j < bmp.ancho; j++) {
				int rgb = bmp.pixels[i][j].aInt();
				image.setRGB(j, i, rgb);
			}
		}
	}

	private void cargar(Circulo c1, Circulo c2) {
		this.image = null;
		this.c1 = c1;
		this.c2 = c2;
		enclosingRect();
	}

	public static void dibujar(Imagen bmp) {
		JFrame frame = new JFrame("Ver imagen");
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent we){
		        System.exit(0);
		      }
		    });
		Dibujador dibujador = new Dibujador();
		dibujador.cargar(bmp);
		frame.getContentPane().add(dibujador);
		frame.setSize(bmp.ancho, bmp.alto);
		frame.setVisible(true);
	}

	public static void dibujar(Circulo c1, Circulo c2) {
		JFrame frame = new JFrame("Ver imagen");
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent we){
		        System.exit(0);
		      }
		    });
		Dibujador dibujador = new Dibujador();
		dibujador.cargar(c1, c2);
		frame.getContentPane().add(dibujador);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

}