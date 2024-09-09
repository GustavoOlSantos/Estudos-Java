package Calculator;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculadora {

	public static void main(String[] args) {

		// => Declaração de Variáveis
		int num1 = 0, num2 = 0, result, op = 0;
		Scanner entrada = new Scanner(System.in);

		do {
			// => Insere o Menu de Operações
			try {
				op = Menu(entrada);
			} catch (InterruptedException e) {
				e.printStackTrace(); // => Lida com a exceção
			}

			// => System.out.println("Escrevendo qualquer coisa para não me encherem o saco
			// que estou digitando pouca coisa");

			if (op != 6) {
				// => Obtenção dos Números
				System.out.print("\nInsira o Primeiro Número: ");
				num1 = entrada.nextInt();
				entrada.nextLine();

				System.out.print("Insira o Segundo Número: ");
				num2 = entrada.nextInt();
				entrada.nextLine();

				System.out.println("\n");
			}

			// => Opções de Operação
			switch (op) {
			case 1:
				result = sum(num1, num2);
				System.out.printf("Resultado da Soma: %d", result);
				break;
			case 2:
				result = sub(num1, num2);
				System.out.printf("Resultado da Subtração: %d", result);
				break;
			case 3:
				result = multi(num1, num2);
				System.out.printf("Resultado da Multiplicação: %d", result);
				break;
			case 4:
				result = div(num1, num2);
				System.out.printf("Resultado da Divisão: %d", result);
				break;
			case 5:
				result = media(num1, num2);
				System.out.printf("Resultado da Média: %d", result);
				break;
			case 6:
				result = 0;
				break;
			}

			if (op != 6) {
				System.out.println("\n\nPressione Enter para continuar...");
				entrada.nextLine();
				clear();
			}

		} while (op != 6);

		// => Encerramento do Programa
		System.out.println("\n".repeat(50));
		entrada.close();

		System.out.println("Encerrando Programa...");

	}

	public static void clear() {
		System.out.println("\n".repeat(50));
	}

	private static int sum(int a, int b) {
		return a + b;
	}

	private static int sub(int a, int b) {
		return a - b;
	}

	private static int multi(int a, int b) {
		return a * b;
	}

	private static int div(int a, int b) {
		return a / b;
	}

	private static int media(int a, int b) {
		return (a + b) / 2;
	}

	private static int Menu(Scanner scan) throws InterruptedException {
		int resposta = 0;
		int flag = 0;

		while (!(resposta >= 1 && resposta <= 6)) {

			if (flag == 1) {
				clear();
				System.out.println("Opção Inválida selecionada! \n");
			}

			System.out.println("|================== SELECIONE UMA OPÇÃO ==================|");
			Thread.sleep(100);
			System.out.println("| • 1 -> Somar                                            |");
			Thread.sleep(100);
			System.out.println("| • 2 -> Subtrair                                         |");
			Thread.sleep(100);
			System.out.println("| • 3 -> Multiplicar                                      |");
			Thread.sleep(100);
			System.out.println("| • 4 -> Dividir                                          |");
			Thread.sleep(100);
			System.out.println("| • 5 -> Média                                            |");
			Thread.sleep(100);
			System.out.println("| • 6 -> Encerar                                          |");
			Thread.sleep(100);
			System.out.println("|=========================================================|");
			Thread.sleep(100);
			System.out.print("Resposta: ");

			try {
				resposta = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				flag = 1;
				scan.next();
			}

			if (!(resposta >= 1 && resposta <= 6)) {
				flag = 1;
			}
		}

		return resposta;
	}

}
