package Objetos;

//=> Classe que não pode ser Herdada por outra
public final class ContaPoupanca extends Conta{

	private Double taxaRendimento;
	
	//=> CONSTRUTORES
	public ContaPoupanca() {
		//=> Construtor da SuperClasse
		super();
	} 
	
	public ContaPoupanca(Integer numero, String titular, Double saldo, Double taxaRendimento) {
		//=> Construtor da SuperClasse
		super(numero, titular, saldo); 
		this.taxaRendimento = taxaRendimento;
	}
	
	//=> MÉTODOS
	public Double getTaxa() {
		return taxaRendimento;
	}
	
	public void setTaxa(Double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}
	
	public void updateSaldo() {
		saldo += saldo * taxaRendimento;
	}
	
	@Override  //=> Método que se sobreopõe ao da SuperClasse
	public final void saque(Double valor) { //=> esse método não pode ser sobrescrito
		saldo -= valor;
	}
}
