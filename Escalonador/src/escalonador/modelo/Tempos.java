package escalonador.modelo;

public class Tempos {

	public long bloqueado;
	public long executando;
	public long pronto;
	public long resposta = -1;
	
	public long timeoutBloqueado;
	
	
	public long tempoEspera;
	public long tempoComputacao;
	
	@Override
	public String toString() {
		return "Tempos [bloqueado=" + bloqueado + ", executando=" + executando
				+ ", pronto=" + pronto + ", resposta= " +  resposta + "]";
	}	
}
