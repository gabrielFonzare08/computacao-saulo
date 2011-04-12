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

		while (!processos.isEmpty()) {
			Collections.sort(prontos, comparator);

			try {
				executando = prontos.remove(0); // pode n ter elementos na lista #1

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Nao existe processos na lista de prontos");
			} finally {

				tempoSimulacao++;

				if (executando.vaiFazerES()) { // se n tiver provavelmente n vai funcionar #1
					executando.setEstado(EstadoProcesso.BLOQUEADO);
				} else {
					executando.setEstado(EstadoProcesso.EXECUTANDO);
				}

				// n precisa dessse for assim
				for (Processo p : processos) {
					switch (p.getEstado()) {
					case PRONTO: /// aqui pode ser o for na lista de prontos #2
						p.tempos.tempoEspera++;
						System.out.println("Processos: " + p.getPid()
								+ " Esperando");
						break;

					case BLOQUEADO: // aqui o for na lista de block #3
						p.decrementaTempoEStemp();
						System.out.println("Processo: " + p.getPid()
								+ " Bloqueado");
						if (p.getTempoEStemp() == 0) {
							p.setEstado(EstadoProcesso.PRONTO);
							System.out.println("Processo: " + p.getPid()
									+ "ficou pronto");
							prontos.add(executando);
						}
						break;

					case EXECUTANDO:
						p.decrementarTempoComputacao();
						System.out.println("Processo " + p.getPid()
								+ " executando");
						if (p.tempos.tempoComputacao == 0) {
							System.out.println("tempo de computação no fim");
							System.out.println("Processo " + p.getPid() + "terminou");
							terminados.add(p);
						}
						break;
					}
				}

			}
		}
	}
}