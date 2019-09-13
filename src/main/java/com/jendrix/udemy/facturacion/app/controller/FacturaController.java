package com.jendrix.udemy.facturacion.app.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jendrix.udemy.facturacion.app.model.entity.Cliente;
import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.jendrix.udemy.facturacion.app.model.entity.ItemFactura;
import com.jendrix.udemy.facturacion.app.model.entity.Producto;
import com.jendrix.udemy.facturacion.app.model.service.ClienteService;
import com.jendrix.udemy.facturacion.app.model.service.FacturaService;
import com.jendrix.udemy.facturacion.app.model.service.ProductoService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ProductoService productoService;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/view/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Factura factura = this.facturaService.findById(id);
		if (factura == null) {
			flash.addFlashAttribute("error", "La factura no existe!");
			return "redirect:/cliente/listar";
		}

		model.addAttribute("titulo", "Factura: ".concat(factura.getDescripcion()));
		model.addAttribute("factura", factura);

		return "pages/factura/view";
	}

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Model model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findById(clienteId);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		model.addAttribute("titulo", "Crear Factura");
		model.addAttribute("factura", new Factura(cliente));

		return "pages/factura/form";
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody Iterable<Producto> cargarProductos(@PathVariable String term) {
		return this.productoService.findByNombre(term);
	}

	@PostMapping("/form")
	public String save(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "pages/factura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "La factura NO tiene incluye productos");
			return "pages/factura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Producto producto = productoService.findById(itemId[i]);

			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setImporteUnitario(producto.getImporte());
			linea.setProducto(producto);
			factura.addItemFactura(linea);

			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}

		this.facturaService.save(factura);
		status.setComplete();

		flash.addFlashAttribute("success", "Factura creada con éxito!");

		return "redirect:/cliente/view/" + factura.getCliente().getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Factura factura = facturaService.findById(id);
		if (factura != null) {
			facturaService.delete(id);
			flash.addFlashAttribute("success", "Factura eliminada!");
			return "redirect:/cliente/view/" + factura.getCliente().getId();
		}
		flash.addFlashAttribute("error", "La factura no existe!");

		return "redirect:/cliente/listar";
	}

}
