package program;

import java.util.Scanner;
import entities.Alunos;

public class Pensionato {

	public static void main(String[] args) {
		// Declarações
		Scanner in = new Scanner(System.in);
		Alunos[] quartos = new Alunos[10];
		int qtd = 0;
		
		System.out.print("Quantos quartos serão reservados? \nR: ");
		qtd = in.nextInt();
		in.nextLine();
		
		//=> Reserva de Quarto
		for(int i = 0; i < qtd; i++) {
			String nome;
			String email;
			int quarto = 0;
			
			System.out.println("\nReserva #" + (i+1) + ":");
			System.out.print("Nome: ");
			nome = in.nextLine();
			
			System.out.print("Email: ");
			email = in.nextLine();
			
			System.out.print("Número do Quarto (0 a 9): ");
			quarto = in.nextInt();
			in.nextLine();
			
			quartos[quarto] = new Alunos(nome, email);
		}
		
		//=> Exibição das Reservas
		System.out.println("\n\nQuartos Ocupados: ");
		for(int i = 0; i < 10; i++) {
			
			if(quartos[i] != null) {
				System.out.println(i +": "+ quartos[i].getNome() + ", " + quartos[i].getEmail());
			}
		}
	}

}
