package modelo.algoritmos;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class BestFit extends Algoritmo {

	@Override
	public boolean adicionarProcesso(Processo p) {
		Segmento novo = new Segmento(p);

		int melhor = novo.getTamanho();
		int indiceSubstituicao = -1;
		boolean espacoEncontrado = false;

		for (int i = 0; i < memoria.size(); i++) {
			Segmento atual = memoria.get(i);

			if (atual.isLivre() && novo.getTamanho() <= atual.getTamanho()
					&& melhor >= atual.getTamanho()) {
				if (melhor == atual.getTamanho()) {
					melhor = atual.getTamanho();
					indiceSubstituicao = i;
					espacoEncontrado = true;
					
				}
				if (melhor < atual.getTamanho() && espacoEncontrado == false) {
					melhor = atual.getTamanho();
					indiceSubstituicao = i;
				}
			}
		}

		if (espacoEncontrado == false) {
		}

		if (indiceSubstituicao != -1) {

			Segmento encontrado = memoria.get(indiceSubstituicao);
			novo.setOcupado(true);
			// mesmo tamanho so trocar com o segmento
			if (novo.getTamanho() == encontrado.getTamanho()) {
				memoria.set(indiceSubstituicao, novo);

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
