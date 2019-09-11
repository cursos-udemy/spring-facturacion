package com.jendrix.udemy.facturacion.app.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;

public interface ClienteService {

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public Cliente findById(Long id);

	public void save(Cliente cliente);

	public void delete(Long id);
}
