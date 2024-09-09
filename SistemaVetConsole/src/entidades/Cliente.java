package entidades;

import java.util.Arrays;
import java.util.UUID;

public class Cliente {
	
	private int id;
	
	public String nome;
	public String cpf;
	public String telefone;
	
	public int[] servicos = new int[8];
	private int NAO_INICIALIZADO;
	
	private double orcamento;
	
	private FormaPagamento formaPagamento; 
	public enum FormaPagamento {DEBITO, CREDITO, DINHEIRO, PIX};
	public int parcelaPagamento;
	
	private StatusPagamento status;
	public enum StatusPagamento {PENDENTE, PAGO}
	
	private Situacao trabalho;
	public enum Situacao {TRABALHANDO, FINALIZADO, CANCELADO, EXCLUÍDO}
	
	public class animal{
		
	}
	
	//=> Constrututor 
	public Cliente() {
		this.id = UUID.randomUUID().hashCode();		   //=> Gera um ID para o cliente
		this.NAO_INICIALIZADO = -1;
		Arrays.fill(this.servicos, NAO_INICIALIZADO); //=> Define toda a array como não incializada
		this.trabalho = Situacao.TRABALHANDO; 		 //=> Define a situação do cliente como trabalhando
		this.status = StatusPagamento.PENDENTE;		//=> Deixa o pagamento como pendente
	}
	
	//===> MÉTODOS
	
	//======> Orçamento
	public double getOrcamento(){
		return this.orcamento;
	}
	
	public void setOrcamento(double value){
		orcamento += value;
	}
	
	//======> Id
	public int getId() {
		return this.id;
	}
	
	//======> Serviços
		public void getServicos(Servicos[] serv) {
			for(Integer servicoSelecionado : this.servicos) {
				if(servicoSelecionado >= 0 && servicoSelecionado < serv.length && servicoSelecionado != NAO_INICIALIZADO) {
					System.out.println(serv[servicoSelecionado].nome + " " + "-".repeat(50 - serv[servicoSelecionado].nome.length()) + " R$" + serv[servicoSelecionado].preco);
				}
			}
		}
	
	//======> Forma de Pigment
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
        
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
}