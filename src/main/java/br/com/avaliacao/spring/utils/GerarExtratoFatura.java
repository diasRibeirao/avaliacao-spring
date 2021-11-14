package br.com.avaliacao.spring.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.avaliacao.spring.domain.Fatura;
import br.com.avaliacao.spring.domain.Transacao;

public class GerarExtratoFatura {

	public static ByteArrayInputStream gerar(Fatura fatura) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			
            Paragraph pTitulo = new Paragraph("EXTRATO CARTÃO DE CRÉDITO", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
            pTitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(pTitulo);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(fatura.getCartaoCredito().getNome()));
                        
            PdfPTable tCartaoInfo = new PdfPTable(4);
            tCartaoInfo.setWidthPercentage(100);
            tCartaoInfo.setWidths(new float[] { 4, 2, 2, 2 });
            tCartaoInfo.getDefaultCell().setBorder(0);
            
			tCartaoInfo.addCell(criarPdfPCell("Número do Cartão"));
			tCartaoInfo.addCell(criarPdfPCell("Limite"));
			tCartaoInfo.addCell(criarPdfPCell("Disponível"));
			tCartaoInfo.addCell(criarPdfPCell("Vencimento"));
			tCartaoInfo.setHeaderRows(1);
			
			tCartaoInfo.addCell(new Paragraph(fatura.getCartaoCredito().getNumero()));
			tCartaoInfo.addCell(new Paragraph("R$ " + fatura.getCartaoCredito().getLimiteFormatado()));
            tCartaoInfo.addCell(new Paragraph("R$ " + fatura.getCartaoCredito().getValorDisponivelFormatado()));
            tCartaoInfo.addCell(new Paragraph(fatura.getDataVencimentoFormatada()));
                        
            document.add(tCartaoInfo);
            
            document.add(new Paragraph(" "));
            
			PdfPTable tTransacoes = new PdfPTable(3);
			tTransacoes.setWidthPercentage(100);
			tTransacoes.setWidths(new float[] { 2, 6, 2 });
			PdfPCell cell1 = new PdfPCell(new Phrase("DATA"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tTransacoes.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("DESCRIÇÃO"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tTransacoes.addCell(cell1);

			cell1 = new PdfPCell(new Phrase("VALOR"));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tTransacoes.addCell(cell1);
			tTransacoes.setHeaderRows(1);
			
			if (fatura.getTransacoes().isEmpty()) {
				PdfPCell empty = new PdfPCell(new Phrase("Sem transações nesta fatura"));
				empty.setColspan(3);
				tTransacoes.addCell(empty);
			} else {			
				for (Transacao transacao : fatura.getTransacoes()) {
					tTransacoes.addCell(transacao.getDataFormatada());
					tTransacoes.addCell(transacao.getDescricao().length() > 39 ? transacao.getDescricao().substring(0, 40) : transacao.getDescricao());
					tTransacoes.addCell("R$ " + transacao.getValorFormatado());
				}
				
				PdfPCell total = new PdfPCell(new Phrase("TOTAL A PAGAR  "));
				total.setColspan(2);
				total.setHorizontalAlignment(Element.ALIGN_RIGHT);
				total.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tTransacoes.addCell(total);
				
				PdfPCell totalPagar = new PdfPCell(new Phrase("R$ " + fatura.getValorTotalFormatado()));
				totalPagar.setHorizontalAlignment(Element.ALIGN_CENTER);
				totalPagar.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tTransacoes.addCell(totalPagar);
			}

			document.add(tTransacoes);

			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static PdfPCell criarPdfPCell(String valor) {
		PdfPCell cell = new PdfPCell(new Phrase(valor));
		cell.setBorder(0);
		return cell;
	}
}
