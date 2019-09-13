package com.jendrix.udemy.facturacion.app.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jendrix.udemy.facturacion.app.model.entity.Producto;
import com.jendrix.udemy.facturacion.app.model.repository.ProductoRepository;
import com.jendrix.udemy.facturacion.app.model.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Producto> findByNombre(String nombre) {
		return this.productoRepository.findByNombreLikeIgnoreCase("%" + nombre + "%");
		// return this.productoRepository.findByNombre(nombre);
	}

	@Override
	public Producto findById(Long id) {
		return this.productoRepository.findById(id).orElse(null);
	}

}
