package com.jendrix.udemy.facturacion.app.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.jendrix.udemy.facturacion.app.model.repository.FacturaRepository;
import com.jendrix.udemy.facturacion.app.model.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Factura> findByCliente(Long clienteId) {
		return this.facturaRepository.findByCliente(clienteId);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findById(Long id) {
		//return this.facturaRepository.findById(id).orElse(null);
		return this.facturaRepository.fetchById(id);
	}

	@Override
	@Transactional
	public void save(Factura factura) {
		this.facturaRepository.save(factura);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.facturaRepository.deleteById(id);
	}
}
