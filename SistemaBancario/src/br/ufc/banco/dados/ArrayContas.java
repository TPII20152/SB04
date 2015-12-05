package br.ufc.banco.dados;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArrayContas implements IRepositorioContas {

	private ArrayList<ContaAbstrata> contas;

	public ArrayContas() {
		this.contas = new ArrayList<ContaAbstrata>();
	}

	public void apagar(String numero) throws CIException {
		if (this.procurar(numero) != null) {
			for (ContaAbstrata c: contas){
				if(c.obterNumero()==numero){
					contas.remove(c);
				}
			}
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) != null) {
			this.contas.add(conta);
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {
		ContaAbstrata[] lista = new ContaAbstrata[contas.size()];
		lista = contas.toArray(new ContaAbstrata[contas.size()]);
		return lista;
	}

	public int numeroContas() {
		return contas.size();
	}

	public ContaAbstrata procurar(String numero) {
		if (this.contas.size() > 0) {
			for (ContaAbstrata c: contas) {
				if (c!= null && c.obterNumero().equals(numero)) {
					return c;
				}
			}
		}
		return null;
	}
	
	public void persistir() throws IOException {
		FileOutputStream outFile = new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "contas.tmp");
		ObjectOutputStream out = new ObjectOutputStream(outFile);
		out.writeObject(this.contas);
		out.close();
	}
}
