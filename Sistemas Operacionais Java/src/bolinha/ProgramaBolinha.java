package bolinha;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProgramaBolinha {
	public static void main(String[] args) throws InterruptedException {
		Tela tela = new Tela();
		tela.setVisible(true);
		
		synchronized (tela) {
			tela.wait();
		}
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(tela.bolinha);
	}
}
