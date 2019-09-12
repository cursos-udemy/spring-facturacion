package com.jendrix.udemy.facturacion.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;

public interface FacturaRepository extends PagingAndSortingRepository<Factura, Long> {

	@Query("select f from Factura f where f.cliente.id = ?1")
	public Iterable<Factura> findByCliente(Long clienteId);

}
