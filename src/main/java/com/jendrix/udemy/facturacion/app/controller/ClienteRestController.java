package com.jendrix.udemy.facturacion.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;
import com.jendrix.udemy.facturacion.app.model.service.ClienteService;

@RestController
@RequestMapping("/cliente/api/v1")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public List<Cliente> listar() {
		return this.clienteService.findAll();
	}
}
