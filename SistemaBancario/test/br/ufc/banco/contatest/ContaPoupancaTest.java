package br.ufc.banco.contatest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.conta.excecoes.VNException;

public class ContaPoupancaTest {

	ContaPoupanca poupanca;
	@Before
	public void setUp() throws Exception {
		this.poupanca = new ContaPoupanca("1234");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRendeJuros() throws VNException {
		this.poupanca.creditar(100);
		this.poupanca.rendeJuros(0.01);
		assertEquals(101, this.poupanca.obterSaldo(), 0);
	}

}
