package application;

//=> Date Imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//=> UtilsImport
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//=> Entities Imports
import entities.Animal;
import entities.Cliente;
import entities.Servicos;

import java.io.IOException;
//=> SQL imports
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//=> Database Imports
import db.DB;
import db.DbException;
import db.DbIntegrityException; 

//=> Exceptions Imports
import exceptions.DomainException;
import interfaces.dao.ClienteDAO;
import interfaces.dao.DaoFactory;

public class SistemaVet {
	
	//=> Formato Global
	public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public static void main(String[] args) {

		//=> Declarações de variáveis
		final int MAX_CLIENTES = 100;
		String option = "*";
		int cont = 0;
		
		//=> Declarações de Funções
		Scanner in = new Scanner(System.in);
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement pst = null;
		
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
		
		Cliente[]  cli = new Cliente[MAX_CLIENTES];
		List<Cliente> cliList = new ArrayList();
		ClienteDAO clienteDAO = DaoFactory.createClienteDao(in);
		
		//=> Lista de IDs Registrados
		List<Integer> ids = new ArrayList<>();
		
		
		try {
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
						int sexoA, animalType, formaPg, op, cont3 = 0, qtdAnimais;
						
						//=> Preencher Dados do Cliente
						System.out.print("\n\nNome do Cliente: ");
						nome = in.nextLine();
						
						System.out.print("CPF do Cliente (Apenas números): ");
						cpf = in.nextLine();
						
						System.out.print("Telefone p/ Contato (Apenas números): ");
						telefone = in.nextLine();
						
						System.out.print("Qtd de Animais: ");
						qtdAnimais = in.nextInt();
						in.nextLine();
						
						//=> Criar Cliente
						cli[cont] = new Cliente(nome, cpf, telefone, qtdAnimais);
						
						for(int i = 0; i < qtdAnimais; i++) {
							//=> Preencher Dados do Animal
							System.out.print("\n\nNome do Animal #"+ (i+1) +": ");
							nomeA = in.nextLine();
							
							//=> Sexo do Animal
							System.out.println("\nSexo do Animal: ");
							System.out.println("0: MACHO");
							System.out.println("1: FÊMEA");
							System.out.println("2: HERMAFRODITA");;
							System.out.print("R: ");
							sexoA = in.nextInt();
							in.nextLine();
							
							//=> Tipo do Animal
							System.out.println("\nEspécie do Animal: ");
							System.out.println("0: GATO");
							System.out.println("1: CACHORRO");
							System.out.println("2: AVE");
							System.out.println("3: COELHO");
							System.out.println("3: TARTARUGA");
							System.out.println("3: COBRA");
							System.out.println("3: LAGARTO");
							System.out.println("7: OUTROS ANIMAIS SILVESTRES");
							System.out.print("R: ");
							animalType = in.nextInt();
							in.nextLine();
							
							cli[cont].animal[i] = new Animal(nomeA, animalType, sexoA);
							
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
							
							
							System.out.println("Orçamento do pet: " + cli[cont].animal[i].getOrcamento());
						}
						
						System.out.printf("\nOrçamento Total: %.2f \nDeseja Continuar? (S/N)\n", cli[cont].getOrcamento());
						System.out.print("R:");
						String resp = in.next();
						
						if(resp.equalsIgnoreCase("N")) {
							cli[cont].setSituacao(2);
							System.out.println("\n Atendimento cancelado pelo cliente.");
							break;
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
						clienteDAO.insert(cli[cont]);
						int id = cli[cont].getId();
						ids.add(id);
					
						cont++;
						break;
						
					//=> EXIBIR TODOS OS CLIENTES
					case "2":
						
						if(cont == 0) {
							throw new DomainException("Não há clientes cadastrados para mostrar.");
						}
						
						System.out.println("\n".repeat(50));
						System.out.println("======> Exibindo Todos os Clientes\n");
						
						for(int cont2 = 0; cont2 < cont; cont2++) {
							
							cli[cont2].printClienteSheet(serv, timeFormat);
		
						}
						
						System.out.println("\nPressione ENTER para continuar...");
						in.nextLine();
						break;
						
					case "3":
						
						System.out.println("Insira um Id para buscar o cliente: ");
						int idBusca = in.nextInt();
						in.nextLine();
						
						cli[cont] = clienteDAO.findById(idBusca);
						ids.add(cli[cont].getId());
						cont++;
						
						break;
						
					case "4":
						
						System.out.println("\nExibindo todos os clientes cadastrados...: ");
						cliList = clienteDAO.findAll();
						
						for(Cliente client : cliList) {
							System.out.printf("[%d] %s \n", client.getId(), client);
						}
						
						break;
					
					case "5":
						System.out.println("Informe um Id para excluir: ");
						int idExcluir = in.nextInt();
						in.nextLine();
						
						clienteDAO.deleteById(idExcluir);
						System.out.println("Cliente Excluído.");
						break;
						
					case "6":
						System.out.println("Qual Cliente deseja atualizar? ");
						
						cliList = clienteDAO.findAll();
						
						for(Cliente client : cliList) {
							System.out.printf("[%d] %s \n", client.getId(), client);
						}
						
						System.out.print("R: ");
						int buscaUpdate = in.nextInt();
						in.nextLine();
						
						for(Cliente client : cliList) {
							if(client.getId() != buscaUpdate) {
								continue;
							}
							
							System.out.println("Qual informação deseja Alterar?");
							client.listaEdit();
							
							boolean flag = true;
							
							for(int ii = 0; flag != false; ii++) {
								if(ii > 0) {
									System.out.print("Deseja Alterar outra informação? (S/N): ");
									String respInfo = in.nextLine();
									
									if(respInfo.equalsIgnoreCase("N")) {
										flag = false;
										continue;
									}
								}
									
								System.out.println("R: ");
								String replaceVar;
								int replaceVal;
								int replaceEditOp = in.nextInt();
								in.nextLine();
								
								switch(replaceEditOp) {
									case 0: 
										System.out.print("Digite um novo nome: ");
										replaceVar = in.nextLine();
										client.setNome(replaceVar);
										break;
									case 1: 
										System.out.print("Digite um novo cpf: ");
										replaceVar = in.nextLine();
										client.setCpf(replaceVar);
										break;
									case 2: 
										System.out.print("Digite um novo telefone: ");
										replaceVar = in.nextLine();
										client.setTelefone(replaceVar);
										break;
									case 3: 
										System.out.println("Alterar Forma de pagamento: ");
										System.out.println("0: DÉBITO");
										System.out.println("1: CRÉDITO");
										System.out.println("2: DINHEIRO");
										System.out.println("3: PIX");
										System.out.print("R: ");
										replaceVal = in.nextInt();
										in.nextLine();
										
										client.setFormaPagamento(replaceVal, in);
										break;
									case 4: 
										System.out.print("Insira quantas parcelas: ");
										replaceVal = in.nextInt();
										in.nextLine();
										
										client.parcelaPagamento = replaceVal;
										break;
									case 5: 
										System.out.println("Alterar Status do Pagamento: ");
										System.out.println("0: PENDENTE");
										System.out.println("1: PAGO");
										System.out.print("R: ");								
										replaceVal = in.nextInt();
										in.nextLine();
										
										client.setStatusPagamento(replaceVal);
										break;
									case 6: 
										System.out.println("Alterar Situação do cliente: ");
										System.out.println("0: TRABALHANDO");
										System.out.println("1: FINALIZADO");
										System.out.println("2: CANCELADO");
										System.out.println("3: EXCLUÍDO");
										System.out.print("R: ");
										replaceVal = in.nextInt();
										in.nextLine();
										
										client.setSituacao(replaceVal);
										break;
								}
							}
							
							clienteDAO.update(client);
							System.out.println("Dados do Atualizados.");
							
						}
						
						
					break;
						
					case "999":
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
				
				System.out.println("\n");
			}
			
		}
		catch(DomainException e) {
			System.out.println("\nErro: " + e.getMessage());
		}
		
		catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		catch(InputMismatchException e) {
			System.out.println("\nErro: Entrada de Dados inválida.");
		}
		
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("\nErro: Acesso de serviço inexistente.\n");
			e.printStackTrace();
		}
		
		catch(RuntimeException e) {
			System.out.println("\nErro Inesperado. \n");
			e.printStackTrace();
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
		
		//============================================>
		
		System.out.println("[3]: Ler cliente específico");
		System.out.println("[4]: Relatório Clientes");
		System.out.println("[5]: Apagar Cliente");
		System.out.println("[6]: Atualizar/Editar Cliente");
		System.out.println("[X]: Encerrar");
		
		System.out.print("Resposta: ");
	}
}


