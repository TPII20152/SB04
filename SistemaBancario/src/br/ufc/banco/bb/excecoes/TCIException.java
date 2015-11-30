package br.ufc.banco.bb.excecoes;

public class TCIException extends Exception {

	private static final long serialVersionUID = 1L;

	public TCIException(String numero) {
		super("Tipo de conta incompatível com a operação: " + numero);
	}
}
