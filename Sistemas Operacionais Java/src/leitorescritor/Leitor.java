package leitorescritor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Leitor extends Entidade {
	static int id = 0;
	Condition podeLer;
	
	public Leitor(Lock lock, Condition podeLer) {
		this.lock = lock;
		this.estado = false;
		this.podeLer = podeLer;
	}
	
	public boolean isLendo() {
		return estado;
	}
	
	public void setLendo(boolean lendo) {
		estado = lendo;
	}
	
	
	public void lerBancoDados() {
		esperar(200);
	}

	@Override
	public void run() {
		
		while (!janelaInvisivel) {
			lock.lock();
				setLendo(true);
				lerBancoDados();
				setLendo(false);
				
				lock.unlock();
			
			try {
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			esperar();
		}		
	}
}