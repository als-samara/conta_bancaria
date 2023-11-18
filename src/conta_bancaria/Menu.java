package conta_bancaria;

import java.util.Scanner;
import conta_bancaria.model.Conta;
import conta_bancaria.util.Cores;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;

public class Menu {
	static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao;
		
		// teste da classe conta
		Conta c1 = new Conta(1, 123, 1, "Samara", 5000.00f);
		c1.visualizar();
		c1.sacar(400.00f);
		c1.depositar(800.00f);
		c1.visualizar();
		
		// teste da classe conta corrente
		ContaCorrente cc1 = new ContaCorrente(2, 123, 1, "Ian Neves", 1500.00f, 800.00f);
		cc1.visualizar();
		cc1.sacar(3000.00f);
		cc1.sacar(1700.00f);
		cc1.visualizar();
		
		// Teste da Classe Conta Poupança
		ContaPoupanca cp1 = new ContaPoupanca(2, 123, 2, "Maria dos Santos", 100000.0f, 15);
		cp1.visualizar();
		cp1.sacar(1000.0f);
		cp1.visualizar();
		cp1.depositar(5000.0f);
		cp1.visualizar();
		
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
			System.out.println("                                              "+Cores.TEXT_RESET);
			opcao = leia.nextInt();
			if(opcao == 9) {
				System.out.println("*** Ratanabá Bank ***");
				sobre();
				break;
			}
			switch(opcao) {
			case 1 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Criar conta \n\n");
			case 2 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Listar todas as contas \n\n");
			case 3 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Consultar dados da conta - por número\n\n");
			case 4 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Atualizar dados da conta\n\n");
			case 5 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Apagar a conta\n\n");
			case 6 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Saque\n\n");
			case 7 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND+"Depósito\n\n");
			case 8 -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND_BRIGHT+"Transferência entre contas\n\n");
			default -> System.out.println(Cores.TEXT_WHITE_BOLD+Cores.ANSI_GREEN_BACKGROUND_BRIGHT+"Opção inválida! \n");
			}
		}
				

	}
	
	public static void sobre() {
		System.out.println("\n*******************************************");
		System.out.println("Projeto desenvolvido por: ");
		System.out.println("Samara - samaraalmeida379@gmail.com - github.com/als-samara");
	}

}
