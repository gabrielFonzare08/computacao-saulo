package filosofos;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Renderizador implements Runnable {
	
	private static Image pensando = new ImageIcon(Renderizador.class.getResource("pensando.png")).getImage(); 
	private static Image comFome = new ImageIcon(Renderizador.class.getResource("comFome.png")).getImage();
	private static Image comendo = new ImageIcon(Renderizador.class.getResource("comendo.png")).getImage();
	
	private static Point [] coordenadas = {
		new Point(250, 100), 
		new Point(400, 150), new Point(350, 300),
		new Point(150, 300), new Point(100, 150)
	};
	
	private static int i = 0;
	
	private Filosofo filosofo;
	private Point ponto;

	public Renderizador(Filosofo filosofo) {
		this.filosofo = filosofo;
		this.ponto = coordenadas[i];
		i = (i + 1) % coordenadas.length;
	}
	
	void paint(Graphics g) {
		
		Image icone = null;
		
		if(filosofo.isComendo()) {
			icone = comendo;
		} else if(filosofo.isComFome()) {
			icone = comFome;
		} else {
			icone = pensando;
		}
		
		g.drawImage(icone, ponto.x, ponto.y, 128, 128, null);
	}
	
	@Override
	public void run() {
		Janela mesa = Janela.getInstance(null);
		
		while(mesa.isVisible()) {
			paint(Janela.getInstance(null).getGraphics());
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}