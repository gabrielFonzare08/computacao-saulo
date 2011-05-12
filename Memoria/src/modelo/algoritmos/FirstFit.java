package modelo.algoritmos;


import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class FirstFit extends Algoritmo {

	@Override
	public boolean adicionarProcesso(Processo p) {
		Segmento novoSegmento = new Segmento(p);

		int indiceSubstituicao = -1;

		for (int i = 0; i < memoria.size(); i++) {
			Segmento atual = memoria.get(i);

			if (atual.isLivre() && novoSegmento.getTamanho() <= atual.getTamanho()) {
				indiceSubstituicao = i;
				break;				
			}
		}

		if (indiceSubstituicao != -1) {
			Segmento aSerSubstituido = memoria.get(indiceSubstituicao);
			int sobra = aSerSubstituido.getTamanho() - novoSegmento.getTamanho();
			
			
			if(sobra == 0) {
				novoSegmento.setOcupado(true);
				memoria.set(indiceSubstituicao, novoSegmento);
			} else {
				memoria.get(indiceSubstituicao).setTamanho(sobra);
				memoria.add(indiceSubstituicao, novoSegmento);
			}
			return true;			
		}

		return false;
	}


}
