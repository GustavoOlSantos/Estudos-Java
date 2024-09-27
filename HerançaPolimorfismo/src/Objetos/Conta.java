package Objetos;

public class Conta {
	
	private Integer numero;
	private String titular;
	protected Double saldo;
	
	//=> CONSTRUTORES
	public Conta() {}
	
	public Conta(Integer numero, String titular, Double saldo) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}
	
	
	//=> MÉTODOS
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public void SetTitular(String titular) {
		this.titular = titular;
	}
	
	public Double getSaldo() {
		return this.saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	//=> Métodos com sobreposição
	
	public void saque(Double valor) {
		saldo -= valor + 5.00;
	}
	
	public void deposito(Double valor) {
		saldo += valor;
	}
}
