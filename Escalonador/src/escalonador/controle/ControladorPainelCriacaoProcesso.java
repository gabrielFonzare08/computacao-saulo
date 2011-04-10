package escalonador.controle;

import java.util.ArrayList;

import escalonador.modelo.EstadoProcesso;
import escalonador.modelo.Processo;
import escalonador.visao.paineis.PainelCriacaoProcesso;

public class ControladorPainelCriacaoProcesso {
	
	private static ControladorPainelCriacaoProcesso instance;
	private static int pid = 0;
	
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
		Processo processo = new Processo();
		
		try {		
			
			processo.setPid(++pid);
			processo.setEstado(EstadoProcesso.PRONTO);
			
			int prioridade = Integer.parseInt(painel.getPrioridadeProcesso());
			processo.setPrioridade(prioridade);
			
			int tempoComputacao = Integer.parseInt(painel.getTempoComputacaoProcesso());
			processo.setTempoComputacao(tempoComputacao);
			
			float taxaES = Float.parseFloat(painel.getTaxaESProcesso());
			processo.setSolicitacaoES(taxaES);
			
			int tempoES = Integer.parseInt(painel.getTempoESProcesso());
			processo.setTempoES(tempoES);
			
		} catch (Exception e) {
			return;
		}
		
		processos.add(processo);
		atualizarLista(processos.size() - 1);		
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
}