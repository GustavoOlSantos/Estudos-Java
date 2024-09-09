package program;

import java.util.Scanner;
import entities.Pessoa;

public class Alturas {

	public static void main(String[] args) {
	
		//=> Decvlarações
		Scanner in = new Scanner(System.in);
		int qtd = 0;
		
		//=> Obtém a quantidade de pessoas a serem cadastradas
		System.out.print("Quantas Pessoas Serão Cadastradas?\nR: ");
		qtd = in.nextInt();
		in.nextLine();
		
		Pessoa[] P = new Pessoa[qtd];
		
		//=> Recebe rDados
		for(int i = 0; i < P.length; i++) {
			
			String nome;
			int idade;
			double altura;
			
			System.out.print("\nPessoa Nº "+ (i+1) + ":\n");
			System.out.print("Nome: ");
			nome = in.nextLine();
			
			System.out.print("Idade: ");
			idade = in.nextInt();
			
			System.out.print("Altura: ");
			altura = in.nextDouble();
			in.nextLine();
			
			P[i] = new Pessoa(nome, idade, altura);
		}
		
		//=> Processar Dados
		double alturaMedia = 0;
		double alturas = 0;
		String nomes = "";
		int menores16 = 0;
		double porcentagem;
		
		for(int i = 0; i < P.length; i++) {
			alturas += P[i].getAltura();
			
			if(P[i].getIdade() < 16) {
				nomes += P[i].getNome() + "\n";
				menores16++;
			}
			
		}
		
		alturaMedia = alturas/P.length;
		porcentagem = 100 * (float) menores16 / qtd;
		
		System.out.printf("Altura Média: %.2f \n", alturaMedia);
		System.out.printf("Pessoas com menos de 16 anos: %.1f%% \n", porcentagem);
		System.out.println(nomes);
	}

}
