import java.util.ArrayList;


public class ListaSegmentos {
	
	public static final int MAXIMO_MEMORIA = 128;
	
	private ArrayList<Segmento> segmentos;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5321023702032450498L;
	
	public ListaSegmentos() {
		
		segmentos = new ArrayList<Segmento>();
		
		Segmento inicial = new Segmento();
		inicial.setInicio(0);
		inicial.setOcupado(false);
		inicial.setOffset(MAXIMO_MEMORIA);
		
		segmentos.add(inicial);
	}
	
	
}
