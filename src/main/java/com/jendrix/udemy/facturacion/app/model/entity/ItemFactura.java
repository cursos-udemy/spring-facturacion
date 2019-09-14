package com.jendrix.udemy.facturacion.app.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "item_factura")
public class ItemFactura implements Serializable {

	private static final long serialVersionUID = 6546248880970670139L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Positive
	@Column(name = "cantidad")
	private Integer cantidad;

	@NotNull
	@Positive
	@Column(name = "importe_unitario")
	private BigDecimal importeUnitario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		if (cantidad == null) {
			cantidad = 0;
		}
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getImporteUnitario() {
		if (importeUnitario == null) {
			importeUnitario = BigDecimal.ZERO;
		}
		return importeUnitario;
	}

	public void setImporteUnitario(BigDecimal importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	public BigDecimal getImporteTotal() {
		return getImporteUnitario().multiply(new BigDecimal(getCantidad()));
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
