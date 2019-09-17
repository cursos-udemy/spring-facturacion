package com.jendrix.udemy.facturacion.app.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.jendrix.udemy.facturacion.app.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);

}
