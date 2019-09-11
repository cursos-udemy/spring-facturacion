package com.jendrix.udemy.facturacion.app.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;
import com.jendrix.udemy.facturacion.app.model.repository.ClienteRepository;
import com.jendrix.udemy.facturacion.app.model.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) this.clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return this.clienteRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return this.clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		this.clienteRepository.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.clienteRepository.deleteById(id);
	}

}
