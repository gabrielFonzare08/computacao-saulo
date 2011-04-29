import java.util.ArrayList;

import javax.annotation.processing.Processor;

class SemMemoriaException extends Exception {
	
}

public abstract class AlgoritmoAlocacaoMemoria {
	
	public static final int MAXIMO_MEMORIA = 128;
	protected ArrayList<Segmento> segmentos;
	
	
	public AlgoritmoAlocacaoMemoria() {
		segmentos = new ArrayList<Segmento>();
		
		Segmento inicial = new Segmento();
		inicial.setInicio(0);
		inicial.setOcupado(false);
		inicial.setOffset(MAXIMO_MEMORIA);
		
		segmentos.add(inicial);
	}
	
	public abstract void add(Processo processo) throws SemMemoriaException;
	public abstract void remover(Processo processo)  throws SemMemoriaException;

}
