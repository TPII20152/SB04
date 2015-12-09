package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.VNException;

@SuppressWarnings("serial")
public class ContaPoupanca extends Conta {

	public ContaPoupanca(String numero) {
		super(numero);
	}

	public void rendeJuros(double taxa) throws VNException {
		this.creditar(this.obterSaldo() * taxa);
	}
}
