package filosofos;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class Filosofo implements Runnable {

	enum EstadoFiloso {
		PENSANDO, COM_FOME, COMENDO		
	}
	
	private EstadoFiloso estado;
	private Condition podeComer;
	private Filosofo filosofoDaEsquerda;
	private Filosofo filosofoDaDireita;

	private final Lock mutex;
	private static Random random = new Random(1000); 

	public Filosofo(Lock mutex) {
		this.mutex = mutex;
		this.estado = EstadoFiloso.PENSANDO;
		this.podeComer = mutex.newCondition();
	}

	public boolean isPensando() {
		return estado == EstadoFiloso.PENSANDO;
	}

	public boolean isComFome() {
		return estado == EstadoFiloso.COM_FOME;
	}

	public boolean isComendo() {
		return estado == EstadoFiloso.COMENDO;
	}

	public void setEsquerda(Filosofo esquerda) {
		this.filosofoDaEsquerda = esquerda;
	}

	public void setDireita(Filosofo direita) {
		this.filosofoDaDireita = direita;
	}

	public boolean isNinguemProximoComendo() {
		return !filosofoDaEsquerda.isComendo()
				&& !filosofoDaDireita.isComendo();
	}

	public void pensar() {
		comer();
	}
	
	public void comer() {
		try {
			Thread.sleep(random.nextInt(1000));
		} catch (Exception e) {
		}
	}

	public void pegarGarfos() throws InterruptedException {
		mutex.lock();

		this.estado = EstadoFiloso.COM_FOME;
		this.testar();

		mutex.unlock();
	}

	public void soltarGarfos() throws InterruptedException {
		try {
			mutex.lock();
			
			estado = EstadoFiloso.PENSANDO;
			
			
			if (filosofoDaEsquerda.isComFome()) {
				filosofoDaEsquerda.podeComer.signal();
			}
			
			if (filosofoDaDireita.isComFome()) {
				filosofoDaDireita.podeComer.signal();
			}
			
		} finally {
			mutex.unlock();			
		}

		

	}

	public void testar() throws InterruptedException {
		if (isComFome() && isNinguemProximoComendo()) {
			estado = EstadoFiloso.COMENDO;
			return;
		}
		
		this.podeComer.await();
	}

	@Override
	public void run() {
		while (Janela.getInstance(null).isVisible()) {
			try {
				pensar();
				pegarGarfos();
				comer();
				soltarGarfos();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		float f = 0.1f;
		float h = 0.0f;
		
		for(int i = 0; i < 10; i++) {
			h += f;
		}
		
		System.out.println(h);
	}
}