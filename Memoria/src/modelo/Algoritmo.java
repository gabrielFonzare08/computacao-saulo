package modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Algoritmo {

	public static final int BEST_FIT = 0;
	public static final int FIRST_FIT = 1;
	public static final int NEXT_FIT = 2;
	public static final int WORST_FIT = 4;

	protected LinkedList<Segmento> memoria_;
	protected LinkedList<Segmento> terminados;

	protected ArrayList<Segmento> memoria;
	protected ArrayList<Segmento> buffer;

	public double fragmentacao = 0.0;
	public long livres = 0;
	public long ciclos = 0;

	public Algoritmo() {

		memoria = new ArrayList<Segmento>();
		memoria.add(Segmento.vazio(20));

		buffer = new ArrayList<Segmento>();
	}

	/**
	 * Retorna o numero de segmentos na memoria.
	 * 
	 * @return o tamanho da memoria, exatamente o numero de segmentos.
	 * **/
	public long tamanhoMemoria() {
		return memoria_.size();
	}

	/**
	 * Retorna uma lista contendo todos os segmentos, livres e ocupados.
	 * */
	public ArrayList<Segmento> getMemoria() {
		return memoria;
	}

	public ArrayList<Segmento> getTerminados() {
		return buffer;
	}

	/**
	 * Retorna a soma de todos os segmentos livres.
	 * */
	public long tamanhoSegmentosLivres() {
		long tamanho = 0;
		for (Segmento segmento : memoria) {

			if (segmento.isLivre()) {
				tamanho += segmento.getTamanho();
			}
		}

		return tamanho;
	}

	public int segmentosLivres() {
		int contador = 0;
		for (Segmento segmento : memoria) {

			if (segmento.isLivre()) {
				contador++;
			}
		}

		return contador;
	}

	public abstract boolean adicionarProcesso(Processo p);

	public boolean removerProcesso(Processo p) {
		int indice = memoria.indexOf(p);

		if (indice != -1) {

			buffer.add(new Segmento(p));
			memoria.get(indice).setOcupado(false);
			concatenarDireita(indice);
			concatenarEsquerda(indice);

			return true;
		}

		return false;
	}

	/**
	 * Redimensiona a memoria concatenando espacos em branco a esquerda
	 * 
	 * @param indice
	 *            atual para concatenacao com o segmento anterior.
	 * */
	protected final void concatenarEsquerda(int indice) {
		try {

			Segmento atual = memoria.get(indice);
			Segmento anterior = memoria.get(indice - 1);

			if (atual.isLivre() && anterior.isLivre()) {
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

			if (atual.isLivre() && posterior.isLivre()) {
				atual.setTamanho(atual.getTamanho() + posterior.getTamanho());
				memoria.remove(indice + 1);
			}

		} catch (Exception e) {

		}
	}

	protected final boolean adicionarProcesso0(int indice, Segmento novoSegmento) {
		if (indice != -1) {

			novoSegmento.setOcupado(true);
			
			Segmento aSerSusbtituido = memoria.get(indice);
			
			memoria.set(indice, novoSegmento);
			int sobra = aSerSusbtituido.getTamanho() - novoSegmento.getTamanho();

			if (sobra > 0) {
				memoria.add(indice + 1, Segmento.vazio(sobra));
			}

			return true;
		}
		
		return false;
	}
}
