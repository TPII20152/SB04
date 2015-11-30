package br.ufc.banco.conta.excecoes;

public class SIException extends Exception {

	private static final long serialVersionUID = 1L;

	private String numero;

	private double saldo;

	public SIException(String numero, double saldo) {
		super("Saldo Insuficiente!");
		this.numero = numero;
		this.saldo = saldo;

	}

	public String getMessage() {
		return "Saldo Insuficiente: conta = " + numero + " saldo = " + saldo;
	}

	public String getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

}
