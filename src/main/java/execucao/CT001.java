package execucao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

import telas.Login;

import org.junit.Assert;
import util.GeraEvidencias;

public class CT001 {
	public static WebDriver driver;
	GeraEvidencias evidencia = new GeraEvidencias();
	Login login = new Login();
	Document document;

	@Before
	public void setUp() throws DocumentException, MalformedURLException, IOException {
		Path userDirPath = Paths.get(System.getProperty("user.dir"));
		Path webdriverPath = userDirPath.resolve("src/main/java/util/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", webdriverPath.toString());
		driver = new ChromeDriver();
		document = evidencia.iniciarEvidencia("001", "Validar acesso ao sistema");
	}

	@Test
	public void executarCasoTeste() throws Exception {
		login.direcionaSite(driver);
		evidencia.TakeScreenShot("Step 1 - Acessar site http://desafio.geofusion.tech", document);
		
		Assert.assertTrue((login.validarLogin()));
		
		evidencia.encerrarEvidencia(document);

	}

	@AfterClass
	public static void afterClass() {
		driver.quit();
	}

}
