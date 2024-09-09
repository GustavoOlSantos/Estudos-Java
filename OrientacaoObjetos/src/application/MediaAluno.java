package application;

import java.util.Scanner;
import java.util.Locale;
import entities.Aluno;

public class MediaAluno {

	public static void main(String[] args) {

		//=> Declaração de Funções
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		
		//=> Declaração de Variáveis/Objetos
		Aluno a1;
		a1 = new Aluno();
		
		System.out.println("Preencha o nome e notas do Aluno: ");
		System.out.print("Nome: ");
		a1.nome = in.nextLine();
		
		System.out.print("Nota 1: ");
		a1.n1 = in.nextDouble();
		
		System.out.print("Nota 2: ");
		a1.n2 = in.nextDouble();
		
		System.out.print("Nota 3: ");
		a1.n3 = in.nextDouble();
		
		System.out.printf("\n\nNota Final %.2f", a1.NotaFinal());
		
		System.out.println(a1.NotaPendente());
		
		in.close();
		
	}

}
