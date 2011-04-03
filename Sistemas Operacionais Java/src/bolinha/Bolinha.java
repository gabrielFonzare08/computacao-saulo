package bolinha;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bolinha implements Runnable {

	private Tela tela;
	private Point coordenadas;
	private Color cor;
	
	private boolean paraDireita = false;
	private boolean paraCima = false;
	private boolean parada = true;
	
	final int VELOCIDADE = 15;
	
	public Bolinha(Tela tela, Point coordenadas) {
		this.tela = tela;
		this.coordenadas = coordenadas;
	}
	
	public boolean isParada() {
		return parada;
	}
	
	// Pára a bola se ela estiver em movimento e movimenta a mesma se parada.
	public void alternar() {
		parada = !parada;
	}

	public void mover() {
		
		// atualizar as posições
		coordenadas.x += paraDireita ? VELOCIDADE : -VELOCIDADE;
		coordenadas.y += paraCima    ? -VELOCIDADE : VELOCIDADE;
		
		
		// limitar horizontalmente
		if (coordenadas.x >= tela.getWidth() - 30) {
			paraDireita = false;
		} else if (coordenadas.x <= 0) {
			paraDireita = true;
		}
		
		// limitar verticalmente
		if(coordenadas.y < 30) {
			paraCima = false;
		} else if(coordenadas.y >= tela.getHeight() - 30) {
			paraCima = true;
		}

		
	}

	@Override
	public void run() {
		while (tela.isVisible()) {
			
			synchronized (this) {
				try {
					
					if(parada) {
						cor = Color.BLUE;
						paint(tela.getGraphics());
						wait(); // ficar desenhando pra q!?
					} else {
						mover();
						cor = Color.RED;
						paint(tela.getGraphics());
						wait(60); // espera cronometrada da bolinha
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void paint(Graphics g) {
		
		// limpar a tela com um retangulo
		g.setColor(Color.WHITE); 
		g.fillRect(0, 0, tela.getWidth(), tela.getHeight());
		
		// pintar a bolinha
		g.setColor(cor); 
		g.fillOval(coordenadas.x, coordenadas.y, 20, 20);
	}
}