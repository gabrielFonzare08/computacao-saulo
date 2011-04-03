package filosofos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JantarDosFilosofos {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Lock mutex = new ReentrantLock();
		
		Filosofo [] filosofos = {
				new Filosofo(mutex),
				new Filosofo(mutex),
				new Filosofo(mutex),
				new Filosofo(mutex),
				//new Filosofo(mutex),
				new Filosofo(mutex)
		};
		
		
		ExecutorService executor = Executors.newFixedThreadPool(filosofos.length * 2);
		
		for(int i = 0, n = filosofos.length; i < n; i++) {			
			filosofos[i].setEsquerda(filosofos[(i + n -1) % n]);
			filosofos[i].setDireita(filosofos[(i + 1) % n]);
		}
		
		Janela mesa = Janela.getInstance(args);
		mesa.setVisible(true);
		
		for(Filosofo filosofo : filosofos) {
			executor.execute(new Renderizador(filosofo));
			executor.execute(filosofo);
		}
		
		synchronized (args) {
			args.wait();
		}
		
		mesa.setVisible(false);
		mesa.dispose();
		executor.shutdown();
	}
};