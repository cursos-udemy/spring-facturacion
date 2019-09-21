package com.jendrix.udemy.facturacion.app.view.json;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;

@Component("pages/cliente/listar.json")
public class ClienteListJsonView extends MappingJackson2JsonView {

	@Override
	@SuppressWarnings("unchecked")
	protected Object filterModel(Map<String, Object> model) {

		model.remove("titulo");
		model.remove("page");

		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		model.remove("clientes");

		model.put("clientes", clientes.getContent());

		return super.filterModel(model);
	}

}
