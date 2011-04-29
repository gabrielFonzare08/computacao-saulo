public class FirstFit extends AlgoritmoAlocacaoMemoria {

	@Override
	public void add(Processo processo) throws SemMemoriaException {
		Segmento segmento = new Segmento(processo.getId());
		segmento.setOffset(processo.getTamanho());

		for (int i = 0; i < segmentos.size(); i++) {
			Segmento s = segmentos.get(i);

			if (!s.isOcupado() && s.getTamanho() >= processo.getTamanho()) {
				// posicao alocada
				segmentos.add(i, segmento);
				segmento.setOcupado(true);
				segmento.setInicio(s.getInicio());

				// posicao limpa
				s.setInicio(s.getInicio() + processo.getTamanho());
				s.setOffset(s.getOffset() - processo.getTamanho());

				break;
			}
		}
	}

	@Override
	public void remover(Processo processo) throws SemMemoriaException {
		int indice = segmentos.indexOf(processo);

		if (indice > 0) {
			if (indice == 0) {
				segmentos.get(0).setOcupado(false);				
			} else if (indice == segmentos.size() - 1) {
				segmentos.get(segmentos.size() - 1).setOcupado(false);				
			}

			try {
				
				Segmento anterior	= segmentos.get(indice - 1);
				Segmento atual		= segmentos.get(indice);
				Segmento depois		= segmentos.get(indice + 1);
				
				if (anterior.isLivre()) {
					anterior.setOffset(anterior.getOffset() + atual.getOffset());
					segmentos.remove(indice);
				}
				
				if (depois.isLivre()) {
					anterior.setOffset(anterior.getOffset() + depois.getOffset());
					segmentos.remove(indice + 1);
				}
				
			} catch (Exception e) { }

		}
	}
}