package entidades;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

import entidades.Animal.AnimalTipo;

public class Cliente {
	
	private int id;
	
	private String nome;
	private String cpf;
	private String telefone;
	
	//public int[] servicos = new int[8];
	private int NAO_INICIALIZADO;
	
	private double orcamentoTotal;
	
	private FormaPagamento formaPagamento; 
	public enum FormaPagamento {DEBITO, CREDITO, DINHEIRO, PIX};
	public int parcelaPagamento;
	
	private StatusPagamento status;
	public enum StatusPagamento {PENDENTE, PAGO}
	
	private Situacao trabalho;
	public enum Situacao {TRABALHANDO, FINALIZADO, CANCELADO, EXCLUÍDO}
	
	private LocalDateTime dataCadastro;
	
	public Animal[] animal;
	public int qtdAnimal;
	
	
	//=> Constrututor 
	public Cliente() {
		this.id = UUID.randomUUID().hashCode();		   //=> Gera um ID para o cliente
		this.NAO_INICIALIZADO = -1;

		for(Animal each : animal) {
			Arrays.fill(each.servicos, NAO_INICIALIZADO); //=> Define toda a array como não incializada
		}
		
		this.trabalho = Situacao.TRABALHANDO; 		 //=> Define a situação do cliente como trabalhando
		this.status = StatusPagamento.PENDENTE;		//=> Deixa o pagamento como pendente
		this.dataCadastro = LocalDateTime.now();
	}

	public Cliente(String nome, String cpf, String num, int qtd) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = num;
		this.qtdAnimal = qtd;
		
		animal = new Animal[qtd];
		
		this.id = UUID.randomUUID().hashCode();		   //=> Gera um ID para o cliente
		this.NAO_INICIALIZADO = -1;
		
		this.trabalho = Situacao.TRABALHANDO; 		 	 //=> Define a situação do cliente como trabalhando
		this.status = StatusPagamento.PENDENTE;		    //=> Deixa o pagamento como pendente
		this.dataCadastro = LocalDateTime.now();
	}
	
	//===> MÉTODOS
	
	//======> Nome
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//======> CPF

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	//======> Telefone

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	//======> Orçamento
	public double getOrcamento(){
		return this.orcamentoTotal;
	}

	public void setOrcamento(double value){
		orcamentoTotal += value;
	}
	
	//======> Id
	public int getId() {
		return this.id;
	}
	
	//======> Forma de Pigment
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(int formaPg, Scanner in) {
    	
    	switch(formaPg) {
			case 0: this.formaPagamento = FormaPagamento.DEBITO; break;
			case 1: 
				this.formaPagamento = FormaPagamento.CREDITO; 
				
				System.out.print("\n\nSerá parcelado em quantas vezes?(Limite 6)\nR:");
				this.parcelaPagamento = in.nextInt();
				in.nextLine();
			break;
			case 2: this.formaPagamento = FormaPagamento.DINHEIRO; break;
			case 3: this.formaPagamento = FormaPagamento.PIX; break;
    	}
        
        //=> Define o Valor da Parcela como 1
        if(formaPagamento == FormaPagamento.DEBITO || formaPagamento == FormaPagamento.DINHEIRO || formaPagamento == FormaPagamento.PIX) {
        	this.parcelaPagamento = 1;
        }
    }
    
    //======> Status do Pagamento
    public StatusPagamento getStatusPagamento() {
    	return status;
    }
    
    public void setStatusPagamento(StatusPagamento stts) {
    	this.status = stts;
    }
    
    ///======> Finalizado
    public Situacao getSituacao() {
        return trabalho;
    }
    
    public void setSituacao(Situacao stts) {
        this.trabalho = stts;
    }	
    
    public LocalDateTime getDataCadastro() {
    	return dataCadastro;
    }
}