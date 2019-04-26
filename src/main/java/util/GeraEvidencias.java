package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeraEvidencias {
	String caminho;
	Document document = new Document();
	
	public Table createTable(String projeto) throws BadElementException {
        int data = Calendar.DATE;
		Table table = new Table(2);
        
        table.addCell("Projeto:");
        table.addCell("Exec:");
        table.addCell(projeto);
        table.addCell(Integer.toString(data));
        return table;
	}
	
	/* Implementar depois
//	public Table criarCabecalho(Document document) throws DocumentException {
//		//PdfPTable table = new PdfPTable(new float[] { 10f, 5f, 3f });
//		Table table = new Table(150,150); 
//		
//		Cell cell1 = new Cell();   // Creating a cell 
//		cell1.add("Projeto");         // Adding content to the cell 
//		table.addCell("Alice");      // Adding cell to the table  
//		
//		//PdfPCell celulaNome = new PdfPCell(new Phrase("Projeto"));
//		//celulaNome.addElement(element);
//		
////		celulaNome.setHorizontalAlignment(Element.ALIGN_CENTER);
////		PdfPCell celulaDataNasc = new PdfPCell(new Phrase("Data Exec"));
////		celulaDataNasc.setHorizontalAlignment(Element.ALIGN_CENTER);
////		PdfPCell emBranco = new PdfPCell(new Phrase(""));
////		celulaDataNasc.setHorizontalAlignment(Element.ALIGN_CENTER);
////		table.addCell(celulaNome);
////		table.addCell(celulaDataNasc);
////		return table;
//		//document.add(table);
//		return table;
//	}	
	
//	public static void preencherDados(Document document, PdfPTable table, String Projeto) throws DocumentException {
//		
//			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		    Date date = new Date();
//		    String data;
//		    data = dateFormat.format(date);
//				table.addCell(Projeto);
//				table.addCell(data);
//				
//			document.add(table);
//	} */

	public Document iniciarEvidencia(String idTeste, String nomeTeste) throws DocumentException, MalformedURLException, IOException {
		Font font = new Font(10);
		
		caminho = "D:\\bruno\\Documents\\evidencias\\" + idTeste + "." + nomeTeste + ".jpg";
		PdfWriter.getInstance(document,
				new FileOutputStream("D:\\bruno\\Documents\\evidencias\\" + idTeste + "." + nomeTeste + ".pdf"));
		document.setMargins(60, 60, 35, 25);
		document.open();
		Image figura = Image.getInstance("D:\\bruno\\Documents\\evidencias\\westwing-original.png");
		figura.scaleAbsolute(500, 150);
		this.document.add(figura);
		document.add(new Paragraph(""));
		document.add(new Paragraph(""));
		Paragraph p = new Paragraph("Evidencia de caso de teste");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setFont(font);
		document.add(p);
		this.document.newPage();
		return document;
	}

	public void encerrarEvidencia(Document documento) {
		documento.close();
	}

	public void TakeScreenShot(String coments, Document document) throws AWTException, IOException, DocumentException, InterruptedException {
		try {
			Thread.sleep(5000);
			File outputfile = new File(caminho);
			Calendar data = Calendar.getInstance();
			int hora = data.get(Calendar.HOUR_OF_DAY);
			int seg = data.get(Calendar.SECOND);

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			java.awt.Dimension screenSize = toolkit.getScreenSize();
			Rectangle screenRect = new Rectangle(screenSize);

			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new // Captura a tela na àrea definida pelo retângulo
			Rectangle(screenRect)); // aqui vc configura as posições xy e o tam da área que quer capturar

			// Save the screenshot
			// ss.SaveAsFile((string.Format("{0}\\{1}", createdFolderLocation, testName +
			// ".Jpeg")), System.Drawing.Imaging.ImageFormat.Jpeg);
			ImageIO.write(bi, "jpg", outputfile);
			this.document.newPage();
			document.add(new Paragraph(coments));
			
			Image figura = Image.getInstance(caminho);
			figura.scaleAbsolute(500, 400);
			
			document.add(figura);
			

		} catch (AWTException e) {

		}
	}
}
