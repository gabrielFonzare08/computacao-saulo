package modelo;

import java.util.ArrayList;



public abstract class Algoritmo {
	
	public static final int BEST_FIT = 0;
	public static final int FIRST_FIT = 1;
	public static final int NEXT_FIT = 2;
	public static final int WORST_FIT = 4;
	
	protected ArrayList<Segmento> memoria;
	protected ArrayList<Segmento> buffer;
	
	public Algoritmo() {
		memoria = new ArrayList<Segmento>();
		memoria.add(Segmento.vazio(128));
		
		buffer = new ArrayList<Segmento>();
	}
	
	public ArrayList<Segmento> getMemoria() {
		return memoria;
	}
	
	public ArrayList<Segmento> getBuffer() {
		return buffer;
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
		if(indice <= 0 || indice > memoria.size() -1)  { // n existem mais segmentos
			return;
		}
		
		Segmento anterior = memoria.get(indice -1);
		Segmento atual = memoria.get(indice);
		
		if(anterior.isLivre() && atual.isLivre()) {
			anterior.setTamanho(anterior.getTamanho() + atual.getTamanho());
			memoria.remove(indice);
		}	
	}
	
	protected final void concatenarDireita(int indice) {
		if(indice <= 0 || indice >= memoria.size())  { // n existem mais segmentos
			return;
		}
		concatenarEsquerda(indice + 1);
	}
}
