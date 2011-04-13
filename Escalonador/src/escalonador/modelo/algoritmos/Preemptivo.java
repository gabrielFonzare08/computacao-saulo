package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

public class Preemptivo extends Algoritmo {
	
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

		while (processos.size() != terminados.size()) {

			Collections.sort(prontos, comparator);
			try {
				atual = prontos.get(0);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Lista de prontos vazia");
			} finally {
				try {
					if (executando == null) {
						executando = prontos.remove(0);
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
					//	processos.remove(executando);
						terminados.add(executando);
						executando = null;
					}
				}

				catch (Exception e) {

					System.out
							.println("Nao existe processos na lista de prontos");
					
					
				} finally {
					tempoSimulacao++;
					// n precisa dessse for assim
					for (Processo p : prontos) {
						p.tempos.tempoEspera++;
						System.out.println("Processos: " + p.getPid()
								+ " Esperando");
					}

					for (int i = 0; i < bloqueados.size(); i++) {
						System.out.println("Processo: "
								+ bloqueados.get(i).getPid() + " Bloqueado");
						bloqueados.get(i).decrementaTempoEStemp();
						System.out.println(bloqueados.get(i).getTempoEStemp());
						if (bloqueados.get(i).getTempoEStemp() == 0) {
							prontos.add(bloqueados.get(i));
							System.out.println("Processo: "
									+ bloqueados.get(i).getPid()
									+ "ficou pronto");
							bloqueados.get(i).setTempoEStemp(
									bloqueados.get(i).getTempoES());
							bloqueados.remove(i);
						}
					}
				}
			}
		}
	}
}