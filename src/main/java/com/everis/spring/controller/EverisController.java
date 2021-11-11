package com.everis.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.spring.repository.EverisCustomer;
import com.everis.spring.repository.EverisCustomerRepository;
import com.everis.spring.services.EverisCustomerManagementServiceI;

@Controller
public class EverisController {
	
	public static String INDEX_VIEW = "index";
	
	public static String SHOW_CLIENTS_VIEW = "showClients";
	
	public static String FORM = "form";
	
	@Autowired
	private EverisCustomerManagementServiceI customerService;
	
	@Autowired
	private EverisCustomerRepository customerRepository;
	
	@GetMapping(value ="/index")
	public String index() {
		return INDEX_VIEW;
	}
	
	@GetMapping(value ="/showClients")
	public String showClients(Model model) {
		List<EverisCustomer> list = customerRepository.findAll();
		model.addAttribute("everisCustomers", list);
		return SHOW_CLIENTS_VIEW;
		
	}
	
	@GetMapping(value ="/addClient")
	public String addClient(Model model) {
		model.addAttribute("everisCustomer", new EverisCustomer());
		return FORM;
	}
	
	@PostMapping(value = "/save")
	public String saveClient(@ModelAttribute("everisCustomer") EverisCustomer client, Model model) {
	    customerRepository.save(client);
	    return "redirect:/showClients";
	}
	
	@GetMapping(value ="/search")
	public String searchClientByName(@RequestParam("name") String name, Model model) {
		List<EverisCustomer> everisCustomer = customerRepository.findByName(name);
		model.addAttribute("everisCustomers", everisCustomer);
		return SHOW_CLIENTS_VIEW;
	}
	
	@PostMapping(value ="/delete/{id}")
	public String deleteClient(@RequestParam("id") Long id) {
		customerRepository.deleteById(id);
		return "redirect:/showClients";
	}

}
