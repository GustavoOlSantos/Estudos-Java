package application;

//=> Importações
import java.util.Locale;
import java.util.Scanner;
import entities.retangulo;

public class geometriaRetangulo {

	public static void main(String[] args) {
		//=> Definições de Funções
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		
		//=> Declaração de Variáveis/Objetos
		retangulo x;
		x = new retangulo();
		
		System.out.println("Insira a Base e Altura de seu retângulo (em cm).");
		System.out.print("Base: ");
		x.width = in.nextDouble();
		
		System.out.print("Altura: ");
		x.height = in.nextDouble();
		
		System.out.println("\n\n========== PARÂMETROS DO RETÂNGULO FORNECIDO ===============");
		System.out.printf("Área:       %.2f cm \n",  x.Area());
		System.out.printf("Perímetro:  %.2f cm \n", x.Perimeter());
		System.out.printf("Diagonal:   %.2f cm \n" ,x.Diagonal());

		in.close();
	}

}
