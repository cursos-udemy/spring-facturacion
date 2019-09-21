package com.jendrix.udemy.facturacion.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {

	private static final long serialVersionUID = 7216179923467527965L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fecha;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_factura")
	private FacturaType tipoFactura;

	@NotEmpty
	@Column(name = "numero")
	private String numero;

	@NotEmpty
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "observaciones")
	private String observaciones;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	@XmlTransient
	private Cliente cliente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;

	public Factura() {
		super();
		this.tipoFactura = FacturaType.C;
		this.numero = UUID.randomUUID().toString();
		this.items = new ArrayList<>();
		this.fecha = new Date();
	}

	public Factura(Cliente cliente) {
		this();
		this.cliente = cliente;
	}

	@PrePersist
	public void prePersist() {
		this.fecha = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public FacturaType getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(FacturaType tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

//	public void setItems(List<ItemFactura> items) {
//		this.items = items;
//	}

	public void addItemFactura(ItemFactura item) {
		this.getItems().add(item);
	}

	public BigDecimal getImporteFactura() {
		BigDecimal importe = BigDecimal.ZERO;
		for (ItemFactura item : getItems()) {
			importe = importe.add(item.getImporteTotal());
		}
		return importe;
	}
}
