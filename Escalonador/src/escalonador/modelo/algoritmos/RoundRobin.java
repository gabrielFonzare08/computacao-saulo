package escalonador.modelo.algoritmos;


import java.util.Collections;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

/**
 * Implementa&ccedil;&atilde;o do algoritmo RoundRobin.
 * O algoritmo deve obedecer as seguintes regras:
 * <ul>
 * 	<li>Baseado em fila circular.</li>
 * 	<li>Cada processo possui uma quantidade de tempo de execu&ccedil;&atilde;o, o seu quantum.</li>
 * 	<li>Ao final do quantum, se o processo ainda estiver em execu&ccedil;&atilde;o, sofrer&aacute; preemp&ccedil;&atilde;o.</li>
 * 	<li>Tamanho do quantum interfere no desempenho.</li>
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
					if (executando == null ) {
						executando = prontos.remove(0);
						if (executando.tempos.resposta == -1) {
							executando.tempos.resposta = executando.tempos.pronto;
						}
						executando.setEstado(EstadoProcesso.EXECUTANDO);
					}
					if (executando.vaiFazerES()) {
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