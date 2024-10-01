package interfaces.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import db.DB;
import db.DbException;
import entities.Animal;
import entities.Cliente;
import exceptions.DomainException;
import interfaces.dao.ClienteDAO;

public class ClienteDaoJDBC implements ClienteDAO {
	
	private Connection conn;
	private Scanner in;
	
	public ClienteDaoJDBC(Connection conn, Scanner in) {
		this.conn = conn;
		this.in = in;
	}
	
	@Override
	public void insert(Cliente cli) throws IOException {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		int id = 0;
		
		try {
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement(
					"INSERT INTO cliente (nome, cpfCnpj, telefone, qtdAnimal, orcamentoTotal,"
					+ " formaPagamento, parcelas, statusPagamento, situacao, dataCadastro)"
					+ "VALUES (?, ? ,?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, cli.getNome()); 			//=> Nome
			st.setString(2, cli.getCpf()); 				//=> Cpf/Cnpj
			st.setString(3, cli.getTelefone()); 		//=> Telefone
			st.setInt(4, cli.qtdAnimal); 	   			//=> QtdAnimal
			st.setDouble(5, cli.getOrcamento());	   	//=> OrçamentoTotal
			st.setInt(6, cli.getIntFormaPagamento());	//=> Forma de Pagamento
			st.setInt(7, cli.parcelaPagamento);			//=> Parcelas
			st.setInt(8, cli.getIntStatusPagamento());	//=> Status do Pagamento
			st.setInt(9, cli.getIntSituacao());			//=> Situação do Trabalho
			st.setTimestamp(10, Timestamp.valueOf(cli.getDataCadastro())); //=> Data de Cadastro
			
			int RowsAffected = st.executeUpdate();
			
			if(RowsAffected > 0) {
				rs = st.getGeneratedKeys();
				
				while(rs.next()) {
					id = rs.getInt(1);
					cli.setId(id);
				}
			}
			
			else {
				conn.rollback();
				throw new DbException("Erro Inesperado. Nenhuma linha adicionada.");
			}
			
			for(int i = 0; i < cli.qtdAnimal; i++) {
				st = conn.prepareStatement(
						"INSERT INTO animal (idCliente, nome, sexo, tipo, orcamento, servicos ) "
						+ "VALUES (?, ? ,?, ?, ?, ?)");
				
				st.setInt	(1, id);							//=> IdCliente
				st.setString(2, cli.animal[i].getNome());		//=> Nome Pet
				st.setInt	(3, cli.animal[i].getIntSexo());	//=> Sexo Pet
				st.setInt	(4, cli.animal[i].getIntTipo());	//=> TipoPet
				st.setDouble(5, cli.animal[i].getOrcamento());	//=> Orçamento pet
				
				String intString = Arrays.toString(cli.animal[i].getServicos());
				st.setString(6, intString);
				
				st.executeUpdate();
				conn.commit();
			}
		}
		catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
				System.out.println("Erro: Não foi possível realizar o RollBack.\n Stacktrace: " + e1.getMessage());
			}
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void update(Cliente cli) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			
			conn.setAutoCommit(true);
			st = conn.prepareStatement(
					"UPDATE cliente SET nome = ?, cpfCnpj = ?, telefone = ?, qtdAnimal = ?, orcamentoTotal = ?,"
					+ " formaPagamento = ?, parcelas = ?, statusPagamento = ?, situacao = ?, dataCadastro = ?"
					+ "WHERE id = ?");
			
			st.setString(1, cli.getNome()); 			//=> Nome
			st.setString(2, cli.getCpf()); 				//=> Cpf/Cnpj
			st.setString(3, cli.getTelefone()); 		//=> Telefone
			st.setInt(4, cli.qtdAnimal); 	   			//=> QtdAnimal
			st.setDouble(5, cli.getOrcamento());	   	//=> OrçamentoTotal
			st.setInt(6, cli.getIntFormaPagamento());	//=> Forma de Pagamento
			st.setInt(7, cli.parcelaPagamento);			//=> Parcelas
			st.setInt(8, cli.getIntStatusPagamento());	//=> Status do Pagamento
			st.setInt(9, cli.getIntSituacao());			//=> Situação do Trabalho
			st.setTimestamp(10, Timestamp.valueOf(cli.getDataCadastro())); //=> Data de Cadastro
			st.setInt(11, cli.getId()); 				//=> Id do Cliente
			
			st.executeUpdate();
		
			
			for(int i = 0; i < cli.qtdAnimal; i++) {
				st = conn.prepareStatement(
						"UPDATE animal SET idCliente = ?, nome = ?, sexo = ?, tipo = ?, orcamento = ? "
						+ "WHERE idCliente = ?");
				
				st.setInt	(1, cli.getId());					//=> IdCliente
				st.setString(2, cli.animal[i].getNome());		//=> Nome Pet
				st.setInt	(3, cli.animal[i].getIntSexo());	//=> Sexo Pet
				st.setInt	(4, cli.animal[i].getIntTipo());	//=> TipoPet
				st.setDouble(5, cli.animal[i].getOrcamento());	//=> Orçamento pet
				st.setInt	(6, cli.getId());					//=> IdCliente
				
				st.executeUpdate();
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn.setAutoCommit(true);
			st = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
			
			st = conn.prepareStatement("DELETE FROM animal WHERE idCliente = ?");
			st.setInt(1, id);
			st.executeUpdate();
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public Cliente findById(Integer id) throws DomainException {
		
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			conn.setAutoCommit(true);
			st = conn.prepareStatement(
					"SELECT cliente.*, animal.* "
					+ "FROM cliente INNER JOIN animal "
					+ "ON cliente.id = animal.idCliente "
					+ "WHERE cliente.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Cliente cliente = instCliente(rs);		
				
				for(int i = 0; i < cliente.qtdAnimal; i++) {
					cliente.animal[i] = instAnimal(rs, cliente, i);
					
					if(!rs.next()) {
						break;
					}
				}
				
				return cliente;
				
			}
			
			return null;
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Cliente> findAll() throws DomainException {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
			st = conn.prepareStatement(
					"SELECT cliente.*, animal.* "
					+ "FROM cliente INNER JOIN animal "
					+ "ON cliente.id = animal.idCliente "
					+ "ORDER BY dataCadastro");
			
			rs = st.executeQuery();
			
			List<Cliente> listaClientes = new ArrayList<Cliente>();
			int rows = 0;
			
			while(rs.next()) {
				
				Cliente cliente = instCliente(rs);	
				rows++;
				
				for(int i = 0; i < cliente.qtdAnimal; i++) {
					cliente.animal[i] = instAnimal(rs, cliente, i);
					
					if(i == cliente.qtdAnimal - 1) {
						continue;
					}
					
					if(!rs.next()) {
						break;
					}
					

				}
				
				listaClientes.add(cliente);
			}
			
			System.out.println("Clientes Encontrados: " + rows + "\n");
			return listaClientes;
					
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Cliente instCliente(ResultSet rs) throws SQLException, DomainException {
		Cliente cliente = new Cliente();
		
		cliente.setId(rs.getInt("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getString("telefone"));
		cliente.setCpf(rs.getString("cpfCnpj"));
		cliente.setOrcamento(rs.getDouble("orcamentoTotal"));
		cliente.setFormaPagamento(rs.getInt("formaPagamento"), in);
		cliente.setStatusPagamento(rs.getInt("statusPagamento"));
		cliente.setSituacao(rs.getInt("situacao"));
		cliente.setDataCadastro(rs.getObject("dataCadastro", LocalDateTime.class));
		
		cliente.qtdAnimal = rs.getInt("qtdAnimal");
		cliente.animal = new Animal[cliente.qtdAnimal];
		
		return cliente;
	}
	
	private Animal instAnimal(ResultSet rs, Cliente cliente, int i) throws SQLException, DomainException {
		Animal animal = new Animal();
		animal.setNome(rs.getString("animal.nome"));
		animal.setSexo(rs.getInt("animal.sexo"));
		animal.setTipo(rs.getInt("animal.tipo"));
		animal.setOrcamento(rs.getDouble("animal.orcamento"));
		String intStr = rs.getString("animal.servicos");
		
		intStr = intStr.replaceAll(" ", "");
		intStr = intStr.substring(1, intStr.length() - 1);

		String[] parts = intStr.split(",");
		int[] arr = new int[parts.length];
		
		int j = 0;
		for (String part : parts) {
			
		    part = part.trim();
		    
		    if (part.startsWith("-")) {
		        arr[j] = -1;
		        j++;
		        continue;
		    }
		    
		    arr[j] = Integer.parseInt(part);
		    j++;
		}
		
		animal.servicos = arr;
		
		return animal;
	}
}
