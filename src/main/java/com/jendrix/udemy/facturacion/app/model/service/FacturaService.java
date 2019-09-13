package com.jendrix.udemy.facturacion.app.model.service;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;

public interface FacturaService {

	public Iterable<Factura> findByCliente(Long clienteId);

	public Factura findById(Long id);

	public void save(Factura factura);

	public void delete(Long id);

}
