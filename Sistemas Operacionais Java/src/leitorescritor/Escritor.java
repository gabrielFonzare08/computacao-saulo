package leitorescritor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Escritor extends Entidade {
	
	Condition podeEscrever;
	Condition podeLer;
	
	public Escritor(Lock lock, Condition podeEscrever, Condition podeLer) {
		this.lock = lock;
		this.estado = false;
		this.podeEscrever = podeEscrever;
		this.podeLer = podeLer;
	}
	
	public boolean isEscrevendo() {
		return estado;
	}
	
	public void setEscrevendo(boolean escrevendo) {
		estado = escrevendo;
	}
	
	
	public void escreverBancoDados() {
		esperar(3000);
	}

	@Override
	public void run() {
		
		while (!janelaInvisivel) {
			lock.lock();
			
			
			setEscrevendo(true);
			escreverBancoDados();			
			setEscrevendo(false);
			
			
			lock.unlock();	
			
			esperar();
		}		
	}
}