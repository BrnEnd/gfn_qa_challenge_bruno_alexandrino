package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class GeraEvidencias {
	String caminho;
	Document document = new Document();

	public Document iniciarEvidencia(String idTeste, String nomeTeste)
			throws DocumentException, MalformedURLException, IOException {
		Path userDirPath = Paths.get(System.getProperty("user.dir"));
		Path evidenciasPath = userDirPath.resolve("./evidencias");
		String fileName = idTeste + "." + nomeTeste;
		Font font = new Font(10);
		File diretorio = new File(evidenciasPath.toString());
		System.out.println(diretorio.mkdir());
		caminho = evidenciasPath.resolve(fileName + ".jpg").toString();
		PdfWriter.getInstance(document, new FileOutputStream(evidenciasPath.resolve(fileName + ".pdf").toString()));
		document.setMargins(60, 60, 35, 25);
		document.open();
		Paragraph p = new Paragraph("Evidencia de caso de teste");
		p.setAlignment(Paragraph.ALIGN_CENTER);
		p.setFont(font);
		document.add(p);
		this.document.newPage();
		return document;
	}

	public void TakeScreenShot(String coments, Document document)
			throws AWTException, IOException, DocumentException, InterruptedException {

		Thread.sleep(5000);
		File outputfile = new File(caminho);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);

		Robot robot = new Robot();
		BufferedImage bi = robot.createScreenCapture(new Rectangle(screenRect));

		ImageIO.write(bi, "jpg", outputfile);
		this.document.newPage();
		document.add(new Paragraph(coments));

		Image figura = Image.getInstance(caminho);
		figura.scaleAbsolute(500, 400);

		document.add(figura);

	}

	public void encerrarEvidencia(Document documento) {
		documento.close();
	}

}
