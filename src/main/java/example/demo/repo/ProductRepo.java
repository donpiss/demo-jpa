package example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import example.demo.entity.Category;
import example.demo.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Long>{

	public List<Product> findByNameIgnoringCase(String name);
	
	public List<Product> findByCategory(Category category);
	
	public List<Product> findAll();
	
	@Query("select pro from Product pro where pro.name like %:paramName%")
	public List<Product> findByName(@Param("paramName") String paramName);
	
	@Query("select pro from Product pro where pro.harga between :min and :max")
	public List<Product> findByPriceRange(@Param("min") double min, @Param("max") double max);
}
