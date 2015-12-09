package br.ufc.banco.contatest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.excecoes.VNException;

public class ContaEspecialTest {

	ContaEspecial conta;
	
	@Before
	public void setUp() throws Exception {
		conta = new ContaEspecial("1903");
	}

	@After
	public void tearDown() throws Exception {
	}

	//Credita normalmente para valores positivos
	@Test
	public void testCretitarNormal() throws VNException {
		conta.creditar(100);
		assertEquals(100, conta.obterSaldo(),0);
	}
	
	// Retorna VNException - valor negativo inserido
	@Test (expected = VNException.class)
	public void testCreditrarNegativo() throws VNException{
		conta.creditar(-10);
		assertEquals(0, conta.obterSaldo(), 0);
	}
	
	//Gera Bonus normalmente quando creditado valor negativo
	@Test 
	public void testeBonusNormal() throws VNException{
		conta.creditar(100);
		assertEquals(100*0.01, conta.obterBonus(), 0);
	}

	// Retorna VNException - tentou creditar valor negativo
	@Test (expected = VNException.class)
	public void testBonusNegativo() throws VNException{
		conta.creditar(-100);
		assertEquals(0, conta.obterBonus(), 0);
	}
	
	// Rende bônus normalmente
	@Test
	public void testRendeBonus() throws VNException{
		conta.creditar(100);
		conta.creditar(100);
		conta.creditar(100);
		conta.rendeBonus();
		
		assertEquals(303, conta.obterSaldo(),0);
		
	}
}