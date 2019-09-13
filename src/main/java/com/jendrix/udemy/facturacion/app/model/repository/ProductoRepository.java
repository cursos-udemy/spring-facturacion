package com.jendrix.udemy.facturacion.app.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jendrix.udemy.facturacion.app.model.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

	@Query("select p from Producto p where p.nombre like %?1%")
	public Iterable<Producto> findByNombre(String nombre);

	public Iterable<Producto> findByNombreLikeIgnoreCase(String nombre);
}
