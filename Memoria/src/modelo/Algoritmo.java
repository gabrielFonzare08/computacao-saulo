package modelo;

import java.util.ArrayList;



public abstract class Algoritmo {
	
	public class SemMemoria extends Exception {
		
	}
	
	protected ArrayList<Segmento> memoria;
	
	public Algoritmo() {
		memoria = new ArrayList<Segmento>();
		memoria.add(Segmento.vazio(128));
	}
	
	public abstract boolean adicionarProcesso(Processo p) throws SemMemoria;
	//public abstract boolean removerProcesso(Processo p) throws SemMemoria;
	
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
		if(indice <= 0 || indice > memoria.size())  { // n existem mais segmentos
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
