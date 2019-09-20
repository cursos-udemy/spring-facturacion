package com.jendrix.udemy.facturacion.app.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("pages/factura/view.pdf")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Factura factura = (Factura) model.get("factura");

		PdfPTable tableCliente = new PdfPTable(1);
		tableCliente.setSpacingAfter(20);

		PdfPCell cell = new PdfPCell(new Phrase(getMessage("page.factura.view.cliente.info")));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		tableCliente.addCell(cell);
		tableCliente.addCell(factura.getCliente().getNombreCompleto());
		tableCliente.addCell(factura.getCliente().getEmail());

		PdfPTable tableFacturaCabecera = new PdfPTable(1);
		tableFacturaCabecera.setSpacingAfter(20);
		cell = new PdfPCell(new Phrase(getMessage("page.factura.view.factura.info")));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		tableFacturaCabecera.addCell(cell);
		tableFacturaCabecera.addCell(getMessage("label.id") + ": " + factura.getId());
		tableFacturaCabecera.addCell(getMessage("label.factura.fecha") + ": " + factura.getFecha());
		tableFacturaCabecera.addCell(getMessage("label.factura.descripcion") + ": " + factura.getDescripcion());

		PdfPTable tableDetalleFactura = new PdfPTable(4);
		tableDetalleFactura.setWidths(new float[] { 3.2f, 1, 1, 1 });
		tableDetalleFactura.setSpacingAfter(20);
		cell = new PdfPCell(new Phrase(getMessage("page.factura.view.itemFactura.info")));
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		cell.setColspan(4);
		tableDetalleFactura.addCell(cell);

		tableDetalleFactura.addCell(getMessage("label.producto"));
		tableDetalleFactura.addCell(getMessage("label.itemFactura.importeUnitario"));
		tableDetalleFactura.addCell(getMessage("label.itemFactura.cantidad"));
		tableDetalleFactura.addCell(getMessage("label.itemFactura.importeTotal"));
		factura.getItems().forEach(item -> {
			tableDetalleFactura.addCell(item.getProducto().getNombre());
			tableDetalleFactura.addCell(item.getImporteUnitario().toString());
			tableDetalleFactura.addCell(item.getCantidad().toString());
			tableDetalleFactura.addCell(item.getImporteTotal().toString());
		});
		PdfPCell cellTotalFactura = new PdfPCell(new Phrase(getMessage("label.factura.importeTotal")));
		cellTotalFactura.setColspan(3);
		cellTotalFactura.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		tableDetalleFactura.addCell(cellTotalFactura);
		tableDetalleFactura.addCell(factura.getImporteFactura().toString());

		document.add(tableCliente);
		document.add(tableFacturaCabecera);
		document.add(tableDetalleFactura);
		
		response.setContentType("application/pdf");      
		response.setHeader("Content-Disposition", "attachment; filename=\"algun-nombre.pdf\""); 
		response.setContentType("application/pdf");   
		//response.setContentType("application/force-download");
	}

	public String getMessage(String code) {
		return this.messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
	}
}
