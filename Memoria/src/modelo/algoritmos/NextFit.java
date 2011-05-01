package modelo.algoritmos;

import javax.swing.text.Segment;

import modelo.Algoritmo;
import modelo.Processo;
import modelo.Segmento;

public class NextFit extends Algoritmo {
	
	private int cursor = 0;
	
	@Override
	public boolean adicionarProcesso(Processo p) throws SemMemoria {
		int aux = cursor;
		boolean ciclo = false;
		
		while (!ciclo) {
			Segmento atual = memoria.get(cursor);
			if(atual.isLivre() && ....) {
				
			}
			
			cursor = (cursor + 1) % memoria.size();
			
			
			if(cursor == aux) {
				ciclo = false;
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}

}
