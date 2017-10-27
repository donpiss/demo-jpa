package example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import example.demo.entity.User;


public interface UserRepo extends CrudRepository<User, Long>{

	public User findByEmailAndPassword(String email, String password);
	
	public User findByEmail(String email);
}
