package br.ufc.banco.bb;

import java.util.Scanner;

import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.VectorContas;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class TAABB24H {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		BancoBrasil banco = new BancoBrasil(new VectorContas());
		boolean loop = true;
		while (loop) {
			switch (menu()) {
			case 1:
				ContaAbstrata conta = null;
				switch (menuCadastroConta()) {
				case 1:
					System.out.println("Digite o número da conta comum: ");
					conta = new Conta(scanner.next());
					break;
				case 2:
					System.out.println("Digite o número da conta especial: ");
					conta = new ContaEspecial(scanner.next());
					break;
				case 3:
					System.out.println("Digite o número da conta poupança: ");
					conta = new ContaPoupanca(scanner.next());
					break;
				case 4:
					System.out.println("Digite o número da conta imposto: ");
					conta = new ContaPoupanca(scanner.next());
					break;

				default:
					System.out.println("Opçao Inválida!!!!");
					break;
				}

				if (conta != null) {
					try {
						banco.cadastrar(conta);
						System.out.println("Operação realizada com sucesso!");
					} catch (CEException cjee) {
						System.out.println("Erro: " + cjee.getMessage());
					}
				}

				break;
			case 2:
				System.out.println("Digite o número da conta: ");
				String numero = scanner.next();
				System.out.println("Digite o valor a ser creditado: ");
				double valor = scanner.nextDouble();
				try {
					banco.creditar(numero, valor);
					System.out.println("Operação realizada com sucesso!");
				} catch (TNRException tnre) {
					System.out.println("Erro: " + tnre.getMessage());
				}

				break;
			case 3:
				System.out.println("Digite o número da conta: ");
				numero = scanner.next();
				System.out.println("Digite o valor a ser debitado: ");
				valor = scanner.nextDouble();
				try {
					banco.debitar(numero, valor);
					System.out.println("Operação realizada com sucesso!");
				} catch (TNRException tnre) {
					System.out.println("Erro: " + tnre.getMessage());
				}

				break;
			case 4:
				System.out.println("Digite o número da conta de origem: ");
				String numeroOrigem = scanner.next();
				System.out.println("Digite o número da conta de destino: ");
				String numeroDestino = scanner.next();
				System.out.println("Digite o valor a ser debitado: ");
				valor = scanner.nextDouble();

				try {
					banco.transferir(numeroOrigem, numeroDestino, valor);
					System.out.println("Operação realizada com sucesso!");
				} catch (TNRException tnre) {
					System.out.println("Erro: " + tnre.getMessage());
				}

				break;
			case 5:
				System.out.println("Digite o número da conta: ");
				numero = scanner.next();
				try {
					System.out.println("Conta numero: " + numero);
					System.out.println("Saldo: " + banco.saldo(numero));
				} catch (TNRException tnre) {
					System.out.println();
					System.out.println("Erro: " + tnre.getMessage());
				}
				break;
			case 6:
				System.out.println("Digite o número da conta: ");
				numero = scanner.next();
				try {
					banco.remover(numero);
					System.out.println("Operação realizada com sucesso!");
				} catch (CIException cnee) {
					System.out.println("Erro: " + cnee.getMessage());
				}
				break;
			case 7:
				System.out.println("Digite o número da conta: ");
				numero = scanner.next();
				try {
					banco.renderJuros(numero);
					System.out.println("Operação realizada com sucesso!");
				} catch (TNRException tnre) {
					System.out.println("Erro: " + tnre.getMessage());
				}

				break;
			case 8:
				System.out.println("Digite o número da conta: ");
				numero = scanner.next();
				try {
					banco.renderBonus(numero);
					System.out.println("Operação realizada com sucesso!");
				} catch (TNRException tnre) {
					System.out.println("Erro: " + tnre.getMessage());
				}
				break;

			case 9:
				System.out.print("Tenha um bom dia!!!");
				loop = false;
				break;

			default:
				break;
			}
		}
	}

	private static int menu() {
		System.out.println("================================");
		System.out.println("Bem Vindo ao Banco do Brasil");
		System.out.println("Terminal de Auto-Atendimento");
		System.out.println("================================");
		System.out.println(" [1] Cadastrar Conta");
		System.out.println(" [2] Fazer Depósito");
		System.out.println(" [3] Realizar Saque");
		System.out.println(" [4] Transferência");
		System.out.println(" [5] Visualizar Saldo");
		System.out.println(" [6] Remover Conta");
		System.out.println(" [7] Render Juros");
		System.out.println(" [8] Render Bonus");
		System.out.println(" [9] Sair");
		System.out.println("================================");
		System.out.print("Digite a opção desejada: ");
		return scanner.nextInt();

	}

	private static int menuCadastroConta() {
		System.out.println("================================");
		System.out.println("Cadastrar Conta");
		System.out.println("================================");
		System.out.println(" [1] Comum");
		System.out.println(" [2] Especial");
		System.out.println(" [3] Poupança");
		System.out.println(" [4] Imposto");
		System.out.println("================================");
		System.out.print("Digite a opção desejada: ");
		return scanner.nextInt();
	}

}