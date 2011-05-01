package modelo;

import java.util.ArrayList;



public abstract class Algoritmo {
	
	public static final int BEST_FIT = 0;
	public static final int FIRST_FIT = 1;
	public static final int NEXT_FIT = 2;
	public static final int WORST_FIT = 4;
	
	protected ArrayList<Segmento> memoria;
	protected ArrayList<Segmento> buffer;
	
	public double fragmentacao = 0.0;
	public long ciclos = 0;
	
	
	public Algoritmo() {
		memoria = new ArrayList<Segmento>();
		memoria.add(Segmento.vazio(20));
		
		buffer = new ArrayList<Segmento>();
	}
	
	public ArrayList<Segmento> getMemoria() {
		return memoria;
	}
	
	public ArrayList<Segmento> getTerminados() {
		return buffer;
	}
	
	public int segmentosLivres() {
		int contador = 0;
		for(Segmento segmento : memoria) {
			
			if(segmento.isLivre()) {
				contador++;
			}
		}
		
		return contador;
	}
	
	public abstract boolean adicionarProcesso(Processo p);
	
	public boolean removerProcesso(Processo p) {
		int indice = memoria.indexOf(p);
		
		if(indice != -1) {
			
			buffer.add(new Segmento(p));
			memoria.get(indice).setOcupado(false);
			concatenarDireita(indice);
			concatenarEsquerda(indice);
			
			return true;
		}	
		
		return false;
	}
	
	/**
	 * Redimensiona a memoria concatenando espa�os em branco a esquerda
	 * @param indice atual para concatenacao com o segmento anterior.
	 * */
	protected final void concatenarEsquerda(int indice) {
//		IF(INDICE <= 0 || INDICE > MEMORIA.SIZE() -1)  { // N EXISTEM MAIS SEGMENTOS
//			RETURN;
//		}
//		
//		SEGMENTO ANTERIOR = MEMORIA.GET(INDICE -1);
//		SEGMENTO ATUAL = MEMORIA.GET(INDICE);
//		
//		IF(ANTERIOR.ISLIVRE() && ATUAL.ISLIVRE()) {
//			ANTERIOR.SETTAMANHO(ANTERIOR.GETTAMANHO() + ATUAL.GETTAMANHO());
//			MEMORIA.REMOVE(INDICE);
//		}
		
		try {
			
			Segmento atual = memoria.get(indice);
			Segmento anterior = memoria.get(indice -1);
			
			if(atual.isLivre() && anterior.isLivre()) {
				anterior.setTamanho(anterior.getTamanho() + atual.getTamanho());
				memoria.remove(indice);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	protected final void concatenarDireita(int indice) {
		
		try {
			Segmento atual = memoria.get(indice);
			Segmento posterior = memoria.get(indice + 1);
			
			if(atual.isLivre() && posterior.isLivre()) {
				atual.setTamanho(atual.getTamanho() + posterior.getTamanho());			
				memoria.remove(indice + 1);				
			}			
			
		} catch (Exception e) {
			
		}
	}
}
