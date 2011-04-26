
public class Processo {

	private int tamanho;

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public void gerarProcessoPequeno(Processo p){
		p.setTamanho(2+(int)(Math.random()*2));
	}
	
	public void gerarProcessoMedio(Processo p){
		p.setTamanho(6+(int)(Math.random()*2));
	}
	
	public void gerarProcessoGrande(Processo p){
		p.setTamanho(12+(int)(Math.random()*10));
	}
	
	public static void main(String[] args) {
		Processo p = new Processo();
		p.gerarProcessoPequeno(p);
		System.out.println(p.getTamanho());
		
	}
}
