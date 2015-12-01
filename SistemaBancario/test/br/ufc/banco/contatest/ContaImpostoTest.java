package br.ufc.banco.contatest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.ContaImposto;

public class ContaImpostoTest {

	ContaImposto contaImposto;
	@Before
	public void setUp() throws Exception {
		contaImposto = new ContaImposto("1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDebitarNegativo() {
		this.contaImposto.creditar(1000);
		this.contaImposto.debitar(-100);
	}
	
	@Test
	public void testDebitarNormal() {
		this.contaImposto.creditar(1000);
		this.contaImposto.debitar(100);
		assertEquals(899.9, this.contaImposto.obterSaldo(), 0);
	}

}
