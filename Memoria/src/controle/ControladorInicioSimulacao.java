package controle;

import visao.ListaProcessos;
import visao.PainelInicioSimulacao;

import javax.swing.JOptionPane;

import modelo.Algoritmo;
import modelo.algoritmos.BestFit;
import modelo.algoritmos.FirstFit;
import modelo.algoritmos.NextFit;
import modelo.algoritmos.WorstFit;

public class ControladorInicioSimulacao extends Controlador {

	private static ControladorInicioSimulacao instance;
	private PainelInicioSimulacao painel;

	public ControladorInicioSimulacao(PainelInicioSimulacao painel) {
		this.painel = painel;
	}

	public static ControladorInicioSimulacao getInstance(
			PainelInicioSimulacao painel) {
		if (instance == null) {
			instance = new ControladorInicioSimulacao(painel);
		}
		return instance;
	}

	public void adicionarNovoProcesso() {
		// ListaProcessos processos = null;
		try {
			System.out
					.println(Integer.parseInt(painel.getQuantidadeProcessos()));
			int qtdeProcessos = Integer.parseInt(painel
					.getQuantidadeProcessos());
			int tipo = painel.getTamanhoProcessos();
			processos = new ListaProcessos(qtdeProcessos, tipo);
			atualizarLista(0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Valor inválido: " + e.getLocalizedMessage(),
					"Erro ao adicionar novo processo",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	private void atualizarLista(int indice) {
		painel.getListaProcessos().setListData(processos.toArray());
		painel.getListaProcessos().setSelectedIndex(indice);
	}

	public void removerProcessoSelecionado() {
		if (!processos.isEmpty()) {
			int indice = painel.getListaProcessos().getSelectedIndex();
			processos.remove(indice);
			indice = indice > 0 ? indice : 1;
			atualizarLista(indice - 1);
		}
	}

	public void notificarSimulacao() {

		switch (painel.getAlgoritmos()) {
		case Algoritmo.BEST_FIT:
			algoritmo = new BestFit();
			break;
			
		case Algoritmo.FIRST_FIT: algoritmo = new FirstFit(); break;
		case Algoritmo.NEXT_FIT: algoritmo = new NextFit(); break;
		
		default:
			algoritmo = new WorstFit();
			break;
		}

		ControladorRelatorioSimulacao relatorioSimulacao = ControladorRelatorioSimulacao
				.getInstance();

		synchronized (relatorioSimulacao) {
			relatorioSimulacao.notify();
		}
	}
}