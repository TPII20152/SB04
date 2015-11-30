package br.ufc.banco.bb.excecoes;

public class TNRException extends Exception {

	private static final long serialVersionUID = 1L;

	private Exception causa;

	public TNRException(Exception exception) {
		super("Transação não Realizada!");
		this.causa = exception;
	}

	public String getMessage() {
		return "Transação não Realizada! Causa: " + causa.getMessage();
	}

	public Exception getCausa() {
		return causa;
	}

}
