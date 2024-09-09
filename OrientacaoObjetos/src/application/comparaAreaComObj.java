package application;

import java.util.Scanner;
import java.util.Locale;

import entities.Triangle;

public class comparaAreaComObj {
	
	//=. Programa Para comparar a área de dois triângulos (Com Orientação a Objetos)
	public static void main(String[] args) {
		
		//=> Definição de Funções
		Locale.setDefault(Locale.US);
		Scanner entrada = new Scanner(System.in);
		
		//=> Declaração de Variáveis/Objetos
		Triangle x, y;
		x = new Triangle();
		y = new Triangle();
		
		double areaX, areaY;
		
		System.out.println("Insira as Medidas do Triângulo X: ");
		x.a = entrada.nextDouble();
		x.b = entrada.nextDouble();
		x.c = entrada.nextDouble();
		
		System.out.println("\n\nInsira as Medidas do Triângulo Y: ");
		y.a = entrada.nextDouble();
		y.b = entrada.nextDouble();
		y.c = entrada.nextDouble();
		
		//=> Calcula a área
		areaX = AreaTriangle(x.a, x.b, x.c);
		areaY = AreaTriangle(y.a, y.b, y.c);
		
		System.out.printf("Área do triângulo X: %.4f \n", areaX);
		System.out.printf("Área do triângulo Y: %.4f \n", areaY);
		
		if(areaX > areaY) {
			System.out.println("A área do Triângulo X é maior");
		}
		else if(areaX < areaY) {
			System.out.println("Área do triângulo Y é maior");
		}
		else {
			System.out.println("As áreas são iguais");
		}
		
		entrada.close();
	}
	
	private static double AreaTriangle(double a, double b, double c) {
		double p = (a + b + c)/2;
		double Area =  Math.sqrt(p * (p - a) * (p - b) * (p - c));
		
		return Area;
	}

}
