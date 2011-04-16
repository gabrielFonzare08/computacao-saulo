package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

/**
 * Implementa&ccedil;&atilde;o do algoritmo RoundRobin. O algoritmo deve
 * obedecer as seguintes regras:
 * <ul>
 * <li>Baseado em fila circular.</li>
 * <li>Cada processo possui uma quantidade de tempo de execu&ccedil;&atilde;o, o
 * seu quantum.</li>
 * <li>Ao final do quantum, se o processo ainda estiver em
 * execu&ccedil;&atilde;o, sofrer&aacute; preemp&ccedil;&atilde;o.</li>
 * <li>Tamanho do quantum interfere no desempenho.</li>
 * </ul>
 * 
 * */
public class RoundRobin extends Algoritmo {
	public RoundRobin(List<Processo> processos) {
		super(processos);
	}

	@Override
	public void escalonar() {
		while (processos.size() != terminados.size()) {
			esperar();
			try {
				if (executando.tempos.getTempoComputacaotemp() == executando
						.getQuantum() || executando == null) {
					executando.tempos.setTempoComputacaotemp(0);
					executando = prontos.remove(0);

				}
				
				if (executando.tempos.resposta == -1) {
					executando.tempos.resposta = executando.tempos.pronto;
				}

				executando.setEstado(EstadoProcesso.EXECUTANDO);

				if (!executando.isBloqueado()) {
					if (executando.vaiFazerES()) {
						executando.setEstado(EstadoProcesso.BLOQUEADO);
						bloqueados.add(executando);
						esperar();
					}
				} else {
					executando.tempos.incrementarTempoComputacaotemp();
					executando.tempos.tempoComputacaoaux++;
				}


				if (executando.tempos.tempoComputacaoaux == executando.getTempoComputacao()) {
					terminados.add(executando);
					executando.setEstado(EstadoProcesso.TERMINADO);
				}

				if (executando.tempos.getTempoComputacaotemp() == executando
						.getQuantum()) {
					executando = null;
					continue;
				}

				executando.tempos.tempoRetorno++;
				
				for (Processo p : prontos) {
					p.tempos.pronto++;
					p.tempos.tempoRetorno++;
				}

				for (int i = 0; i < bloqueados.size(); i++) {
					bloqueados.get(i).tempos.tempoRetorno++;
					bloqueados.get(i).tempos.decrementaTempoEStemp();
					bloqueados.get(i).tempos.bloqueado++;
					if (bloqueados.get(i).tempos.getTempoEStemp() == 0) {
						prontos.add(bloqueados.get(i));
						bloqueados.get(i).tempos.setTempoEStemp(bloqueados.get(
								i).getTempoES());
						bloqueados.remove(i);
					}
				}

			} catch (Exception e) {
				incrementaTempoCpuOciosa();
			} finally {
				for (Processo p : prontos) {
					p.tempos.tempoRetorno++;
					p.tempos.pronto++;
				}
				for (int i = 0; i < bloqueados.size(); i++) {
					bloqueados.get(i).tempos.tempoRetorno++;
					bloqueados.get(i).tempos.decrementaTempoEStemp();
					bloqueados.get(i).tempos.bloqueado++;
					if (bloqueados.get(i).tempos.getTempoEStemp() == 0) {
						prontos.add(bloqueados.get(i));
						bloqueados.get(i).tempos.setTempoEStemp(bloqueados.get(
								i).getTempoES());
						bloqueados.remove(i);
					}
				}
			}
		}
	}
}