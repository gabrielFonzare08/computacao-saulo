package leitorescritor;

import java.awt.Point;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BancoDados {
	
	public static void main(String[] args) throws Exception {
		
		Lock banco = new ReentrantLock();
		Condition podeLer = banco.newCondition();
		Condition podeEscrever = banco.newCondition();
		
		Entidade [] leitoresEscritores = {
			new Leitor(banco, podeLer),
			new Leitor(banco, podeLer),
			new Leitor(banco, podeLer),
			new Leitor(banco, podeLer),
			new Escritor(banco, podeEscrever, podeLer),
			new Escritor(banco, podeEscrever, podeLer),
			new Escritor(banco, podeEscrever, podeLer),
			new Escritor(banco, podeEscrever, podeLer)
				
		};
		
		Tela tela = new Tela();
		ExecutorService executor = Executors.newFixedThreadPool(leitoresEscritores.length * 2);
		
		synchronized (tela) {
			tela.wait();
		}
		
		int x = 200;
		int y = 100;
		for(Entidade leitorEscritor : leitoresEscritores) {
			if(x >= 400) {
				y = 150;
				x = 200;
			}
			executor.execute(leitorEscritor);
			executor.execute(new Renderizador(new Point(x += 50, y), leitorEscritor, tela));
		}
		
		executor.shutdown();
		
		
	}

}
