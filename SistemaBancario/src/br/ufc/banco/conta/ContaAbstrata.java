package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.SIException;
import br.ufc.banco.conta.excecoes.VNException;


public abstract class ContaAbstrata {

	protected String numero;
	protected double saldo;

	public ContaAbstrata(String numero) {
		this.numero = numero;
		saldo = 0;
	}

	public void creditar(double valor) throws VNException {
		if (valor > 0){
			this.saldo = this.saldo + valor;
		}else{	
			throw new VNException(valor);
		}	
	}

	public abstract void debitar(double valor) throws SIException,VNException;

	public String obterNumero() {
		return numero;
	}

	public double obterSaldo() {
		return saldo;
	}
}
