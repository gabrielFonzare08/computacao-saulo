package escalonador.controle;

import javax.swing.JList;
import javax.swing.JOptionPane;

import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;
import escalonador.visao.paineis.PainelCriacaoProcesso;
import escalonador.visao.paineis.PainelSimulacao;

public class ControladorCriacaoProcesso extends Controlador {
	
	private static ControladorCriacaoProcesso instance;
	
	private PainelCriacaoProcesso painel;
	
	public ControladorCriacaoProcesso(PainelCriacaoProcesso painel) {
		this.painel = painel;
	}
	
	public static ControladorCriacaoProcesso getInstance(PainelCriacaoProcesso painel) {
		if(instance == null) {
			instance = new ControladorCriacaoProcesso(painel);
		}
		return instance;
	}
	
	/**
	 * Captura os dados da GUI e os converte em 
	 * objetos do tipo {@link Processo}. 
	 * */
	public void adicionarNovoProcesso() {
		Processo processo = null;
		
		try {		
			
			int prioridade = Integer.parseInt(painel.getPrioridadeProcesso());
			int tempoComputacao = Integer.parseInt(painel.getTempoComputacaoProcesso());
			int quantum = Integer.parseInt(painel.getQuantumProcesso());
			float taxaES = Float.parseFloat(painel.getTaxaESProcesso());
			int tempoES = Integer.parseInt(painel.getTempoESProcesso());
			
			
			processo = new Processo();
			processo.setEstado(EstadoProcesso.PRONTO);			
			processo.setPrioridade(prioridade);
			processo.setTempoComputacao(tempoComputacao);			
			processo.setQuantum(quantum);			
			processo.setSolicitacaoES(taxaES);			
			processo.setTempoES(tempoES);
			processos.add(processo);
			atualizarLista(processos.size() - 1);		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Valor inválido: " + e.getLocalizedMessage(), "Erro ao adicionar novo processo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}
	
	/**
	 * Atualiza o {@link JList} que contem os processos.
	 * @param indice deixa o &uacute;ltimo processo selecionado.
	 * */
	private void atualizarLista(int indice) {
		painel.getListaProcessos().setListData(processos.toArray());
		painel.getListaProcessos().setSelectedIndex(indice);
	}
	
	/**
	 * Remove o processo em foco da lista de processos. 
	 * */
	
	public void removerProcessoSelecionado() {
		if(!processos.isEmpty()) {
			int indice = painel.getListaProcessos().getSelectedIndex();
			processos.remove(indice);
			indice = indice > 0 ? indice : 1;
			atualizarLista(indice -1);
		}
	}
	
	/**
	 * Passa o trabalho para o {@link ControladorSimulacao}
	 * e p&otilde;e o painel de simulação para frente da GUI.
	 * */
	public void notificarSimulacao() {
		
		PainelSimulacao painelSimulacao = painel.getJanela().getPainelSimulacao();
		painelSimulacao.porEmFoco();
		
		ControladorSimulacao simulacao = ControladorSimulacao.getInstance(painelSimulacao, painel.getAlgoritmoId());
		
		synchronized (simulacao) {
			simulacao.notify();
		}
				
	}
 }