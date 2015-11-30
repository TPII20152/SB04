package br.ufc.banco.dados.excecoes;

public class CEException extends Exception {

	private static final long serialVersionUID = 1L;

	private String numero;

	public CEException(String numero) {
		super("Conta Existente!");
		this.numero = numero;
	}

	public String numeroConta() {
		return numero;
	}

}
