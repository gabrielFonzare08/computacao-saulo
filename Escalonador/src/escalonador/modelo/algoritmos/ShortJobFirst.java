package escalonador.modelo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import escalonador.modelo.Algoritmo;
import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;

class ComparadorProcessoTempoComputacao implements Comparator<Processo> {
	@Override
	public int compare(Processo p1, Processo p2) {
		return p1.getTempoComputacao() - p2.getTempoComputacao();
	}
}

public class ShortJobFirst extends Algoritmo {

	private static final ComparadorProcessoTempoComputacao COMPARADOR = new ComparadorProcessoTempoComputacao();

	public ShortJobFirst(List<Processo> processos) {
		super(processos);
		for (Processo p : processos) {
			p.tempos.setTempoEStemp(p.getTempoES());
			p.tempos.setTempoComputacaotemp(p.getTempoComputacao());
		}
	}

	boolean bloqueado;

	@Override
	public void escalonar() {

		Collections.sort(prontos, COMPARADOR); // reorganiza-os

		while (terminados.size() < processos.size()) {
			esperar();

			if (executando == null) {
				executando = prontos.remove(0); // pega o proximo processo
				executando.setEstado(EstadoProcesso.EXECUTANDO);
				bloqueado = false;
				if (executando.tempos.resposta == -1) {
					executando.tempos.resposta = executando.tempos.pronto;
				}
				esperar();
			}

			if (executando.vaiFazerES() && bloqueado == false) {
				bloqueados.add(executando);
				bloqueado = true;
				System.out.println("Executando bloqueou");
				executando.setEstado(EstadoProcesso.BLOQUEADO);
			}

			if (!bloqueado) {
				System.out.println("processo " + executando.getPid()
						+ " executando");
				executando.tempos.executando++;
				executando.tempos.tempoRetorno++;
				executando.tempos.decrementarTempoComputacaotemp();
				System.out
						.println("processo possui"
								+ executando.getTempoComputacao()
								+ " ciclos restantes");
			}

			if (executando.tempos.getTempoComputacaotemp() <= 0) {
				System.out.println("processo " + executando.getPid()
						+ "terminou");
				executando.setEstado(EstadoProcesso.TERMINADO);
				terminados.add(executando);
				bloqueados.remove(executando);
				executando = null;
			}

			for (Processo p : prontos) {
				p.tempos.tempoEspera++;
				p.tempos.pronto++;
				p.tempos.tempoRetorno++;
				System.out.println("Processos: " + p.getPid() + " Esperando");
			}

			for (int i = 0; i < bloqueados.size(); i++) {
				System.out.println("Processo: " + bloqueados.get(i).getPid()
						+ " Bloqueado");
				bloqueados.get(i).tempos.tempoRetorno++;
				bloqueados.get(i).tempos.bloqueado++;
				bloqueados.get(i).tempos.decrementaTempoEStemp();
				incrementaTempoCpuOciosa();
				System.out.println(bloqueados.get(i).tempos.getTempoEStemp());
				if (bloqueados.get(i).tempos.getTempoEStemp() == 0) {
					bloqueado = false;
					System.out.println("Processo: "
							+ bloqueados.get(i).getPid() + "ficou pronto");
					bloqueados.get(i).tempos.setTempoEStemp(bloqueados.get(i)
							.getTempoES());
					bloqueados.remove(i);
				}
			}
		}
	}
}