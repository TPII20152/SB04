package br.ufc.banco.dados;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArrayContas implements IRepositorioContas {

	private ContaAbstrata[] contas = null;
	private int indice = 0;

	public ArrayContas() {
		this.contas = new ContaAbstrata[100];
	}

	public void apagar(String numero) throws CIException {
		if (this.procurar(numero) != null) {
			for (int i = 0; i < indice; i++) {
				if (contas[i] != null && contas[i].obterNumero().equals(numero)) {
					for (int j = i; j < indice - 1; j++) {
						contas[j] = contas[j + 1];
					}
					contas[indice++] = null;
				}
			}
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) != null) {
			this.contas[indice++] = conta;
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {
		ContaAbstrata[] lista = null;
		if (indice > 0) {
			lista = new ContaAbstrata[indice];
			for (int i = 0; i < indice; i++) {
				lista[i] = this.contas[i];
			}
		}
		return lista;
	}

	public int numeroContas() {
		return indice;
	}

	public ContaAbstrata procurar(String numero) {
		if (this.indice > 0) {
			for (int i = 0; i < indice; i++) {
				if (contas[i] != null && contas[i].obterNumero().equals(numero)) {
					return contas[i];
				}
			}
		}
		return null;
	}
}
