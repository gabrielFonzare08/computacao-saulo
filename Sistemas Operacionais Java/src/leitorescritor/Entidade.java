package leitorescritor;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

abstract class Entidade implements Runnable {
	public static boolean janelaInvisivel = false;
	protected boolean estado;
	protected Lock lock;
	protected Condition condicao;
	protected static Random random = new Random(1000);
	
	protected void esperar(int tempo) {
		esperar(tempo, 0);
		
	}
	
	protected void esperar(int tempo, int min) {
		try {
			Thread.sleep(random.nextInt(tempo) + min);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void esperar() {
		esperar(1000);
	}

}
