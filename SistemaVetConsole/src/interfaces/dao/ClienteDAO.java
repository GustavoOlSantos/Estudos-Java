package interfaces.dao;

import java.util.List;
import entities.Cliente;
import exceptions.DomainException;

public interface ClienteDAO {
	
	void insert(Cliente cli);
	void update(Cliente cli);
	void deleteById(Integer id);
	Cliente findById(Integer id) throws DomainException;
	List<Cliente> findAll();
}
