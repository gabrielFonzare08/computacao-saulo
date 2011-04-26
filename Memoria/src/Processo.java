
public class Processo {

	private int tamanho;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public void gerarProcessoPequeno(Processo p){
		p.setTamanho(2+(int)Math.round((Math.random()*2)));
	}
	
	public void gerarProcessoMedio(Processo p){
		p.setTamanho(6+(int)Math.round((Math.random()*2)));
	}
	
	public void gerarProcessoGrande(Processo p){
		p.setTamanho(12+(int)Math.round((Math.random()*8)));
	}
	
}
