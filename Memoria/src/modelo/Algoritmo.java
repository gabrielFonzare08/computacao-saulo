package modelo;



import java.util.ArrayList;
import java.util.LinkedList;



public abstract class Algoritmo {
	
	protected LinkedList<Segmento> memoria;
	
	public double fragmentacao = 0.0;
	public long livres = 0;
	public long ciclos = 0;
	
	public Algoritmo() {
		memoria = new LinkedList<Segmento>();
		memoria.add(Segmento.vazio(128));
	}
	
	public long segmentosLivres() {
		long memoriaLivre = 0;
		for(Segmento segmento : memoria) {
			if(segmento.isLivre()) {
				memoriaLivre ++;
			}
		}
		
		return memoriaLivre;
	}
	
	
	public long tamanhoSegmentosLivres() {
		long memoriaLivre = 0;
		for(Segmento segmento : memoria) {
			if(segmento.isLivre()) {
				memoriaLivre += segmento.getTamanho();
			}
		}
		
		return memoriaLivre;
		
	}
	
	
	
	
	
	public abstract boolean adicionarProcesso(Processo p);
	
	public LinkedList<Segmento> getMemoria() {
		return memoria;
	}
	
	public boolean removerProcesso(Processo p) {
		int indice = memoria.indexOf(p);
		
		if(indice != -1) {
			memoria.get(indice).setOcupado(false);
			concatenarDireita(indice);
			concatenarEsquerda(indice);
			
			return true;
		}	
		
		return false;
	}
	
	/**
	 * Redimensiona a memoria concatenando espaços em branco a esquerda
	 * @param indice atual para concatenacao com o segmento anterior.
	 * */
	protected final void concatenarEsquerda(int indice) {
		try {
			Segmento anterior = memoria.get(indice - 1);
			Segmento atual = memoria.get(indice);
			
			if(anterior.isLivre() && atual.isLivre()) {
				anterior.setTamanho(anterior.getTamanho() + atual.getTamanho());
				memoria.remove(indice);
			}
		} catch (Exception e) {}		
	}	
	
	protected final void concatenarDireita(int indice) {
		try {
			
			Segmento atual = memoria.get(indice);
			Segmento posterior = memoria.get(indice + 1);
			
			if(posterior.isLivre() && atual.isLivre()) {
				posterior.setTamanho(posterior.getTamanho() + atual.getTamanho());
				memoria.remove(indice);
			}
			
		} catch (Exception e) {}
	}
}