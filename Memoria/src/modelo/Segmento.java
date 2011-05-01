package modelo;


public class Segmento extends Processo {
	
	private boolean ocupado;
	private int tempoExecucaoS;
		
	public Segmento() {
	}
	
	/**
	 * Cria um novo segmento livre com o tamanho passado
	 * @param tamanho da memoria livre.
	 * 
	 * */
	public static Segmento vazio(int tamanho) {
		Segmento s = new Segmento();
		s.ocupado = false;
		s.setTamanho(tamanho);
		s.tempoExecucaoS = 0; 
		return s;
	}
	
	public Segmento(Processo processo) {
		setTamanho(processo.getTamanho());
		setId(processo.getId());
		setTempoExecucao(processo.getTempoExecucao());
		tempoExecucaoS = processo.getTempoExecucao();
	}
	
	public boolean isTerminado() {
		return tempoExecucaoS <= 0;
	}
	
	public void decrementarTempoExecucao() {
		tempoExecucaoS--;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}
	
	public boolean isLivre() {
		return !ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	@Override
	public String toString() {
		return "Segmento [ocupado=" + ocupado + ", tempoExecucao="
				+ tempoExecucaoS + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}
