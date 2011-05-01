package modelo;

public class Processo {
	
	private static int idGeral = 0;
	private int tamanho;
	private int id;
	private int tempoExecucao;
	
	public int getTempoExecucao() {
		return tempoExecucao;
	}

	public void setTempoExecucao(int tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

	public Processo() {
		id = ++idGeral;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	@Override
	public String toString() {
		return "Processo [tamanho=" + tamanho + ", id=" + id
				+ ", tempoExecucao=" + tempoExecucao + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Processo) {
			Processo outro = (Processo) obj;
			return this.id == outro.id;
		}
		
		return false;
	}
}