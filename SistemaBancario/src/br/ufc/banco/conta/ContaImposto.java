package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.SIException;

@SuppressWarnings("serial")
public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws SIException {
		if(valor > 0){
			double aDebitar = valor + (valor * 0.001);
			if(this.saldo >= aDebitar)
				this.saldo -= aDebitar;
			else
				throw new SIException(numero, aDebitar);
		}
		else
			throw new IllegalArgumentException();
	}
}
