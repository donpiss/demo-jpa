package example.demo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import example.demo.entity.Category;
import example.demo.repo.CategoryRepo;
import example.demo.service.Matematika;
import example.demo.service.UserService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemojpaApplication.class, 
webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemojpaApplicationTests {

	@Autowired
	private Matematika mtk;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private CategoryRepo cRepo;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	int port;
	
	@Test
	public void testGetCategory() {
		ResponseEntity<ArrayList> entity = this.testRestTemplate
				.getForEntity("http://localhost:"+this.port+"/category", ArrayList.class);
		
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	
	@Test
	public void contextLoads() {
		
		Assert.assertEquals(20, mtk.add(10, 10));
	}
	
	@Test
	public void categoryTest() {
		Category category = new Category();
		category.setName("sample");
		
		Category result = cRepo.save(category);
		Assert.assertNotNull(result);
		
		result.setName("Sample Update");
		result = cRepo.save(result);
		Assert.assertEquals("Sample Update", result.getName());
		
		cRepo.delete(result);
		Assert.assertEquals(null, cRepo.findOne(result.getId()));
	}
	
	@Test
	public void loginTest() throws Exception {
		String email = "donny@pissu.com";
		String password = "e10adc3949ba59abbe56e057f20f883e";
		Assert.assertNotNull(uService.login(email, password));
	}

}
