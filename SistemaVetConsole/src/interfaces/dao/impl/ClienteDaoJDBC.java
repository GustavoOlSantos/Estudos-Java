package interfaces.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import db.DB;
import db.DbException;
import entities.Animal;
import entities.Cliente;
import enums.FormaPagamento;
import enums.Situacao;
import enums.StatusPagamento;
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
	public void insert(Cliente cli) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		int id = 0;
		
		try {
			
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
				}
			}
			
			for(int i = 0; i < cli.qtdAnimal; i++) {
				st = conn.prepareStatement(
						"INSERT INTO animal (idCliente, nome, sexo, tipo, orcamento) "
						+ "VALUES (?, ? ,?, ?, ?)");
				
				st.setInt	(1, id);							//=> IdCliente
				st.setString(2, cli.animal[i].getNome());		//=> Nome Pet
				st.setInt	(3, cli.animal[i].getIntSexo());	//=> Sexo Pet
				st.setInt	(4, cli.animal[i].getIntTipo());	//=> TipoPet
				st.setDouble(5, cli.animal[i].getOrcamento());	//=> Orçamento pet
				
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
	public void update(Cliente cli) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente findById(Integer id) throws DomainException {
		
		PreparedStatement st = null;
		ResultSet rs = null; 
		
		try {
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
		
		return animal;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
