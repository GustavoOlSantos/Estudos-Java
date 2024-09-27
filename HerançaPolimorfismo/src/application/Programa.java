package application;

import Objetos.Conta;
import Objetos.ContaPoupanca;
import Objetos.ContaSalario;

public class Programa {

	public static void main(String[] args) {
		
		//=> TESTES OVERRIDE + UPCASTING
		
		Conta acc = new Conta(1001, "Gustavo", 1000.0);
		acc.saque(200.0);
		System.out.println("Saldo da Conta Corrente: " + acc.getSaldo());
		
		Conta acc2 = new ContaPoupanca(1002, "Gustavo", 1000.0, 0.01);
		acc2.saque(200.0);
		System.out.println("Saldo da Conta Poupança: " + acc2.getSaldo());
		
		Conta acc3 = new ContaSalario(1003, "Gustavo", 1000.0, 500.0);
		acc3.saque(200.0);
		System.out.println("Saldo da Conta Salário: " + acc3.getSaldo());
	}

}
