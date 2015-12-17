package br.ufc.banco.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

@SuppressWarnings("serial")
public class VectorContas implements IRepositorioContas, Serializable{
	
	private Vector<ContaAbstrata> contas = null;

	public VectorContas() {
		this.contas = new Vector<ContaAbstrata>();
	}

	public void apagar(String numero) throws CIException {
		ContaAbstrata conta = this.procurar(numero);
		if (conta != null) {
			this.contas.remove(conta);
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) == null) { //Aqui tinha um erro. Se 
			this.contas.addElement(conta);
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
	
	public void persistir() throws IOException {
		FileOutputStream outFile = new FileOutputStream(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "VectorContas.tmp");
		ObjectOutputStream out = new ObjectOutputStream(outFile);
		out.writeObject(this);
		out.close();
	}

	@Override
	public void serializar() throws Exception {
		XStream xstream = new XStream();
		xstream.toXML(contas, new FileOutputStream("C:/Users/Talles/Documents/pers.txt"));
	}

	@Override
	public void desserializar() throws Exception {
		XStream xstream = new XStream();
		contas = (Vector) xstream.fromXML(new FileInputStream("C:/Users/Talles/Documents/pers.txt"));
	}

}
