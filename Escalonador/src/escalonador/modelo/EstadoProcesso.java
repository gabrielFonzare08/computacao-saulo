package escalonador.modelo;

public enum EstadoProcesso {
	/**
	 * Indica que o processo está no estado de pronto.
	 * Nesse estado ele pode passar para {@link EstadoProcesso#EXECUTANDO}
	 * */
	PRONTO,
	/**
	 * Estado atribu&iacute;do ao processo que se encontra
	 * na CPU.
	 * */
	EXECUTANDO,
	/**
	 * Indica que o processo se encontra no estado bloqueado.
	 * */
	BLOQUEADO,
	
	/**
	 * Estado que indica que o processo j&aacute; terminou. 
	 * */
	TERMINADO
}
