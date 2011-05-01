package modelo;


public class Segmento extends Processo {
	
	private boolean ocupado;
	
	
	public Segmento() {
		// TODO Auto-generated constructor stub
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
	public boolean equals(Object obj) {
		if(obj instanceof Segmento) {
			Segmento outro = (Segmento) obj;
			return this.getId() == outro.getId();
		}
		
		return false;
	}
	
}
