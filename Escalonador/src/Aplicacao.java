
import javax.swing.UIManager;

import escalonador.controle.ControladorPainelCriacaoProcesso;
import escalonador.controle.ControladorSimulacao;
import escalonador.visao.Janela;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Janela janela = new Janela();
		janela.setVisible(true);
		
		ControladorPainelCriacaoProcesso.getInstance(janela.getPainelCriacaoProcesso());
		ControladorSimulacao.getInstance(janela.getPainelSimulacao(), 0);
		
	}
}