package escalonador.controle;

import java.util.ArrayList;
import java.util.List;

import escalonador.modelo.Processo;


public abstract class Controlador {
	protected static List<Processo> processos = new ArrayList<Processo>();
}