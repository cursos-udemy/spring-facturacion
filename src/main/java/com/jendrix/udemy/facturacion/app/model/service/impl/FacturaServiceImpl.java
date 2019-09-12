package com.jendrix.udemy.facturacion.app.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.jendrix.udemy.facturacion.app.model.repository.FacturaRepository;
import com.jendrix.udemy.facturacion.app.model.service.FacturaService;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Override
	public Iterable<Factura> findByCliente(Long clienteId) {
		return this.facturaRepository.findByCliente(clienteId);
	}

}
