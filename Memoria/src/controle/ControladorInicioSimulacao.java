package controle;


import java.util.ArrayList;

import visao.PainelInicioSimulacao;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class ControladorInicioSimulacao extends Controlador{
	
	private static ControladorInicioSimulacao instance;	
	private PainelInicioSimulacao painel;
	
	public ControladorInicioSimulacao(PainelInicioSimulacao painel) {
		this.painel = painel;
	}
	
	public static ControladorInicioSimulacao getInstance(PainelInicioSimulacao painel) {
		if(instance == null) {
			instance = new ControladorInicioSimulacao(painel);
		}
		return instance;
	}
	
	public void adicionarNovoProcesso() {
		//ListaProcessos processos = null;
		try {		
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor inválido: " + e.getLocalizedMessage(), "Erro ao adicionar novo processo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}
	
	private void atualizarLista(int indice) {
		painel.getListaProcessos().setListData(processos.toArray());
		painel.getListaProcessos().setSelectedIndex(indice);
	}
	
	public void removerProcessoSelecionado() {
		if(!processos.isEmpty()) {
			int indice = painel.getListaProcessos().getSelectedIndex();
			processos.remove(indice);
			indice = indice > 0 ? indice : 1;
			atualizarLista(indice -1);
		}
	}
	
	public void notificarSimulacao() {
		
		PainelInicioSimulacao painelInicioSimulacao = painel.getJanela().getPainelInicioSimulacao();
		painelInicioSimulacao.porEmFoco();
		
		ControladorInicioSimulacao simulacao = ControladorInicioSimulacao.getInstance(painelInicioSimulacao);
		
		synchronized (simulacao) {
			simulacao.notify();
		}
	}
 }