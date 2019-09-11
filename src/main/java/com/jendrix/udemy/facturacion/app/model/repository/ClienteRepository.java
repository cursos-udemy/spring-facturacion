package com.jendrix.udemy.facturacion.app.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
