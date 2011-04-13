package escalonador.modelo;

public class Tempos {

	public long bloqueado;
	public long executando;
	public long pronto;
	public long resposta = -1;
	public long timeoutBloqueado;
	public long tempoEspera;
		
	/**
	 * Atributos necessários para o algoritmo preemptivo baseado em 
	 * prioridades. 
	 */
	
	private int tempoES;
	private int tempoEStemp;
	
	/**
	 * Métodos necessários para o algoritmo preemptivo baseado em 
	 * prioridades.
	 */
	
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
