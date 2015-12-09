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

	@SuppressWarnings("unchecked")
	public ArrayContas() {
		this.contas = new ArrayList<ContaAbstrata>();
		
		try {
            contas = null;
            contas = (ArrayList<ContaAbstrata>) d.deserializar("/home/362974/rep/SistemaBancario/persContas/pers.txt");
        } catch (Exception ex) {
            System.err.println("Falha ao deserializar! - " + ex.toString());
       
	        try {
				 s.serializar("/home/362974/rep/SistemaBancario/persContas/pers.txt", contas);
			 } catch (Exception ex1) {
				 System.err.println("Falha ao serializar! - " + ex1.toString());
			 }
        }
		
		 
	}

	
	@SuppressWarnings("unchecked")
	public void apagar(String numero) throws CIException {
		
        try {
            contas = null;
            contas = (ArrayList<ContaAbstrata>) d.deserializar("/home/362974/rep/SistemaBancario/persContas/pers.txt");
        } catch (Exception ex) {
            System.err.println("Falha ao deserializar! - " + ex.toString());
        }

		if (this.procurar(numero) != null) {
			for (ContaAbstrata c: contas){
				if(c.obterNumero()==numero){
					contas.remove(c);
				}
			}
			 try {
				 s.serializar("/home/362974/rep/SistemaBancario/persContas/pers.txt", contas);
			 } catch (Exception ex) {
				 System.err.println("Falha ao serializar! - " + ex.toString());
			 }	
			
			
		} else {
			throw new CIException(numero);
		}	
	}

	@SuppressWarnings("unchecked")
	public void inserir(ContaAbstrata conta) throws CEException {
		 try {
	            contas = null;
	            contas = (ArrayList<ContaAbstrata>) d.deserializar("/home/362974/rep/SistemaBancario/persContas/pers.txt");
	        } catch (Exception ex) {
	            System.err.println("Falha ao deserializar! - " + ex.toString());
	        }
		
		if (this.procurar(conta.obterNumero()) == null) {
			this.contas.add(conta);
			
			 try {
				 s.serializar("/home/362974/rep/SistemaBancario/persContas/pers.txt", contas);
			 } catch (Exception ex) {
				 System.err.println("Falha ao serializar! - " + ex.toString());
			 }
			 
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
}

