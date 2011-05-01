package modelo.algoritmos;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class WorstFit extends Algoritmo {

	@Override
	public boolean adicionarProcesso(Processo p) throws SemMemoria {
		Segmento novo = new Segmento(p);

		int pior = novo.getTamanho();
		int indiceSubstituicao = -1;
		boolean espacoEncontrado = false;

		for (int i = 0; i < memoria.size(); i++) {
			Segmento atual = memoria.get(i);

			if (atual.isLivre() && novo.getTamanho() <= atual.getTamanho()
					&& pior <= atual.getTamanho()) {
				if (pior == atual.getTamanho() && espacoEncontrado == false) {
					pior = atual.getTamanho();
					indiceSubstituicao = i;
				}
				if (pior < atual.getTamanho()) {
					pior = atual.getTamanho();
					indiceSubstituicao = i;
					espacoEncontrado = true;
				}
			}
		}

		if (espacoEncontrado == false) {
		}

		if (indiceSubstituicao != -1) {

			Segmento encontrado = memoria.get(indiceSubstituicao);

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

		return false;
	}

}
