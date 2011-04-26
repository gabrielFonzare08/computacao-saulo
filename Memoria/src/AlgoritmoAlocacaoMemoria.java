import java.util.ArrayList;


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

}
