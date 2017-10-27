package example.demo.service;

import org.springframework.stereotype.Service;

@Service("matematika")
public class Matematika {

	public int add(int a, int b) {
		return a+b;
	}
}
