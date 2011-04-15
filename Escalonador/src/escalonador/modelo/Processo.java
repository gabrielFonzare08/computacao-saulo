package escalonador.modelo;

import escalonador.modelo.algoritmos.Preemptivo;

/**
 * Representa a entidade do processo no sistema de escalonamento. <br />
 * Cont&eacute;m atributos essenciais para as estat&iacute;sticas da escalona&ccedil;&atilde;o.
 * 
 * */
public class Processo {
	/**
	 * Gerador de sequ&ecirc;ncia para os {@link Processo#pid}.
	 * */
	private static int PID_ATUAL = 0;
	
	
	/**
	 * Id do processo
	 * */
	private int pid;
	
	/**
	 * Prioridade do processo.
	 * @see Preemptivo
	 * */
	private int prioridade;
	
	/**
	 * Indicado de estado do processo.
	 * */
	private EstadoProcesso estado;
	
	/**
	 * Tempo de execu&ccedil;&atilde;o da tarefa
	 * */
	private int tempoComputacao;
	
	/**
	 * Tempo que o processo demora para realiza ES.
	 * */
	private int tempoES;
	
	/**
	 * Fatia de tempo que o processo usa para ececutar na CPU.
	 * */
	private int quantum;
	
	/**
	 * Probabilidade de o processo executar ES
	 * */
	private float solicitacaoES;
	public Tempos tempos;

	public void decrementarTempoComputacao(){
		this.tempoComputacao--;
	}
		
	public Processo() {
		pid = ++PID_ATUAL;
		tempos = new Tempos();
	}
	
	public boolean isPronto() {
		return estado == EstadoProcesso.PRONTO;
	}
	
	public boolean isBloqueado() {
		return estado == EstadoProcesso.BLOQUEADO;
	}
	
	public boolean isExecutando() {
		return estado == EstadoProcesso.EXECUTANDO;
	}
	
	public boolean isTerminado() {
		return estado == EstadoProcesso.TERMINADO;
	}
	
	/**
	 * Indica se o processo vai ou n&atilde;o fazer opera&ccedil;&atilde;o de ES.
	 * @return boolean indicando se vai ou n&atilde;o realizar ES.
	 **/
	
	public boolean vaiFazerES() {
		return Math.random() <= solicitacaoES;
	}
	
	public int getQuantum() {
		return quantum;
	}
	
	public void setQuantum(int quantum) {
		this.quantum = quantum;
	}

	public int getPid() {
		return pid;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public EstadoProcesso getEstado() {
		return estado;
	}

	public void setEstado(EstadoProcesso estado) {
		this.estado = estado;
	}

	public int getTempoComputacao() {
		return tempoComputacao;
	}

	public void setTempoComputacao(int tempoComputacao) {
		this.tempoComputacao = tempoComputacao;
	}

	public float getSolicitacaoES() {
		return solicitacaoES;
	}

	public void setSolicitacaoES(float solicitacaoES) {
		this.solicitacaoES = solicitacaoES >= 1 ? 0.99f : solicitacaoES;
	}
	
	public int getTempoES() {
		return tempoES;
	}

	public void setTempoES(int tempoES) {
		this.tempoES = tempoES;
	}
		
	@Override
	public String toString() {
		return "Processo [pid=" + pid + ", prioridade=" + prioridade
				+ ", estado=" + estado + ", tempoComputacao=" + tempoComputacao
				+ ", quantum=" + quantum
				+ ", solicitacaoES=" + solicitacaoES + ", tempoES=" + tempoES
				+ ", tempos=" + tempos + "]";
	}
}
