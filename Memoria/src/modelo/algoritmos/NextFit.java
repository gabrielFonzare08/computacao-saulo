package modelo.algoritmos;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class NextFit extends Algoritmo {

	private int cursor = -1;

	@Override
	public boolean adicionarProcesso(Processo p) {

		boolean voltaCompleta = false;
		int ponteiro = cursor;
		
		/**
		 * Variavel que recebe o segmento que poderá
		 * ou não ser inserido na memoria.
		 * */
		Segmento novo = new Segmento(p);

		while (!voltaCompleta) {
			ponteiro = (ponteiro + 1) % memoria.size();
			
			 
			if(memoria.get(ponteiro).comporta(novo)) {
				break;
			}
			
			voltaCompleta = (ponteiro == cursor);
		}
		
		
		if(!voltaCompleta) {
			cursor = ponteiro;
			int sobra = memoria.get(ponteiro).getTamanho() - novo.getTamanho();
			if(sobra == 0) {
				memoria.set(ponteiro, novo);
			} else {
				memoria.get(ponteiro).setTamanho(sobra);
				memoria.add(ponteiro, novo);
			}
			
			return true;
		}

		return false;
	}

}
