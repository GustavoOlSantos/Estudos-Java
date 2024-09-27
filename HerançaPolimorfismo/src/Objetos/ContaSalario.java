package Objetos;

public class ContaSalario extends Conta {
	
	private Double limiteEmprestimo;
	
	//=> CONSTRUTORES
	public ContaSalario() {
		super();
	}
	
	public ContaSalario(Integer numero, String titular, Double saldo, Double limiteEmprestimo) {
		//=> Construtor da SuperClasse
		super(numero, titular, saldo);
		
		this.limiteEmprestimo = limiteEmprestimo;
	}
	
	
	//=> MÉTODOS 
	
	public void Emprestimo(Double valor) {
		System.out.println("foi");
	}

	public Double getLimiteEmprestimo() {
		return limiteEmprestimo;
	}

	public void setLimiteEmprestimo(Double limiteEmprestimo) {
		this.limiteEmprestimo = limiteEmprestimo;
	}
	
	@Override  //=> Método que se sobreopõe ao da SuperClasse e adiciona mais um processo
	public void saque(Double valor) {
		super.saque(valor); //=> Executa o Saque da super
		saldo -= 2.0;
	}
}
