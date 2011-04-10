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

				executando.setEstado(EstadoProcesso.PRONTO); // recoloca o
				// processo
				// na lista
				// de
				// prontos
				prontos.add(executando);

				continue; // coloca o próximo processo da fila como
				// executando enquanto o atual está bloqueado

			}

			// tempo
			// de
			// executando.
			executando.setEstado(EstadoProcesso.TERMINADO); // termina!
			terminados.add(executando);

		}
		terminados.get(0).tempos.pronto = 0;
		for (int i = 1; i < processos.size(); i++) {
			terminados.get(i).tempos.pronto = terminados.get(i - 1).getTempoComputacao() + terminados.get(i-1).tempos.pronto;
		}
	}
}
