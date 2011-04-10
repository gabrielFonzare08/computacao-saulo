

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

public class TestePreemptivo extends Algoritmo {

	public TestePreemptivo(List<Processo> processos) {
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

			executando = prontos.remove(0); // pega o proximo processo pronto
			executando.setEstado(EstadoProcesso.EXECUTANDO);

			if (executando.vaiFazerES()) { // vai fazer es?
				executando.setEstado(EstadoProcesso.BLOQUEADO);
				executando.tempos.bloqueado += executando.getTempoES(); // incrementar
																		// de
																		// bloqueado
				
				executando.setEstado(EstadoProcesso.PRONTO);  // recoloca o processo na lista de prontos
				prontos.add(executando);
				
				
				continue;	//coloca o próximo processo da fila como executando enquanto o atual está bloqueado

			}

			executando.tempos.executando += executando.getTempoComputacao(); // somar
																				// tempo
																				// de
																				// executando.
			executando.setEstado(EstadoProcesso.TERMINADO); // termina!
			terminados.add(executando);
			
			for (Processo p : prontos) {
				p.tempos.pronto += 1;   // incrementar tempo de pronto em um
										// ciclo;
			}
		}
	}
}
