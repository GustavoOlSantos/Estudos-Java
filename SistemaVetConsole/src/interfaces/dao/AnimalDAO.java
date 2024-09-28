package interfaces.dao;

import java.util.List;

import entities.Animal;

public interface AnimalDAO {
	void insert(Animal cli);
	void update(Animal cli);
	void deleteById(Integer id);
	Animal findById(Integer id);
	List<Animal> findAll();
}
