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

		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);

		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);

		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Raos", 4000f, 12);
		contas.cadastrar(cp2);

		contas.listarTodas();

		while (true) {
			System.out.println(Cores.temaMenu);
			System.out.println(" ************************************************************** ");
			System.out.println("                                                                ");
			System.out.println("                     ** RATANABÁ BANK **                        ");
			System.out.println("                                                                ");
			System.out.println(" ************************************************************** ");
			System.out.println("                                                                ");
			System.out.println("                   1 - Criar Conta                              ");
			System.out.println("                   2 - Listar todas as Contas                   ");
			System.out.println("                   3 - Buscar Conta por Numero                  ");
			System.out.println("                   4 - Atualizar Dados da Conta                 ");
			System.out.println("                   5 - Apagar Conta                             ");
			System.out.println("                   6 - Sacar                                    ");
			System.out.println("                   7 - Depositar                                ");
			System.out.println("                   8 - Transferir valores entre Contas          ");
			System.out.println("                   9 - Sair                                     ");
			System.out.println("                                                                ");
			System.out.println(" ************************************************************** ");
			System.out.println("                                                                ");
			System.out.println("                    Entre com a opção desejada:                 ");
			System.out.println("                                                                ");
			System.out.println(" ************************************************************** " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out
						.println(Cores.temaErros + "                    Digite valores inteiros!                    ");
				leia.nextLine();
				opcao = 0;
			}
			if (opcao == 9) {
				System.out
						.println(Cores.temaMenu2 + "                                                                ");
				System.out.println("                          Ratanabá Bank                         ");
				System.out.println("                                                                ");
				sobre();
				break;
			}
			switch (opcao) {
			case 1:
				System.out
						.println(Cores.temaMenu2 + "                          Criar conta                           ");
				System.out.println("                                                                ");

				System.out
						.println("Digite o Número da Agência:                                     " + Cores.TEXT_RESET);
				try {
					agencia = leia.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(
							Cores.temaErros + "                    Digite valores inteiros!                    ");
					keyPress();
					break;
				}

				try {
					System.out.println(Cores.temaMenu2
							+ "Digite o Nome do Titular:                                       " + Cores.TEXT_RESET);
					leia.skip("\\R");
					titular = leia.nextLine();
					verificarString(titular);
				} catch (InputMismatchException e) {
					System.out.println(Cores.temaErros + "Erro: " + e.getMessage() + "                  ");
					keyPress();
					break;
				}

				try {
					do {
						System.out.println(
								Cores.temaMenu2 + "Digite o tipo da conta (1-CC ou 2-CP)                           "
										+ Cores.TEXT_RESET);
						tipo = leia.nextInt();
					} while (tipo < 1 || tipo > 2);
				} catch (InputMismatchException e) {
					System.out.println(
							Cores.temaErros + "Erro: Tipo inválido!                                            ");
					keyPress();
					break;
				}

				do {
					System.out.println(Cores.temaMenu2
							+ "Digite o Saldo da Conta (R$):                                   " + Cores.TEXT_RESET);
					while (!leia.hasNextFloat()) {
						System.out.println(
								Cores.temaErros + "Por favor, digite um valor válido                               ");
						System.out.println(
								Cores.temaMenu2 + "Digite o Saldo da Conta (R$):                                   "
										+ Cores.TEXT_RESET);
						leia.next();
					}
					saldo = leia.nextFloat();
					if (saldo < 0)
						System.out.println(
								Cores.temaErros + "Erro: O valor não pode ser negativo!                            ");
				} while (saldo < 0);

				switch (tipo) {
				case 1 -> {
					do {
						System.out.println(
								Cores.temaMenu2 + "Digite o Limite de Crédito (R$):                                "
										+ Cores.TEXT_RESET);
						while (!leia.hasNextFloat()) {
							System.out.println(Cores.temaErros
									+ "Erro: Por favor, digite um valor válido                         ");
							System.out.println(
									Cores.temaMenu2 + "Digite o Limite de Crédito (R$):                                "
											+ Cores.TEXT_RESET);
							leia.next();
						}
						limite = leia.nextFloat();
						if (limite < 0) {
							System.out.println(Cores.temaErros
									+ "Erro: O valor não pode ser negativo!                            ");
						}
					} while (limite < 0);

					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					do {
						System.out
								.println(Cores.temaMenu2 + "Digite o dia do Aniversario da Conta:                           " + Cores.TEXT_RESET);
						while (!leia.hasNextInt()) {
							System.out.println(Cores.temaErros
									+ "Erro: por favor, digite um número inteiro                       ");
							System.out.println(Cores.temaMenu2
									+ "Digite o dia do Aniversario da Conta:                           "
									+ Cores.TEXT_RESET);
							leia.next();
						}
						aniversario = leia.nextInt();
						if (aniversario < 0 || aniversario > 31) {
							System.out.println(Cores.temaErros + "Erro: digite um número inteiro positivo, menor ou igual a 31    ");
						}
					} while (aniversario < 0 || aniversario > 31);

					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out
						.println(Cores.temaMenu2 + "                                                                ");
				System.out.println("                     Listar todas as contas                     ");
				System.out.println("                                                                ");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(
						Cores.temaMenu2 + "\n            Consultar dados da conta - por número               \n");

				try {
					System.out.println(
							"Digite o número da conta:                                       " + Cores.TEXT_RESET);
					numero = leia.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(
							Cores.temaErros + "Erro: por favor, digite um número de Conta válido!              ");
					keyPress();
					break;
				}
				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println(
						Cores.temaMenu2 + "\n                  Atualizar dados da conta                      \n");

				try {
					System.out.println("Digite o número da conta:                                       " + Cores.TEXT_RESET);
					numero = leia.nextInt();
				} catch (InputMismatchException e) {
					System.out.println(
							Cores.temaErros + "\nErro: por favor, digite um número de Conta válido!              ");
					keyPress();
					break;
				}
				Optional<Conta> conta = contas.buscarNaCollection(numero);

				if (conta.isPresent()) {
					tipo = conta.get().getTipo();

					try {
						System.out.println(Cores.temaMenu2 + "Digite o número da Agência:                                     " + Cores.TEXT_RESET);
						agencia = leia.nextInt();
					} catch (InputMismatchException e) {
						System.out.println(
								Cores.temaErros + "\nErro: por favor, digite um número de Agência válido!            ");
						keyPress();
						break;
					}
					try {
						System.out.println(Cores.temaMenu2
								+ "Digite o Nome do Titular:                                       " + Cores.TEXT_RESET);
						leia.skip("\\R");
						titular = leia.nextLine();
						verificarString(titular);
					} catch (InputMismatchException e) {
						System.out.println(Cores.temaErros + "Erro: " + e.getMessage() + "                  ");
						keyPress();
						break;
					}

					switch (tipo) {
					case 1 -> {
						System.out.println(Cores.temaMenu2 + "Digite o Limite de Crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, conta.get().getSaldo(), limite));
						System.out.println(Cores.TEXT_YELLOW_BOLD
								+ "\nSaldo da conta: " + conta.get().getSaldo() + "(R$)\nAtenção: o saldo da Conta não pode ser editado!");
					}
					case 2 -> {
						System.out.println(Cores.temaMenu2 + "Digite o dia do Aniversario da Conta: ");
						aniversario = leia.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, conta.get().getSaldo(), aniversario));
						System.out.println(Cores.TEXT_YELLOW_BOLD
								+ "\nSaldo da conta: " + conta.get().getSaldo() + "(R$)\nAtenção: o saldo da Conta não pode ser editado!");
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
					}
					}

				} else {
					System.out.println(Cores.temaErros + "A Conta número " + numero
							+ " não foi encontrada!                            ");
				}

				keyPress();
				break;
			case 5:
				System.out.println(Cores.temaMenu2 + "\n                        Apagar a conta                          \n");
				
				try {
					System.out.println("Digite o número da Conta:                                       " + Cores.TEXT_RESET);
					numero = leia.nextInt();
					contas.deletar(numero);
					keyPress();
				}catch(InputMismatchException e) {
					System.out.println(Cores.temaErros + "Digite um número válido!                                        ");
					keyPress();
					break;
				}
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
				System.out
						.println(Cores.temaErros + "                        [Opção inválida]                        ");
				keyPress();
				break;
			}
		}

	}

	public static void sobre() {
		System.out.println(Cores.temaMenu2 + " ************************************************************** ");
		System.out.println("                                                                ");
		System.out.println("                    Projeto desenvolvido por:                   ");
		System.out.println("            Samara S. - https://github.com/als-samara           ");
		System.out.println("                                                                ");
		System.out.println(" ************************************************************** ");
		System.out.println("                                                                ");
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
