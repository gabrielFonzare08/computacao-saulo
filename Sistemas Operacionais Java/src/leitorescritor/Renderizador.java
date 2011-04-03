package leitorescritor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Renderizador implements Runnable {
	
	private Point coordenadas;
	private Entidade entidade;
	private Tela tela;
	
	public Renderizador(Point coordenadas, Entidade entidade, Tela tela) {
		this.entidade = entidade;
		this.coordenadas = coordenadas;
		this.tela = tela;
	}
	
	@Override
	public void run() {
		Graphics g = tela.getGraphics();
		
		while(tela.isVisible()) {
			
			g.setColor(Color.RED);
			g.drawString("Leitor a ler", 10, 50);
			
			g.setColor(Color.BLUE);
			g.drawString("Leitor pensando", 10, 80);
			
			g.setColor(Color.GREEN);
			g.drawString("Escritor a escrever", 10, 110);
			
			g.setColor(Color.ORANGE);
			g.drawString("Escritor pensando", 10, 140);
			
			if(entidade instanceof Leitor) {
				if(((Leitor) entidade).isLendo()) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				
				g.fillRect(coordenadas.x, coordenadas.y, 20, 20);
			} else {
				if(((Escritor) entidade).isEscrevendo()) {
					g.setColor(Color.GREEN);
				} else {
					g.setColor(Color.ORANGE);
				}
				g.fillOval(coordenadas.x, coordenadas.y, 20, 20);
			}
			
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
