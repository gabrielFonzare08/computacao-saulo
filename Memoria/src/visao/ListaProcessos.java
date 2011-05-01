package visao;
import java.util.ArrayList;

import modelo.Processo;

public class ListaProcessos {

	private ArrayList<Processo> processos;

	public ListaProcessos(int qtdeProcessos, int tipoprocesso) {

		processos = new ArrayList<Processo>();
		for (int i = 0; i < qtdeProcessos; i++) {
			switch (tipoprocesso) {
			case 0:
				processos.add(gerarProcessoPequeno());
				break;
			case 1:
				processos.add(gerarProcessoMedio());
				break;
			case 2:
				processos.add(gerarProcessoGrande());
				break;
			case 3:
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
		p.setTempoExecucao((10 + (int) Math.round((Math.random() * 23))));
		p.setTamanho(2 + (int) Math.round((Math.random() * 2)));
		return p;
	}

	public Processo gerarProcessoMedio() {
		Processo p = new Processo();
		p.setTempoExecucao((10 + (int) Math.round((Math.random() * 23))));
		p.setTamanho(6 + (int) Math.round((Math.random() * 2)));
		return p;
	}

	public Processo gerarProcessoGrande() {
		Processo p = new Processo();
		p.setTempoExecucao((10 + (int) Math.round((Math.random() * 23))));
		p.setTamanho(12 + (int) Math.round((Math.random() * 8)));
		return p;
	}
	
	public Object[]  toArray() {
		return processos.toArray();
	}
	
	public boolean isEmpty() {
		return processos.isEmpty();
	}
	
	public void remove(int i) {
		processos.remove(i);
	}
}
