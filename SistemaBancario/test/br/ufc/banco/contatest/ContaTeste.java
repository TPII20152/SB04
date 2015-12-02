package br.ufc.banco.contatest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.excecoes.SIException;
import br.ufc.banco.conta.excecoes.VNException;

public class ContaTeste {
	Conta conta;

	@Before
	public void setUp() throws Exception {
		conta = new Conta("1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	//passou
	@Test
	public void testDebitarNormal() throws SIException, VNException{
		conta.creditar(20);
		conta.debitar(10);
		assertEquals(10, conta.obterSaldo(), 0);
	}
	
	//passou
	@Test (expected = SIException.class )
	public void testDebitarComSaldoInsufuciente() throws SIException, VNException{
		conta.creditar(10);
		conta.debitar(20);
		assertEquals(0, conta.obterSaldo(), 0);
	}
	
	//passou
	@Test (expected = VNException.class )
	public void testDebitarNegativo() throws SIException, VNException{
		conta.debitar(-10);
		assertEquals(0, conta.obterSaldo(), 0);
		
	}

	//passou
	@Test
	public void testCreditarNormal() throws SIException, VNException {
		conta.creditar(100);
		assertEquals(100, conta.obterSaldo(), 0);
		
	}
	
	//passou
	@Test (expected = VNException.class )
	public void testeCreditarNegativo() throws SIException, VNException{
		conta.creditar(-100);
		assertEquals(0, conta.obterSaldo(), 0);
		
		
	}
	
	
	
	

}
