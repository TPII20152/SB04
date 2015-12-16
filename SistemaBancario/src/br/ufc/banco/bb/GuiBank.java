package br.ufc.banco.bb;

import java.awt.BorderLayout;
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

import com.sun.xml.internal.ws.api.server.Container;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.conta.excecoes.VNException;
import br.ufc.banco.dados.ArrayContas;
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
	
	public static void main(String[] args) throws InterruptedException {
		GuiBank gui = new GuiBank();
		
		gui.mostrarEntrada();
		gui.menuPrincipal();
	}
	
	public void mostrarEntrada() throws InterruptedException{
		
		JFrame entrada = new JFrame("BuggBank");
		JLabel jLImage = new JLabel();
		jLImage.setIcon(new ImageIcon("C:/Users/Talles/Documents/git/teste/SistemaBancario/persContas/GuiBank-1.png"));
		entrada.add(jLImage);
		entrada.setSize(500, 500);
		entrada.setLocationRelativeTo(null);
		entrada.setVisible(true);
		Thread.sleep(3000);
		entrada.dispose();
	}
	
	public void menuPrincipal(){
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
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta", "Adicionar Conta", JOptionPane.PLAIN_MESSAGE, null, tipos, "no");
					
				if(numConta.equals("Conta Normal")){
					conta = new Conta((String) JOptionPane.showInputDialog(null, "Insira o numero da conta"));
				}
				if(numConta.equals("Conta Imposto")){
					conta = new ContaImposto((String) JOptionPane.showInputDialog(null, "Insira o numero da conta"));
				}
				if(numConta.equals("Conta Especial")){
					conta = new ContaEspecial((String) JOptionPane.showInputDialog(null, "Insira o numero da conta"));
				}
				if(numConta.equals("Conta Poupanca")){
					conta = new ContaPoupanca((String) JOptionPane.showInputDialog(null, "Insira o numero da conta"));
				}
				System.out.println(numConta + " e " + conta.obterNumero());
				if (conta != null) {
					try {
						banco.cadastrar(conta);
						JOptionPane.showMessageDialog(null, "Operação realizada.");
					} catch (CEException cjee) {
						JOptionPane.showMessageDialog(null,"Erro: " + cjee.getMessage());
					}
				}
				
			}
			if(btn == btnDeposito){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				double valorDeposito = Double.parseDouble((String)JOptionPane.showInputDialog(null, "Insira o vlor a ser depositado:"));
				try {
					banco.creditar(numConta, valorDeposito);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (TNRException | VNException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
				}
			}
			if(btn == btnSaque){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				double valorSaque = Double.parseDouble((String)JOptionPane.showInputDialog(null, "Insira o vlor a ser depositado:"));
				try {
					banco.debitar(numConta, valorSaque);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (TNRException | VNException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
				}
			}
			if(btn == btnTransferencia){
				String numContaOrigem = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta(Origem)");
				String numContaDestino = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta(Destino)");
				double valorTransferencia = Double.parseDouble((String)JOptionPane.showInputDialog(null, "Insira o valor a transferir:"));
				try {
					banco.transferir(numContaOrigem, numContaDestino, valorTransferencia);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (TNRException | VNException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
				}
				
			}
			if(btn == btnExcluir){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				try {
					banco.remover(numConta);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (CIException cnee) {
					JOptionPane.showMessageDialog(null,"Erro: " + cnee.getMessage());
				}
			}
			if(btn == btnSaldo){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				try {
					JOptionPane.showMessageDialog(null, "Conta: " + numConta + "\nSaldo: " + banco.saldo(numConta));
				} catch (TNRException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());				}
			}
			if(btn == btnJuros){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				try {
					banco.renderJuros(numConta);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (TNRException | VNException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
				}
			}
			if(btn == btnBonus){
				String numConta = (String) JOptionPane.showInputDialog(null, "Insira o numero da conta");
				try {
					banco.renderBonus(numConta);
					JOptionPane.showMessageDialog(null, "Operação realizada.");
				} catch (TNRException | VNException tnre) {
					JOptionPane.showMessageDialog(null,"Erro: " + tnre.getMessage());
				}
			}
			if(btn == btnSair){
				menu.dispose();
			}
		}
	}
	
}
