package modelo;


public class Segmento extends Processo {
	
	private boolean ocupado;
	
	
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
		return s;
	}
	
	public Segmento(Processo processo) {
		setTamanho(processo.getTamanho());
		setId(processo.getId());
		setTempoExecucao(processo.getTempoExecucao());
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
	
	
	
}
