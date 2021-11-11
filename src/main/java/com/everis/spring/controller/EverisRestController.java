package com.everis.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.spring.repository.EverisCustomer;
import com.everis.spring.repository.EverisCustomerRepository;
import com.everis.spring.services.EverisCustomerManagementServiceI;

@RestController
@RequestMapping("/customers")
public class EverisRestController {
	
	@Autowired
	private EverisCustomerManagementServiceI customerService;
	
	@Autowired
	private EverisCustomerRepository customerRepository;
	
	@GetMapping("/all")
	public List<EverisCustomer> all() {
		return customerRepository.findAll();
	}
	
	@PostMapping()
	public void newCustomer(@RequestBody EverisCustomer newCustomer) {
	    customerRepository.save(newCustomer);
	  }
	
	@GetMapping("/{nombre}")
	public List<EverisCustomer> getCustomerByNombre(final @PathVariable String nombre) {
		List<EverisCustomer> everisCustomer = customerRepository.findByName(nombre);
		return everisCustomer;
	}
	
	@GetMapping("/delete/{id}")
	public void getCustomerByNombre(final @PathVariable Long id) {
		customerRepository.deleteById(id);

	}

}
