public class BestFit extends AlgoritmoAlocacaoMemoria {

	@Override
	public void add(Processo processo) throws SemMemoriaException {
		Segmento segmento = new Segmento(processo.getId());
		segmento.setOffset(processo.getTamanho());
		
		int bestFit = processo.getTamanho();
		int indice  = 0;
		for(int i = 0; i < segmentos.size(); i++) {
			Segmento s = segmentos.get(0);
			if(s.getTamanho() >= bestFit) {
				bestFit = s.getTamanho();
				indice = i;
			}
		}
		
		
	}

	@Override
	public void remover(Processo processo) throws SemMemoriaException {
		// TODO Auto-generated method stub
		
	}

	
}
