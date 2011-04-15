package escalonador.controle;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;
import escalonador.visao.paineis.PainelCriacaoProcesso;
import escalonador.visao.paineis.PainelSimulacao;

public class ControladorPainelCriacaoProcesso extends Controlador {
	
	private static ControladorPainelCriacaoProcesso instance;
	
	private PainelCriacaoProcesso painel;
	private ArrayList<Processo> processos;
	
	public ControladorPainelCriacaoProcesso(PainelCriacaoProcesso painel) {
		this.painel = painel;
		this.processos = new ArrayList<Processo>();
	}
	
	public static ControladorPainelCriacaoProcesso getInstance(PainelCriacaoProcesso painel) {
		if(instance == null) {
			instance = new ControladorPainelCriacaoProcesso(painel);
		}
		return instance;
	}
	
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
	
	public List<Processo> getProcessos() {
		return processos;
	}
	
	public void simular() {
		
		PainelSimulacao painelSimulacao = painel.getJanela().getPainelSimulacao();
		painelSimulacao.porEmFoco();
		
		ControladorSimulacao simulacao = ControladorSimulacao.getInstance(painelSimulacao, painel.getAlgoritmoId());
		
		synchronized (simulacao) {
			simulacao.notify();
		}
				
	}
 }