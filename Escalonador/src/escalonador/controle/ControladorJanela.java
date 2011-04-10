package escalonador.controle;

import escalonador.visao.Janela;

public class ControladorJanela implements Runnable {
	
	private Janela janela;
	
	public ControladorJanela(Janela janela) {
		this.janela = janela;
	}
	
	@Override
	public void run() {
		
	}
}