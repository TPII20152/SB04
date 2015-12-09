package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.SIException;
import br.ufc.banco.conta.excecoes.VNException;


@SuppressWarnings("serial")
public class Conta extends ContaAbstrata {

	public Conta(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws SIException , VNException{
		if (valor > 0){
			if (this.saldo >= valor ) {
				this.saldo = this.saldo - valor;
			} else {
				throw new SIException(numero, valor);
			}
		}else{	
			throw new VNException(valor);
		}
	}
}