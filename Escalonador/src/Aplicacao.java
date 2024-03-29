
import escalonador.controle.ControladorCriacaoProcesso;
import escalonador.controle.ControladorSimulacao;
import escalonador.visao.Janela;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		Janela janela = new Janela();
		janela.setVisible(true);
		
		ControladorCriacaoProcesso.getInstance(janela.getPainelCriacaoProcesso());
		ControladorSimulacao controladorSimulacao = ControladorSimulacao.getInstance(janela.getPainelSimulacao(), 0);
		
		synchronized (controladorSimulacao) {
			controladorSimulacao.wait();
		}
		
		controladorSimulacao.simular();
		
	}
}