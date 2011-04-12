package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

public class Preemptivo extends Algoritmo {

	int tempoCpuOciosa;
	int tempoSimulacao;
	int tempoEspera;
	int tempoES;
	long tempoESTotal;
	long tempoComputacao;
	long tempoComputacaoTotal;

	public Preemptivo(List<Processo> processos) {
		super(processos);
		// TODO Auto-generated constructor stub
	}

	class ProcessComparator implements Comparator<Processo> {
		@Override
		public int compare(Processo p1, Processo p2) {
			return p2.getPrioridade() - p1.getPrioridade();
		}
	}

	@Override
	public void escalonar() {

		ProcessComparator comparator = new ProcessComparator();
		while (!prontos.isEmpty()) {

			Collections.sort(prontos, comparator);

			executando = prontos.remove(0);

			if (executando.vaiFazerES()) {
				executando.setEstado(EstadoProcesso.BLOQUEADO);
				executando = null;
				
			}
			
			
			tempoSimulacao++;
			for (Processo p : processos) {
				switch (p.getEstado()) {
				case PRONTO:
					p.tempos.tempoEspera++;
					break;
			
			
			terminados.add(executando);

				case BLOQUEADO:
					p.tempos.tempoES++;
					tempoESTotal = p.getTempoES() - p.tempos.tempoES;
					if (tempoESTotal == 0) {
						prontos.add(executando);
						p.setEstado(EstadoProcesso.PRONTO);

					}
					break;
				case EXECUTANDO:
					p.tempos.tempoComputacao++;
					tempoComputacaoTotal = p.getTempoComputacao()
							- p.tempos.tempoComputacao;
					if (tempoComputacaoTotal == 0) {
						processos.remove(0);
					}
					break;
				}
			}

			if (executando.vaiFazerES()) {
				executando.setEstado(EstadoProcesso.BLOQUEADO);
				executando.tempos.bloqueado = executando.getTempoES();
				executando = null;
			}
		}

	}

}