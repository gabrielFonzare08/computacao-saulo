package escalonador.modelo;

public class Tempos {

	public long bloqueado;
	public long executando;
	public long tempoEspera;
	
	
	@Override
	public String toString() {
		return "Tempos [bloqueado=" + bloqueado + ", executando=" + executando
				+ ", pronto=" + pronto + "]";
	}	
}
