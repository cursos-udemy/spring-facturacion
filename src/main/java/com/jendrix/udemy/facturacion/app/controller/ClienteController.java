package com.jendrix.udemy.facturacion.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;
import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.jendrix.udemy.facturacion.app.model.service.ClienteService;
import com.jendrix.udemy.facturacion.app.model.service.FacturaService;
import com.jendrix.udemy.facturacion.app.model.service.UploadFileService;
import com.jendrix.udemy.facturacion.app.util.paginator.PageRender;

@Controller
@RequestMapping("/cliente")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private UploadFileService uploadFileService;

	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		Pageable pageable = PageRequest.of(page, 4);
		Page<Cliente> pageClientes = this.clienteService.findAll(pageable);
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/cliente/listar", pageClientes);
		model.addAttribute("clientes", pageClientes);
		model.addAttribute("page", pageRender);
		return "cliente/listar";
	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		Cliente cliente = this.clienteService.findById(id);
		if (cliente != null) {
			model.addAttribute("titulo", "Cliente " + id);
			model.addAttribute("cliente", cliente);

			Iterable<Factura> facturas = this.facturaService.findByCliente(cliente.getId());
			if (facturas != null) {
				model.addAttribute("facturas", facturas);
			}
			
			return "cliente/view";
		}

		flash.addFlashAttribute("error", "No se encontro el cliente");
		return "cliente/listar";
	}

	@GetMapping("/form")
	public String crear(Model model) {
		model.addAttribute("titulo", "Alta Cliente");
		model.addAttribute("cliente", new Cliente());
		return "cliente/form";
	}

	@PostMapping("/form")
	public String save(@Valid Cliente cliente, BindingResult result, Model model,
			@RequestParam(name = "form-cliente-foto") MultipartFile file, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Alta Cliente");
			return "cliente/form";
		}

		if (!file.isEmpty()) {

			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null) {
				this.uploadFileService.delete(cliente.getFoto());
			}

			try {
				String filename = this.uploadFileService.upload(file);
				cliente.setFoto(filename);
			} catch (IOException e) {
				flash.addFlashAttribute("error", "Error al subir la foto");
			}
		}

		this.clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", "El cliente se grabo con exito");
		return "redirect:/cliente/listar";
	}

	@GetMapping("/form/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = this.clienteService.findById(id);
			if (cliente != null) {
				model.addAttribute("titulo", "Cliente");
				model.addAttribute("cliente", cliente);
				return "cliente/form";
			} else {
				flash.addFlashAttribute("error", "No se encontro al cliente solicitado");
				return "redirect:/cliente/listar";
			}
		}
		flash.addFlashAttribute("error", "El ID del cliente es incorrecto");
		return "redirect:/cliente/listar";
	}

	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			Cliente cliente = this.clienteService.findById(id);
			String foto = cliente.getFoto();
			this.clienteService.delete(id);
			flash.addFlashAttribute("success", "El cliente eliminado con exito");
			this.uploadFileService.delete(foto);
		}
		return "redirect:/cliente/listar";
	}

	@GetMapping("/upload/{filename:.+}")
	public ResponseEntity<Resource> uploadFile(@PathVariable(value = "filename") String filename) {
		Resource resource = null;
		try {
			resource = this.uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			throw new RuntimeException("No se puede cargar el archivo " + filename);
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attatchment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
