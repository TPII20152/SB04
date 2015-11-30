package br.ufc.banco.dados.excecoes;

public class CIException extends Exception {

	private static final long serialVersionUID = 1L;

	private String numero;

	public CIException(String numero) {
		super("Conta Inexistente!");
		this.numero = numero;
	}

	public String getMessage() {
		return "Conta Inexistente: conta = " + numero;
	}

	public String numeroConta() {
		return numero;
	}

}
