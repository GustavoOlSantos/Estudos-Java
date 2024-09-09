package entities;

public class Employee {
	
	//=> Variáveis do Objeto
	public String name;
	public double grossSalary;
	public double tax;
	
	//=> Métodos do Objeto
	
	//Salário Líquido
	public double NetSalary() {
		return grossSalary - tax;
	}
	
	public void IncreaseSalary(double percentage) {
		double finalSalary = (grossSalary - tax) + (grossSalary * percentage/100);
		
		System.out.printf("\n\nSalário Final: R$ %.2f \n\n", finalSalary);
	}
	
	public String toString() {
		return name;
	}
}
