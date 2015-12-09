package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.VNException;

@SuppressWarnings("serial")
public class ContaEspecial extends Conta {

	private double bonus;

	public ContaEspecial(String numero) {
		super(numero);
		bonus = 0;
	}

	public void rendeBonus() throws VNException {
		super.creditar(bonus);
		bonus = 0;
	}

	public double obterBonus() {
		return bonus;		
	}

	public void creditar(double valor) throws VNException {
		bonus = bonus + (valor * 0.01);
		super.creditar(valor);
	}

}
