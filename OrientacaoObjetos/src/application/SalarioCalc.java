package application;

//=> Importações
import java.util.Locale;
import java.util.Scanner;
import entities.Employee; 

public class SalarioCalc {

	public static void main(String[] args) {
		
		//=> Declaração de Funções
		Locale.setDefault(Locale.US);
		Scanner in = new Scanner(System.in);
		
		//=> Declaração de Variáveis/Objetos
		Employee funcionario;
		funcionario = new Employee();
		
		System.out.println("Insira as Informações do funcionário:");
		System.out.print("Nome: ");
		funcionario.name = in.nextLine();
		
		System.out.print("Salário Bruto: R$");
		funcionario.grossSalary = in.nextDouble();
		
		System.out.print("Taxa: ");
		funcionario.tax = in.nextDouble();
		
		System.out.printf("\n\nFuncionario %s, %.2f\n\n", funcionario, funcionario.NetSalary());
		
		System.out.print("Qual é a porcentagem de Acréscimo? ");
		double acres = in.nextDouble();
		
		funcionario.IncreaseSalary(acres);
		
		in.close();
	}

}
