package br.ufc.banco.dados;

import java.io.Serializable;
import java.util.ArrayList;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.persistencia.Deserializador;
import br.ufc.banco.conta.persistencia.Serializador;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

@SuppressWarnings("serial")
public class ArrayContas implements IRepositorioContas, Serializable {

private ArrayList<ContaAbstrata> contas;
	
	Serializador s = new Serializador();
	Deserializador d = new Deserializador();

	public ArrayContas() {
		this.contas = new ArrayList<ContaAbstrata>();
		try {
			this.desserializar();
        } catch (Exception ex) {
            System.err.println("Falha ao deserializar! - " + ex.toString());
	        try {
	        	this.serializar();
	        } catch (Exception ex1) {
				 System.err.println("Falha ao serializar! - " + ex1.toString());
			 }
        }
	}

	public void apagar(String numero) throws CIException {
		if (this.procurar(numero) != null) {
				contas.remove(this.procurar(numero));
				try {
					this.serializar();
				} catch (Exception ex) {
					System.err.println("Falha ao serializar! - " + ex.toString());
				}	
		} else 
			throw new CIException(numero);
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) == null) {
			this.contas.add(conta);
			
			try {
				this.serializar();
			} catch (Exception ex) {
				System.err.println("Falha ao serializar! - " + ex.toString());
			}
			 
		} else 
			throw new CEException(conta.obterNumero());
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
				if (c!= null && c.obterNumero().equals(numero)) 
					return c;
			}
		}
		return null;
	}
	
	public void serializar() throws Exception{
    	//s.serializar("/home/362974/rep/SistemaBancario/persContas/pers.txt", contas);
		s.serializar("C:/Users/Talles/Documents/git/BugConta/SistemaBancario/persContas/pers.txt", contas);
	}
	@SuppressWarnings("unchecked")
	public void desserializar() throws Exception{
        contas = null;
        //contas = (ArrayList<ContaAbstrata>) d.deserializar("/home/362974/rep/SistemaBancario/persContas/pers.txt");
        contas = (ArrayList<ContaAbstrata>) d.deserializar("C:/Users/Talles/Documents/git/BugConta/SistemaBancario/persContas/pers.txt");
	}
}

