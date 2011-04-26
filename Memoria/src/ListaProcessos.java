import java.util.ArrayList;

public class ListaProcessos {

	private ArrayList<Processo> processos;

	public ListaProcessos(int qtdeProcessos, TipoProcesso tipoprocesso) {

		processos = new ArrayList<Processo>();
		for (int i = 0; i < qtdeProcessos; i++) {
			switch (tipoprocesso) {
			case PEQUENO:
				processos.add(gerarProcessoPequeno());
				break;
			case MEDIO:
				processos.add(gerarProcessoMedio());
				break;
			case GRANDE:
				processos.add(gerarProcessoGrande());
				break;
			case VARIADO:
				processos.add(gerarProcessoPequeno());
				i++;
				if (i < qtdeProcessos)
					break;
				processos.add(gerarProcessoMedio());
				i++;
				if (i < qtdeProcessos)
					break;
				processos.add(gerarProcessoGrande());
				i++;
				break;
			}
		}
	}

	public Processo gerarProcessoPequeno() {
		Processo p = new Processo();
		p.setTamanho(2 + (int) Math.round((Math.random() * 2)));
		return p;
	}

	public Processo gerarProcessoMedio() {
		Processo p = new Processo();
		p.setTamanho(6 + (int) Math.round((Math.random() * 2)));
		return p;
	}

	public Processo gerarProcessoGrande() {
		Processo p = new Processo();
		p.setTamanho(12 + (int) Math.round((Math.random() * 8)));
		return p;
	}

}
