package com.sysbye.softIsdel.view.pdf;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;


import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;


@Component("importes/detallesImportesInscripcion")
public class ImporteInscripcionPdfView extends AbstractPdfView {
	
	

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ImporteAbonadoInscripcion importe = (ImporteAbonadoInscripcion) model.get("importe");
		
		

		PdfPTable tabla = new PdfPTable(1);
		tabla.setSpacingAfter(20);


		
		PdfPCell cell = null;

		cell = new PdfPCell(new Phrase("Comprobante Importe Inscripción"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		tabla.addCell(cell);

		tabla.addCell("Nro Alumno: " + importe.getFkIdInscripcion().getFkIdAlumno().getNroAlumno());
		tabla.addCell("Apellido: " + importe.getFkIdInscripcion().getFkIdAlumno().getApellido());
		tabla.addCell("Nombre: " + importe.getFkIdInscripcion().getFkIdAlumno().getNombre());
		tabla.addCell("Documento: " + importe.getFkIdInscripcion().getFkIdAlumno().getDni());

		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);
		
		
		cell = new PdfPCell(new Phrase("Datos del Curso"));
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		tabla2.addCell(cell);
		
		tabla2.addCell("Curso: " + importe.getFkIdInscripcion().getFkIdCurso().getNombre());
		tabla2.addCell("Categoria Curso: " + importe.getFkIdInscripcion().getFkIdCurso().getFkIdCategoriaCurso().getCategoria());
		tabla2.addCell("Fecha de Inicio Clases: " + importe.getFkIdInscripcion().getFechaInicio1().toString());
		if(!(importe.getFkIdInscripcion().getFechaFin1() == null)) {
			tabla2.addCell("Fecha de Fin Clases: " + importe.getFkIdInscripcion().getFechaFin1().toString());
		}
		tabla2.addCell("Horario Clases: " + importe.getFkIdInscripcion().getHorario1().toString());
		tabla2.addCell("Dias de Cursada de Clases: " + importe.getFkIdInscripcion().getDiasDeCursada1().toString());
		

		PdfPTable tabla3 = new PdfPTable(1);
		tabla2.setSpacingAfter(20);
		
		cell = new PdfPCell(new Phrase("Datos Importe Inscripción"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		tabla3.addCell(cell);
		
		tabla3.addCell("Descripción: " + importe.getObservacion());
		tabla3.addCell("Fecha: " + importe.getFecha().toString());
		tabla3.addCell("Total: $" + importe.getImporteAbonado());
		
		
		
		document.add(tabla);
		document.add(tabla2);
		document.add(tabla3);
		
	}

}
