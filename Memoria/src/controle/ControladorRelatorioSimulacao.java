package controle;

public class ControladorRelatorioSimulacao {
	
	private static ControladorRelatorioSimulacao instance;
	
	public static ControladorRelatorioSimulacao getInstance() {
		if(instance == null) {
			instance = new ControladorRelatorioSimulacao();
		}
		return instance;
	}
	
	public void simular() {
		System.out.println("simuladno");
	}
	
	public void gerarRelatorio() {
		System.out.println("relatorio");
	}

}
