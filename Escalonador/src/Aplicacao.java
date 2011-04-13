
import escalonador.controle.ControladorPainelCriacaoProcesso;
import escalonador.controle.ControladorSimulacao;
import escalonador.visao.Janela;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		Janela janela = new Janela();
		janela.setVisible(true);
		
		ControladorPainelCriacaoProcesso.getInstance(janela.getPainelCriacaoProcesso());
		ControladorSimulacao.getInstance(janela.getPainelSimulacao(), 0);
		
	}
}