package programa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.Animal;
import entidades.Cliente;
import entidades.Servicos;
import entidades.Cliente.FormaPagamento;

public class SistemaVet {
	
	//=> Formato Global
	public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public static void main(String[] args) {

		//=> Declarações
		Scanner in = new Scanner(System.in);
		String option = "*";
		int cont = 0;
		
		Servicos[] serv = new Servicos[9];
		serv[0] = new Servicos(0, "Consulta", 50.00);
		serv[1] = new Servicos(1, "Exame de Sangue", 67.50);
		serv[2] = new Servicos(2, "Vacinação", 150.00);
		serv[3] = new Servicos(3, "Remoção de Tártaro", 300.00);
		serv[4] = new Servicos(4, "Castração - Gato", 200.00);
		serv[5] = new Servicos(5, "Castração - Cachorro", 400.00);
		serv[6] = new Servicos(6, "Ultrassom/Raio X", 100.00);;
		serv[7] = new Servicos(7, "Internação", 250.00);
		serv[8] = new Servicos(8, "Eutanásia", 300.00);
		
		Cliente[]  cli = new Cliente[100];
		
		//=> Lista de IDs Registrados
		List<Integer> ids = new ArrayList<>();
		
		while(!option.equals("X")) {
			menu();
			option = in.nextLine().toUpperCase();
			
			
			if(option.equals("X")) {
				continue;
			}
			
			switch(option) {
				case "1":
					//=> Variáveis temporárias
					String nome, cpf, telefone, nomeA;
					int animalType, formaPg, op, cont3 = 0, qtdAnimais;
					
					//=> Preencher Dados do Cliente
					System.out.print("\n\nNome do Cliente: ");
					nome = in.nextLine();
					
					System.out.print("CPF do Cliente: ");
					cpf = in.nextLine();
					
					System.out.print("Telefone p/ Contato: ");
					telefone = in.nextLine();
					
					System.out.print("Qtd de Animais: ");
					qtdAnimais = in.nextInt();
					in.nextLine();
					
					//=> Criar Cliente
					cli[cont] = new Cliente(nome, cpf, telefone, qtdAnimais);
					int id = cli[cont].getId();
					ids.add(id);
					
					for(int i = 0; i < qtdAnimais; i++) {
						//=> Preencher Dados do Animal
						System.out.print("\n\nNome do Animal #"+ i+1 +": ");
						nomeA = in.nextLine();
						
						//=> Tipo do Animal
						System.out.println("\nQual o Animal: ");
						System.out.println("0: GATO");
						System.out.println("1: CACHORRO");
						System.out.println("2: AVE");
						System.out.println("3: COELHO");
						System.out.println("4: OUTROS ANIMAIS SILVESTRES");
						System.out.print("R: ");
						animalType = in.nextInt();
						in.nextLine();
						
						cli[cont].animal[i] = new Animal(nomeA, animalType);
						
						//Serviços & Orçamento
						System.out.println("\n\nSelecione os Serviços a serem prestados: ");
						for(Servicos servico : serv) {
							servico.Exibir();
						}
						
						System.out.println("[9]: Encerrar "+ "-".repeat(42) +"\n");
						
						do {
							System.out.print("Serviço: ");
							op = in.nextInt();
							in.nextLine();
							
							if(op != 9) {
								cli[cont].animal[i].servicos[cont3] = op;
								cli[cont].animal[i].setOrcamento(serv[op].preco);
								cli[cont].setOrcamento(serv[op].preco);
								cont3++;
							}
						} while(op != 9);
					}
			
					//=> Forma de Pagamento
					System.out.println("\nForma de Pagamento: ");
					System.out.println("0: DÉBITO");
					System.out.println("1: CRÉDITO");
					System.out.println("2: DINHEIRO");
					System.out.println("3: PIX");
					System.out.print("R: ");
					formaPg = in.nextInt();
					in.nextLine();
					
					cli[cont].setFormaPagamento(formaPg, in);
					
					cont++;
					break;
				
				//=> EXIBIR TODOS OS CLIENTES
				case "2":
					
					System.out.println("\n".repeat(50));
					System.out.println("======> Exibindo Todos os Clientes\n");
					
					for(int cont2 = 0; cont2 < cont; cont2++) {
						
						//System.out.println("id: " + cli[cont2].getId());
						System.out.println("Nome: " + cli[cont2].getNome());
						System.out.print("CPF: " + cli[cont2].getCpf());
						System.out.println(" ".repeat(5) + "Telefone: " + cli[cont2].getTelefone());
						
						for(Animal pet : cli[cont2].animal) {
							System.out.print("\nNome do pet: " + pet.getNome());
							System.out.print(" ".repeat(5) + "Tipo: " + pet.getTipo());
							
							
							System.out.println("\n\nServiços do pet:");
							pet.getServicos(serv);
							System.out.println(" ".repeat(34) + "Orçamento do pet: R$" + pet.getOrcamento());
							
						}
						
						
						System.out.println("\n\n" + " ".repeat(34) + "Orçamento Total: R$" + cli[cont2].getOrcamento());
						System.out.print("Forma de Pagamento: " + cli[cont2].getFormaPagamento());
						
						if(cli[cont2].getFormaPagamento() == FormaPagamento.CREDITO) {
							System.out.print(" ".repeat(8) +"Parcelas: " + cli[cont2].parcelaPagamento + "x");
						}
						System.out.println("");
						
						System.out.println("Status Pagamento: " + cli[cont2].getStatusPagamento());
						System.out.println("\nSituação do Cliente: " + cli[cont2].getSituacao());
						System.out.println("\n\nData de Cadastro: " + cli[cont2].getDataCadastro().format(timeFormat));
						System.out.println("\n\n");
					}
					
					System.out.println("\nPressione ENTER para continuar...");
					in.nextLine();
					break;
					
				case "3":
					int cont2 = 0;
					int buscar;
					System.out.println("Selecione um ID para buscar");
					
					for(Integer idid : ids) {
						System.out.printf("[%d]: %d\n", cont2, idid);
						cont2++;
					}
					
					System.out.print("R: ");
					buscar = in.nextInt();
					in.nextLine();
					
					buscar = ids.get(buscar);
					
					for(Cliente alvo : cli) {
						if(buscar == alvo.getId()) {
							System.out.println("\n\nid: " + alvo.getId());
							System.out.println("Nome: " + alvo.getNome());
							System.out.println("CPF: " + alvo.getCpf());
							//System.out.println("Serviços: "); alvo.animal.getServicos(serv);
							System.out.println("Orçamento: R$" + alvo.getOrcamento());
							System.out.println("Forma de Pagamento: " + alvo.getFormaPagamento());
							System.out.println("Parcelas: " + alvo.parcelaPagamento + "x");
							System.out.println("\n\n");
							
							break;
						}
					}
					
					
					
					
				break;
			}
		}
		
		in.close();
		return;
	}


	public static void menu() {
		LocalDateTime data = LocalDateTime.now();
		
		System.out.println("Data: " + data.format(timeFormat));
		
		
		System.out.println("========== SELECIONE ==========");
		System.out.println("[1]: Cadastrar Novo Cliente");
		System.out.println("[2]: Relatório Diário");
		System.out.println("[3]: Buscar Cliente");
		System.out.println("[X]: Encerrar");
		
		System.out.print("Resposta: ");
	}
}


