package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

class ComparadorProcessoPrioridade implements Comparator<Processo> {
	@Override
	public int compare(Processo p1, Processo p2) {
		return p2.getPrioridade() - p1.getPrioridade();
	}
}

public class Preemptivo extends Algoritmo {
	private static final ComparadorProcessoPrioridade COMPARADOR = new ComparadorProcessoPrioridade();

	public Preemptivo(List<Processo> processos) {
		super(processos);
		for (Processo p : processos) {
			p.tempos.setTempoEStemp(p.getTempoES());
			p.tempos.setTempoComputacaotemp(p.getTempoComputacao());
		}
	}

	@Override
	public void escalonar() {
		while (processos.size() != terminados.size()) {
			Collections.sort(prontos, COMPARADOR);
			esperar();
			try {
				atual = prontos.get(0);
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (executando == null) {
						executando = prontos.remove(0);
						if (executando.tempos.resposta == -1) {
							executando.tempos.resposta = executando.tempos.pronto;
						}
						executando.setEstado(EstadoProcesso.EXECUTANDO);
					}
					if (executando.vaiFazerES()) {
						executando.setEstado(EstadoProcesso.BLOQUEADO);
						bloqueados.add(executando);
						esperar();
						executando = null;
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
								bloqueados.get(i).tempos
										.setTempoEStemp(bloqueados.get(i)
												.getTempoES());
								bloqueados.remove(i);
							}
						}
						continue;
					}
					if (executando.getPrioridade() < atual.getPrioridade()) {
						prontos.add(executando);
						executando = atual;
						prontos.remove(atual);
					}
					executando.tempos.tempoRetorno++;
					executando.tempos.executando++;
					executando.tempos.decrementarTempoComputacaotemp();
					if (executando.tempos.getTempoComputacaotemp() <= 0) {
						terminados.add(executando);
						executando.setEstado(EstadoProcesso.TERMINADO);
						executando = null;
					}
				}
				catch (Exception e) {
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
							bloqueados.get(i).tempos.setTempoEStemp(bloqueados
									.get(i).getTempoES());
							bloqueados.remove(i);
						}
					}
				}
			}
		}
	}
}