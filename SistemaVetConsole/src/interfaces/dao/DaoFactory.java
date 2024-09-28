package interfaces.dao;

import java.util.Scanner;

import db.DB;
import interfaces.dao.impl.AnimalDaoJDBC;
import interfaces.dao.impl.ClienteDaoJDBC;

public class DaoFactory {
	
	//=> Cria as implementações DAO expondo somente a interface
	public static ClienteDAO createClienteDao(Scanner in) {
		return new ClienteDaoJDBC(DB.getConnection(), in);
	}
	
	public static AnimalDAO createAnimalDao() {
		return new AnimalDaoJDBC();
	}
}
