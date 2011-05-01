package modelo.algoritmos;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class NextFit extends Algoritmo {

	private int cursor = 0;

	@Override
	public boolean adicionarProcesso(Processo p) {
		int aux = cursor;
		boolean ciclo = false;

		Segmento novo = new Segmento(p);
		int indiceSubstituicao = -1;

		while (!ciclo) {
			Segmento atual = memoria.get(cursor);
			if (atual.isLivre() && novo.getTamanho() <= atual.getTamanho()) {
				indiceSubstituicao = cursor;
			}

			if (indiceSubstituicao != -1) {

				Segmento encontrado = memoria.get(indiceSubstituicao);
				novo.setOcupado(true);

				// mesmo tamanho so trocar com o segmento
				if (novo.getTamanho() == encontrado.getTamanho()) {
					memoria.set(indiceSubstituicao, novo);
					novo.setOcupado(true);

					return true; // termina
				} else { // espaco maior!
					int sobra = encontrado.getTamanho() - novo.getTamanho();
					memoria.set(indiceSubstituicao, novo);
					memoria.add(indiceSubstituicao + 1, Segmento.vazio(sobra));
					concatenarDireita(indiceSubstituicao + 1);
					return true;
				}
			}
			cursor = (cursor + 1) % memoria.size();

			if (cursor == aux) {
				ciclo = true;
			}

		}
		return false;
	}
}
			
