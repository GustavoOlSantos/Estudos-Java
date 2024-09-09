package entities;

public class Aluno {
	
	//=> Variáveis do Objeto
	final private double MAX = 100.00;
	private double notaF;
	
	public String nome;
	public double n1, n2, n3;	
	
	//=> Métodos do Objeto
	public double NotaFinal() {
		notaF = n1 + n2 + n3;
		return notaF;
	}
	
	public String NotaPendente() {
		if(MAX * 0.6 > notaF) {
			double pendente = (MAX*0.6) - notaF;
			
			return "\nReprovado. \nFaltam " + pendente + " Pontos.";
		}
		
		else {
			return "\nAprovado.";
		}
	}
}
