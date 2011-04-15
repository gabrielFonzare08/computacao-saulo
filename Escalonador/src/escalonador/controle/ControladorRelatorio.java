package escalonador.controle;

import escalonador.visao.paineis.PainelRelatorio;

public class ControladorRelatorio extends Controlador {
	
	private static ControladorRelatorio instance;
	private PainelRelatorio painel;

	public ControladorRelatorio(PainelRelatorio painel) {
		this.painel = painel;
	}

	public static ControladorRelatorio getInstance(PainelRelatorio painel) {
		if (instance == null) {
			instance = new ControladorRelatorio(painel);
		}
		return instance;
	}

	public void setProcessos() {
		painel.setListaProcessos(processos.toArray());
	}

	public void mostrarProcesso(int i) {
		try {
			StringBuffer stringBuffer = new StringBuffer(processos.get(i)
					.toString());
			int indice = 0;

			while ((indice = stringBuffer.indexOf("[")) > 0) {
				stringBuffer.setCharAt(indice, '\n');
			}

			while ((indice = stringBuffer.indexOf("]")) > 0) {
				stringBuffer.setCharAt(indice, '\n');
			}

			while ((indice = stringBuffer.indexOf(",")) > 0) {
				stringBuffer.setCharAt(indice, '\n');
			}

			painel.setProcesso(stringBuffer.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}