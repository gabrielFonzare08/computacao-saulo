package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

public class Preemptivo extends Algoritmo {

	public Preemptivo(List<Processo> processos) {
		super(processos);
		// TODO Auto-generated constructor stub
	}

	class ProcessComparator implements Comparator<Processo> {
		@Override
		public int compare(Processo p1, Processo p2) {
			return p1.getPrioridade() - p2.getPrioridade();
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
																		// tempo
																		// de
																		// bloqueado

			}

			executando.tempos.executando += executando.getTempoComputacao(); // somar
																				// tempo
																				// de
																				// executando.
			executando.setEstado(EstadoProcesso.TERMINADO); // termina!

			for (Processo p : prontos) {
				p.tempos.pronto += 1; // incrementar tempo de pronto em um
										// ciclo;
			}
		}
	}
}
