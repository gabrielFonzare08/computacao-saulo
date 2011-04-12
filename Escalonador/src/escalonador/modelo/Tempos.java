package escalonador.modelo;

public class Tempos {

	public long bloqueado;
	public long executando;
	public long tempoEspera;
	public long tempoComputacao;
	
	@Override
	public String toString() {
		return "Tempos [bloqueado=" + bloqueado + ", executando=" + executando
				+ ", tempo de espera=" + tempoEspera + "]";
	}	
}
