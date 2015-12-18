package br.ufc.banco.bb;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.conta.excecoes.VNException;
import br.ufc.banco.dados.VectorContas;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class GuiBank implements ActionListener{

	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JButton btnTransferencia;
	private JButton btnSaque;
	private JButton btnDeposito;
	private JButton btnJuros;
	private JButton btnBonus;
	BancoBrasil banco;
	private JButton btnSaldo;
	private JButton btnSair;
	private JFrame menu;
	
	public static void main(String[] args) throws Exception {
		GuiBank gui = new GuiBank();
		
		gui.mostrarEntrada();
		gui.menuPrincipal();
	}
	
	public void mostrarEntrada() throws InterruptedException{
		
		JFrame entrada = new JFrame("BuggBank");
		JLabel jLImage = new JLabel();
		jLImage.setIcon(new ImageIcon("C:/Users/Talles/Documents/GuiBank-1.png"));
		entrada.add(jLImage);
		entrada.setSize(500, 500);
		entrada.setLocationRelativeTo(null);
		entrada.setVisible(true);
		Thread.sleep(3000);
		entrada.dispose();
	}
	
	public void menuPrincipal() throws Exception{
		banco = new BancoBrasil(new VectorContas());
		menu = new JFrame();
		menu.setSize(200, 200);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		
		//Botoes
		btnAdicionar = new JButton("Adicionar Conta");
		btnAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdicionar.addActionListener(this);
		btnExcluir = new JButton("Excluir Conta");
		btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnExcluir.addActionListener(this);
		btnDeposito = new JButton("Fazer Depo'sito");
		btnDeposito.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDeposito.addActionListener(this);
		btnSaque = new JButton("Realizar Saque");
		btnSaque.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaque.addActionListener(this);
		btnTransferencia = new JButton("Transferencia");
		btnTransferencia.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaldo = new JButton("Ver Saldo");
		btnSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaldo.addActionListener(this);
		btnTransferencia.addActionListener(this);
		btnJuros = new JButton("Render Juros");
		btnJuros.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnJuros.addActionListener(this);
		btnBonus = new JButton("Render Bonus");
		btnBonus.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBonus.addActionListener(this);
		btnSair = new JButton("Sair");
		btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSair.addActionListener(this);
		
		//Labels
		JLabel title = new JLabel("BUGG BANK");
		title.setFont(new Font("serif", 20, 20));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel tracos = new JLabel("====================");
		tracos.setFont(new Font("serif", 20, 20));
		tracos.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel operacao = new JLabel("OPERACOES:");
		operacao.setFont(new Font("serif", 20, 20));
		operacao.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		menu.getContentPane().setLayout(new BoxLayout(menu.getContentPane(), BoxLayout.Y_AXIS));
		menu.getContentPane().add(title);
		menu.getContentPane().add(tracos);
		menu.getContentPane().add(operacao);
		menu.getContentPane().add(btnAdicionar);
		menu.getContentPane().add(btnDeposito);
		menu.getContentPane().add(btnSaque);
		menu.getContentPane().add(btnTransferencia);
		menu.getContentPane().add(btnSaldo);		
		menu.getContentPane().add(btnExcluir);
		menu.getContentPane().add(btnJuros);
		menu.getContentPane().add(btnBonus);
		menu.getContentPane().add(btnSair);
		
		menu.setSize(500, 500);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton){
			JButton btn = (JButton) source;
			if(btn == btnAdicionar){
				ContaAbstrata conta = null;
				String[] tipos = {"Conta Normal", "Conta Poupanca", "Conta Especial", "Conta Imposto"};
				String tipoConta = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de conta:", "Adicionar Conta", JOptionPane.PLAIN_MESSAGE, null, tipos, "no");
				if(tipoConta != null){	
					String numConta = null;
					if(tipoConta.equals("Conta Normal")){
						numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
						conta = new Conta(numConta);
					}
					if(tipoConta.equals("Conta Imposto")){
						numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
						conta = new ContaImposto(numConta);
					}
					if(tipoConta.equals("Conta Especial")){
						numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
						conta = new ContaEspecial(numConta);
					}
					if(tipoConta.equals("Conta Poupanca")){
						numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
						conta = new ContaPoupanca(numConta);
					}
					if (numConta != null) {
						try {
							banco.cadastrar(conta);
							JOptionPane.showMessageDialog(null, "Operação realizada.");
						} catch (CEException cjee) {
							JOptionPane.showMessageDialog(null,"Erro: " + cjee.getMessage());
						}
					}
				}
			}
			if(btn == btnDeposito){
				String numConta = null;
				numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					String valorDepositoStr = null;
					valorDepositoStr = (String)JOptionPane.showInputDialog(null, "Insira o vlor a ser depositado:");
					if(valorDepositoStr != null || "".equals(valorDepositoStr)){
						Double valorDeposito = Double.parseDouble(valorDepositoStr);
						try {
							banco.creditar(numConta, valorDeposito);
							JOptionPane.showMessageDialog(null, "Operação realizada.");
						} catch (TNRException | VNException tnre) {
							JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
						} catch (Exception e1) {
							
						}
					}
				}
			}
			if(btn == btnSaque){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					String valorSaqueStr = (String)JOptionPane.showInputDialog(null, "Insira o vlor a ser depositado:");
					if(valorSaqueStr != null){
						Double valorSaque = Double.parseDouble(valorSaqueStr);
						try {
							banco.debitar(numConta, valorSaque);
							JOptionPane.showMessageDialog(null, "Operação realizada.");
						} catch (TNRException | VNException tnre) {
							JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
						}
					}
				}
			}
			if(btn == btnTransferencia){
				String numContaOrigem = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta(Origem)");
				if(numContaOrigem != null){
					String numContaDestino = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta(Destino)");
					if(numContaDestino != null){
						String valorTransferenciaStr = (String)JOptionPane.showInputDialog(null, "Insira o valor a transferir:");
						if(valorTransferenciaStr != null){
							Double valorTransferencia = Double.parseDouble(valorTransferenciaStr);
							try {
								banco.transferir(numContaOrigem, numContaDestino, valorTransferencia);
								JOptionPane.showMessageDialog(null, "Operação realizada.");
							} catch (TNRException | VNException tnre) {
								JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
							}
						}
					}
				}
			}
			if(btn == btnExcluir){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					try {
						banco.remover(numConta);
						JOptionPane.showMessageDialog(null, "Operação realizada.");
					} catch (CIException cnee) {
						JOptionPane.showMessageDialog(null,"Erro: " + cnee.getMessage());
					}
				}
			}
			if(btn == btnSaldo){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					try {
						JOptionPane.showMessageDialog(null, "Conta: " + numConta + "\nSaldo: " + banco.saldo(numConta));
					} catch (TNRException tnre) {
						JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
					}
				}
			}
			if(btn == btnJuros){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					try {
						banco.renderJuros(numConta);
						JOptionPane.showMessageDialog(null, "Operação realizada.");
					} catch (TNRException | VNException tnre) {
						JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
					} catch (Exception e1) {
						
					}
				}
			}
			if(btn == btnBonus){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				if(numConta != null){
					try {
						banco.renderBonus(numConta);
						JOptionPane.showMessageDialog(null, "Operação realizada.");
					} catch (Exception tnre) {
						JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
					}
				}
			}
			if(btn == btnSair){
				menu.dispose();
			}
		}
	}
	
}
