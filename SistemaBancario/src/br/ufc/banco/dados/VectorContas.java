package br.ufc.banco.dados;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class VectorContas implements IRepositorioContas{
	
	private Vector<ContaAbstrata> contas = null;
	XStream xstream = new XStream();
	
	public VectorContas() throws Exception {
		this.contas = new Vector<ContaAbstrata>();
		try {
			this.desserializar();
		} catch (Exception e) {
			this.serializar();
		}
	}

	public void apagar(String numero) throws CIException {
		ContaAbstrata conta = this.procurar(numero);
		if (conta != null) {
			this.contas.remove(conta);
			try {
				this.serializar();
			} catch (Exception e) {

			}
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) == null) { //Aqui tinha um erro. Se 
			this.contas.addElement(conta);
			try {
				this.serializar();
			} catch (Exception e) {
				
			}
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {

		ContaAbstrata[] lista = null;
		if (this.contas.size() > 0) {
			lista = new ContaAbstrata[this.contas.size()];
			for (int i = 0; i < this.contas.size(); i++) {
				lista[i] = (ContaAbstrata) this.contas.elementAt(i);
			}
		}
		return lista;
	}

	public int numeroContas() {
		return this.contas.size();
	}

	public ContaAbstrata procurar(String numero) {
		if (this.contas.size() > 0) {
			for (int i = 0; i < this.contas.size(); i++) {
				ContaAbstrata conta = (ContaAbstrata) this.contas.elementAt(i);
				if (conta.obterNumero().equals(numero)) {
					return conta;
				}
			}
		}
		return null;
	}

	@Override
	public void serializar() throws Exception {
		xstream.toXML(contas, new FileOutputStream("C:/Users/Talles/Documents/pers.txt"));
	}

	@Override
	public void desserializar() throws Exception {
		this.contas = (Vector) xstream.fromXML(new FileInputStream("C:/Users/Talles/Documents/pers.txt"));
	}

}
