import javax.sound.sampled.Control;

import escalonador.controle.ControladorJanela;
import escalonador.controle.ControladorPainelCriacaoProcesso;
import escalonador.visao.Janela;


public class Aplicacao {
	
	public static void main(String[] args) {
		
		Janela janela = new Janela();
		
		ControladorJanela controladorJanela = new ControladorJanela(new Janela());
		controladorJanela.run();
		
		ControladorPainelCriacaoProcesso.getInstance(null);
		
	}

}
