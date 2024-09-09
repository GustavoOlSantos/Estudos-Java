package application;

import java.util.Scanner;
import java.util.Locale;

public class comparaAreaSemObj {
	
	
	//=. Programa Para comparar a área de dois triângulos (Sem orientação a Objetos)
	public static void main(String[] args) {
		
		//=> Definição de Funções
		Locale.setDefault(Locale.US);
		Scanner entrada = new Scanner(System.in);
		
		//=> Declaração de Variáveis
		double xA, xB, xC, yA, yB, yC;
		double p, areaX, areaY;
		
		System.out.println("Insira as Medidas do Triângulo X: ");
		xA = entrada.nextDouble();
		xB = entrada.nextDouble();
		xC = entrada.nextDouble();
		
		System.out.println("\n\nInsira as Medidas do Triângulo Y: ");
		yA = entrada.nextDouble();
		yB = entrada.nextDouble();
		yC = entrada.nextDouble();
		
		p = (xA + xB + xC)/2;
		areaX = Math.sqrt(p * (p - xA) * (p - xB) * (p - xC));
		
		p = (yA + yB + yC)/2;
		areaY = Math.sqrt(p * (p - yA) * (p - yB) * (p - yC));
		
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

}
