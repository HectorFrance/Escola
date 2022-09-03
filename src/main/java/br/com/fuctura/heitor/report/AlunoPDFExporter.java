package br.com.fuctura.heitor.report;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.com.fuctura.heitor.model.Aluno;

public class AlunoPDFExporter {
	private List<Aluno> listaAlunos;

	public AlunoPDFExporter(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nome", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Cpf", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Fone", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Tipo", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		int i = 1;
		Color c = null;
		for (Aluno aluno : listaAlunos) {
			if (i % 2 == 0) {
				c = Color.WHITE;
			} else {
				c = Color.LIGHT_GRAY;
			}

			PdfPCell cell = new PdfPCell();
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(5);
			cell.setBackgroundColor(c);

			cell.setPhrase(new Phrase(String.valueOf(aluno.getId()), font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(aluno.getNome(), font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(aluno.getCpf(), font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(aluno.getEmail(), font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(aluno.getFone(), font));
			table.addCell(cell);

			cell.setPhrase(new Phrase(aluno.getTipo(), font));
			table.addCell(cell);

			i++;
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Lista de Alunos", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}
