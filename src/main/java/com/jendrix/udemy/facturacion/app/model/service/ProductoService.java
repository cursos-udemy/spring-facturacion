package com.jendrix.udemy.facturacion.app.model.service;

import com.jendrix.udemy.facturacion.app.model.entity.Producto;

public interface ProductoService {

	public Producto findById(Long id);

	public Iterable<Producto> findByNombre(String nombre);

}
