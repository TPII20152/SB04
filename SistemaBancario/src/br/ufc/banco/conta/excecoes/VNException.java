package br.ufc.banco.conta.excecoes;

public class VNException extends Exception {

	private static final long serialVersionUID = 1L;

	private double valorNegativo;

	public VNException(double valorNegativo) {
		super("Valor Negativo");
		this.valorNegativo = valorNegativo;	

	}

	public String getMessage() {
		return "-> " + valorNegativo + "Valor incorreto. Não e permitido mumero negativo";
	}

	public double getvalorNegativo() {
		return valorNegativo;
	}

}
