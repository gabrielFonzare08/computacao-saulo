package modelo;

public class Processo {
	
	private static int idGeral = 0;
	private int tamanho;
	private int id;
	
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
	
}
