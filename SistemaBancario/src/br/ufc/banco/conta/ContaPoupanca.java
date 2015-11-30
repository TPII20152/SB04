package br.ufc.banco.conta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String numero) {
		super(numero);
	}

	public void rendeJuros(double taxa) {
		this.creditar(this.obterSaldo() * taxa);
	}
}
