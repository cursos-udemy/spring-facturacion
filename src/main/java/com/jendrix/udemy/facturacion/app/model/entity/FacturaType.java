package com.jendrix.udemy.facturacion.app.model.entity;

public enum FacturaType {

	A("Factura A"), B("Factura B"), C("Factura C"), X("Factura X");

	public final String label;

	private FacturaType(String label) {
		this.label = label;
	}
}
