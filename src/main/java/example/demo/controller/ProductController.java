package example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.demo.dto.Result;
import example.demo.dto.SearchForm;
import example.demo.dto.SearchHargaForm;
import example.demo.entity.Category;
import example.demo.entity.Product;
import example.demo.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepo repo;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody Product product, Errors errors) throws Exception {
		Result result = new Result();

		if(errors.hasErrors()) {
			for(ObjectError err: errors.getAllErrors()) {
				result.getMessages().add(err.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(result);
		}
		Product pro = repo.save(product);
		result.setPayload(pro);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public List<Product> findByName(@RequestBody SearchForm form) {
		return repo.findByNameIgnoringCase(form.getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/byname")
	public List<Product> findByNameLike(@RequestBody SearchForm form) {
		return repo.findByName(form.getName());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/byprice")
	public List<Product> findByPriceRange(@RequestBody SearchHargaForm form) {
		return repo.findByPriceRange(form.getMin(), form.getMax());
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/bycategory")
	public List<Product> findByCategory(@RequestBody Category category) {
		return repo.findByCategory(category);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> findAll() {
		return repo.findAll();
	}

}
