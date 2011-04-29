
public class Segmento {
	
	private boolean ocupado;
	private int inicio;
	private int offset;
	private int idProcesso;
	
	public Segmento(int id) {
		idProcesso  = id;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getTamanho() {
		return offset - inicio;
	}
	
	public boolean isLivre() {
		return !ocupado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Segmento) {
			Segmento outro = (Segmento) obj;
			return this.idProcesso == outro.idProcesso;
		}
		return false;
	}
	
	

}
