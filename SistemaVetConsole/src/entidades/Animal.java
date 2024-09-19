package entidades;

import java.util.Arrays;

public class Animal {
	
	private String nome;
	
	private AnimalTipo tipo; 
	public enum AnimalTipo {GATO, CACHORRO, AVE, COELHO, OUTROS_SILVESTRES};
	
	public int[] servicos = new int[8];
	private double orcamento;
	
	public Animal() {}
	
	public Animal(String nome, int tipo) {
		this.nome = nome;
		
		switch(tipo) {
			case 0: this.tipo = AnimalTipo.GATO; 				break;
			case 1: this.tipo = AnimalTipo.CACHORRO; 			break;
			case 2: this.tipo = AnimalTipo.AVE;				break;
			case 3: this.tipo = AnimalTipo.COELHO; 				break;
			case 4: this.tipo = AnimalTipo.OUTROS_SILVESTRES; 	break;
		}
		
		Arrays.fill(this.servicos, -1); //=> Define toda a array como não incializada
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTipo(int tipo) {
		switch(tipo) {
			case 0: this.tipo = AnimalTipo.GATO; 				break;
			case 1: this.tipo = AnimalTipo.CACHORRO; 			break;
			case 2: this.tipo = AnimalTipo.AVE;				break;
			case 3: this.tipo = AnimalTipo.COELHO; 				break;
			case 4: this.tipo = AnimalTipo.OUTROS_SILVESTRES; 	break;
		}
	}
	
	public AnimalTipo getTipo() {
		return tipo;
	}
	
	//======> Orçamento
	public double getOrcamento(){
		return this.orcamento;
	}
	
	public void setOrcamento(double value){
		orcamento += value;
	}
	
	//======> Serviços
	public void getServicos(Servicos[] serv) {
		for(Integer servicoSelecionado : this.servicos) {
			if(servicoSelecionado >= 0 && servicoSelecionado < serv.length && servicoSelecionado != -1) {
				System.out.println(serv[servicoSelecionado].nome + " " + "-".repeat(50 - serv[servicoSelecionado].nome.length()) + " R$" + serv[servicoSelecionado].preco);
			}
		}
	}
}

