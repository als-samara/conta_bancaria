package conta_bancaria;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {
	static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		ContaController contas = new ContaController();
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		System.out.println("\nCriar Contas\n");

		// Contas para aparecer no método listarTodas();
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Raos", 4000f, 12);
		contas.cadastrar(cp2);

		contas.listarTodas();

		// Mostrar Menu
		while (true) {
			System.out.println(Cores.TEXT_GREEN_BOLD + Cores.ANSI_BLACK_BACKGROUND);
			System.out.println(" ******************************************** ");
			System.out.println("                                              ");
			System.out.println("                  RATANABÁ BANK               ");
			System.out.println("                                              ");
			System.out.println(" ******************************************** ");
			System.out.println("                                              ");
			System.out.println("        1 - Criar Conta                       ");
			System.out.println("        2 - Listar todas as Contas            ");
			System.out.println("        3 - Buscar Conta por Numero           ");
			System.out.println("        4 - Atualizar Dados da Conta          ");
			System.out.println("        5 - Apagar Conta                      ");
			System.out.println("        6 - Sacar                             ");
			System.out.println("        7 - Depositar                         ");
			System.out.println("        8 - Transferir valores entre Contas   ");
			System.out.println("        9 - Sair                              ");
			System.out.println("                                              ");
			System.out.println(" ******************************************** ");
			System.out.println("          Entre com a opção desejada:         ");
			System.out.println("                                              " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}
			if (opcao == 9) {
				System.out.println("*** Ratanabá Bank ***");
				sobre();
				break;
			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Criar conta \n\n");

				System.out.println("Digite o Número da Agência: ");
				try {
					agencia = leia.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nDigite valores inteiros!");
					keyPress();
					break;
				}

				try {
					System.out.print("\nDigite o Nome do Titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					verificarString(titular);
				} catch (InputMismatchException e) {
					System.out.println("Erro: " + e.getMessage());
					keyPress();
					break;
				}

				do {
					System.out.println("Digite o tipo da conta (1-CC ou 2-CP)");
					tipo = leia.nextInt();
				} while (tipo < 1 || tipo > 2);

				do {

					System.out.print("\nDigite o Saldo da Conta (R$): ");
					while (!leia.hasNextFloat()) {
						System.out.println("Por favor, digite um valor válido.");
						System.out.print("\nDigite o Saldo da Conta (R$): ");
						leia.next();
					}
					saldo = leia.nextFloat();
					if (saldo < 0)
						System.out.println("O valor não pode ser negativo!");
				} while (saldo < 0);

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Listar todas as contas \n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND
						+ "Consultar dados da conta - por número\n\n");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out
						.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Atualizar dados da conta\n\n");

				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				Optional<Conta> conta = contas.buscarNaCollection(numero);

				if (conta.isPresent()) {
					tipo = conta.get().getTipo();
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();

					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversario da Conta: ");
						aniversario = leia.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}

				} else {
					System.out.println("A Conta número " + numero + " não foi encontrada!");
				}

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Apagar a conta\n\n");
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();

				contas.deletar(numero);

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Saque\n\n");

				System.out.println("Digite o Número da Conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o valor do Saque (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				contas.sacar(numero, valor);

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND + "Depósito\n\n");
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o valor do Depósito (R$): ");
					valor = leia.nextFloat();
				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println(
						Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND_BRIGHT + "Transferência entre contas\n\n");

				System.out.println("Digite o Número da Conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o Número da Conta de Destino: ");
				numeroDestino = leia.nextInt();

				if (numero != numeroDestino) {
					do {
						System.out.println("Digite o valor da Transferência: ");
						valor = leia.nextFloat();
					} while (valor <= 0);
					contas.transferir(numero, numeroDestino, valor);
				} else {
					System.out.println("Os números das Contas são iguais!");
				}

				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_WHITE_BOLD + Cores.ANSI_GREEN_BACKGROUND_BRIGHT + "Opção inválida! \n");
				keyPress();
				break;
			}
		}

	}

	public static void sobre() {
		System.out.println("\n*******************************************");
		System.out.println("Projeto desenvolvido por: ");
		System.out.println("Samara - samaraalmeida379@gmail.com - github.com/als-samara");
	}

	public static void keyPress() {
		try {
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de Enter");
		}
	}

	public static void verificarString(String input) {
		if (!input.matches("^[\\p{L}\\s]+$")) {
			throw new InputMismatchException("O nome do titular só deve conter letras.");
		}
	}

}
