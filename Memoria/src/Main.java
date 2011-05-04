import controle.ControladorRelatorioSimulacao;
import visao.Janela;


public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Janela janela = new Janela();
		
		ControladorRelatorioSimulacao relatorioSimulacao = ControladorRelatorioSimulacao.getInstance();
		
		relatorioSimulacao.setPainel(janela.getPainelRelatorioSimulacao());
		
		synchronized (relatorioSimulacao) {
			relatorioSimulacao.wait();
		}
		
		janela.getPainelRelatorioSimulacao().porEmFoco();
		
		synchronized (relatorioSimulacao) {
			relatorioSimulacao.wait(500);
		}
		
		relatorioSimulacao.simular();
	}

}
