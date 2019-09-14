package com.jendrix.udemy.facturacion.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;

public interface FacturaRepository extends PagingAndSortingRepository<Factura, Long> {

	@Query("select distinct f from Factura f join fetch f.cliente c join fetch f.items i where f.cliente.id = ?1")
	public Iterable<Factura> findByCliente(Long clienteId);
	
	@Query("select f from Factura f join fetch f.items i join fetch f.cliente c join fetch i.producto where f.id = ?1")
	public Factura fetchById(Long id);

}
