package escalonador.modelo;

/**
 * Classe usada para fins estat&iacute;sticos do relat&oacute;rio
 * do escalonamento.
 * */
public class Tempos {

	public long bloqueado;
	public long executando;
	public long pronto;
	public long resposta = -1;
	public long timeoutBloqueado;
	public long tempoEspera;
	public long tempoRetorno;
	
	
	/**
	 * Atributos necess&aacute;rios para o algoritmo preemptivo baseado em 
	 * prioridades. 
	 */
	
	private int tempoEStemp;
	private int tempoComputacaotemp;
	
	/**
	 * M&eacute;todos necess&aacute;rios para o algoritmo preemptivo baseado em 
	 * prioridades.
	 */
	
		
	public void decrementarTempoComputacaotemp(){
		this.tempoComputacaotemp--;
	}
	
	public void incrementarTempoComputacaotemp(){
		this.tempoComputacaotemp++;
	}
		
	public int getTempoComputacaotemp() {
		return tempoComputacaotemp;
	}

	public void setTempoComputacaotemp(int tempoComputacaotemp) {
		this.tempoComputacaotemp = tempoComputacaotemp;
	}

	
	public int getTempoEStemp() {
		return tempoEStemp;
	}

	public void setTempoEStemp(int tempoEStemp) {
		this.tempoEStemp = tempoEStemp;
	}
	
	public void decrementaTempoEStemp(){
		this.tempoEStemp--;
	}
	
	
	@Override
	public String toString() {
		return "Tempos [bloqueado=" + bloqueado + ", executando=" + executando
				+ ", pronto=" + pronto + ", resposta= " +  resposta + "]";
	}	
}
