import controle.ControladorInicioSimulacao;
import controle.ControladorRelatorioSimulacao;
import visao.Janela;


public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Janela janela = new Janela();
		
		ControladorInicioSimulacao simulacao = ControladorInicioSimulacao.getInstance(janela.getPainelInicioSimulacao());
		ControladorRelatorioSimulacao relatorioSimulacao = ControladorRelatorioSimulacao.getInstance();
		
		synchronized (relatorioSimulacao) {
			relatorioSimulacao.wait();
		}
		
		relatorioSimulacao.simular();
		
		janela.getPainelRelatorioSimulacao().porEmFoco();		
		relatorioSimulacao.gerarRelatorio();
	}

}
