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
				System.out.println("Lista de prontos vazia");
			} finally {
				try {
					if (executando == null) {
						executando = prontos.remove(0);
						if (executando.tempos.resposta == -1) {
							executando.tempos.resposta = executando.tempos.pronto;
						}

						executando.setEstado(EstadoProcesso.EXECUTANDO);
					}

					if (executando.getPrioridade() < atual.getPrioridade()) {
						prontos.add(executando);
						executando = atual;
						prontos.remove(atual);
					}

					if (executando.vaiFazerES()) {
						bloqueados.add(executando);
						System.out.println("Executando bloqueou");
						executando = null;
						continue;
					}

					System.out.println("processo " + executando.getPid()
							+ " executando");
					executando.decrementarTempoComputacao();
					System.out.println("processo possui"
							+ executando.getTempoComputacao()
							+ " ciclos restantes");

					if (executando.getTempoComputacao() <= 0) {
						System.out.println("processo " + executando.getPid()
								+ "terminou");
						prontos.remove(executando);
						// processos.remove(executando);
						terminados.add(executando);
						executando = null;
					}
				}

				catch (Exception e) {

					System.out
							.println("Nao existe processos na lista de prontos");
					incrementaTempoCpuOciosa();

				} finally {

					for (Processo p : prontos) {
						p.tempos.tempoEspera++;
						System.out.println("Processos: " + p.getPid()
								+ " Esperando");

					}

					for (int i = 0; i < bloqueados.size(); i++) {
						System.out.println("Processo: "
								+ bloqueados.get(i).getPid() + " Bloqueado");
						bloqueados.get(i).tempos.decrementaTempoEStemp();
						System.out.println(bloqueados.get(i).tempos
								.getTempoEStemp());
						if (bloqueados.get(i).tempos.getTempoEStemp() == 0) {
							prontos.add(bloqueados.get(i));
							System.out.println("Processo: "
									+ bloqueados.get(i).getPid()
									+ "ficou pronto");
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