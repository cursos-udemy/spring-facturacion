package com.jendrix.udemy.facturacion.app.view.xlsx;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.jendrix.udemy.facturacion.app.model.entity.Factura;
import com.jendrix.udemy.facturacion.app.model.entity.ItemFactura;

@Component("pages/factura/view.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		MessageSourceAccessor msg = getMessageSourceAccessor();

		Factura factura = (Factura) model.get("factura");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.xlsx\"");
		Sheet sheet = workbook.createSheet(msg.getMessage("label.factura"));
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(msg.getMessage("page.factura.view.cliente.info"));

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(factura.getCliente().getEmail());

		sheet.createRow(4).createCell(0).setCellValue(msg.getMessage("page.factura.view.factura.info"));
		sheet.createRow(5).createCell(0).setCellValue(msg.getMessage("label.id") + ": " + factura.getId());
		sheet.createRow(6).createCell(0)
				.setCellValue(msg.getMessage("label.factura.descripcion") + ": " + factura.getDescripcion());
		sheet.createRow(7).createCell(0).setCellValue(msg.getMessage("label.factura.fecha") + ": " + factura.getFecha());

		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);

		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue(msg.getMessage("label.producto"));
		header.createCell(1).setCellValue(msg.getMessage("label.itemFactura.importeUnitario"));
		header.createCell(2).setCellValue(msg.getMessage("label.itemFactura.cantidad"));
		header.createCell(3).setCellValue(msg.getMessage("label.itemFactura.importeTotal"));

		header.getCell(0).setCellStyle(theaderStyle);
		header.getCell(1).setCellStyle(theaderStyle);
		header.getCell(2).setCellStyle(theaderStyle);
		header.getCell(3).setCellStyle(theaderStyle);

		int rownum = 10;

		for (ItemFactura item : factura.getItems()) {
			Row fila = sheet.createRow(rownum++);
			cell = fila.createCell(0);
			cell.setCellValue(item.getProducto().getNombre());
			cell.setCellStyle(tbodyStyle);

			cell = fila.createCell(1);
			cell.setCellValue(item.getImporteUnitario().doubleValue());
			cell.setCellStyle(tbodyStyle);

			cell = fila.createCell(2);
			cell.setCellValue(item.getCantidad());
			cell.setCellStyle(tbodyStyle);

			cell = fila.createCell(3);
			cell.setCellValue(item.getImporteTotal().doubleValue());
			cell.setCellStyle(tbodyStyle);
		}

		Row filatotal = sheet.createRow(rownum);
		cell = filatotal.createCell(2);
		cell.setCellValue(msg.getMessage("label.factura.importeTotal") + ": ");
		cell.setCellStyle(tbodyStyle);

		cell = filatotal.createCell(3);
		cell.setCellValue(factura.getImporteFactura().doubleValue());
		cell.setCellStyle(tbodyStyle);

	}

}
