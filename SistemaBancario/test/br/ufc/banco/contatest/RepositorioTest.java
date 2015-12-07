package br.ufc.banco.contatest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.dados.VectorContas;
import br.ufc.banco.dados.excecoes.CEException;

public class RepositorioTest {
	VectorContas contas;
	Conta conta;
	
	@Before
	public void setUp() throws Exception {
		contas = new VectorContas();
		conta = new Conta("123");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInserirVazio() throws CEException {
		this.contas.inserir(conta);
	}
	
	@Test
	public void testInserir() throws CEException {
		this.contas.inserir(conta);
		this.contas.inserir(new Conta("321"));
	}

}
