package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class controleBasicoEstoque {

	public static void main(String[] args) {
		//=> Definições de funções
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		
		//=> Definição de Objetos/Variáveis
		Product produto;
		produto = new Product();
		
		
		//=> Obtenção de Informações
		System.out.println("Insira as informações do produto: ");
		
		System.out.print("Nome: ");
		produto.name = in.nextLine();
		
		System.out.print("Preço: ");
		produto.price = in.nextDouble();
		
		System.out.print("Quantidade em estoque: ");
		produto.quantity = in.nextInt();
		in.nextLine();
		
		double total = produto.TotalValueInStock();
		System.out.printf("\n\nValor original em Estoque: %.2f \n\n", total);
		
		//=> Adição no Estoque
		System.out.println("Quantos novos items chegaram ao estoque do produto "+ produto.name +"?");
		System.out.print("Quantidade: ");
		int qtd = in.nextInt();
		produto.AddProducts(qtd);
		in.nextLine();
		
		total = produto.TotalValueInStock();
		System.out.printf("\n\nValor Após Adição no Estoque: %.2f \n\n", total);
		
		//=> Remoção do Estoque
		System.out.println("Quantos items saíram do estoque do produto "+ produto.name +"?");
		System.out.print("Quantidade: ");
		qtd = in.nextInt();
		produto.RemoveProducts(qtd);
		in.nextLine();
		
		total = produto.TotalValueInStock();
		System.out.printf("\n\nValor Após Remoção no Estoque: %.2f \n\n", total);
		
		in.close();
	}

}
