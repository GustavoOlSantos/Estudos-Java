package entities;

import java.util.Arrays;

import exceptions.DomainException;

public class Animal {
	
	private String nome;
	private AnimalSexo sexo;
	public enum AnimalSexo{MACHO, FÊMEA, HERMAFRODITA};
	
	private AnimalTipo tipo; 
	public enum AnimalTipo {GATO, CACHORRO, AVE, COELHO, TARTARUGA, COBRA, LAGARTO, OUTROS_SILVESTRES};
	
	public int[] servicos = new int[8];
	private double orcamento;
	
	public Animal() {}
	
	public Animal(String nome, int tipo, int sexo) throws DomainException{
		this.nome = nome;
		
		if(nome.length() == 0) {
			throw new DomainException("O nome do Animal não pode estar vazio.");
		}
		
		setSexo(sexo);
		setTipo(tipo);
		
		
		Arrays.fill(this.servicos, -1); //=> Define toda a array como não incializada
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public AnimalSexo getSexo() {
		return sexo;
	}
	
	public void setSexo(int sexo) throws DomainException {
		switch(sexo) {
			case 0: this.sexo = AnimalSexo.MACHO; 		 break;
			case 1: this.sexo = AnimalSexo.FÊMEA; 		 break;
			case 2: this.sexo = AnimalSexo.HERMAFRODITA; break;
			default: throw new DomainException("Opção Inválida apra Sexo do Animal.");
		}
	}
	
	public void setTipo(int tipo) throws DomainException {
		switch(tipo) {
			case 0: this.tipo = AnimalTipo.GATO; 				break;
			case 1: this.tipo = AnimalTipo.CACHORRO; 			break;
			case 2: this.tipo = AnimalTipo.AVE;					break;
			case 3: this.tipo = AnimalTipo.COELHO; 				break;
			case 4: this.tipo = AnimalTipo.TARTARUGA; 			break;
			case 5: this.tipo = AnimalTipo.COBRA; 				break;
			case 6: this.tipo = AnimalTipo.LAGARTO; 			break;
			case 7: this.tipo = AnimalTipo.OUTROS_SILVESTRES; 	break;
			default: throw new DomainException("Tipo Inválido de animal.");
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

