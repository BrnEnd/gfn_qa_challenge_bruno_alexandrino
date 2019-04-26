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

import org.junit.Assert;
import telas.Home;
import telas.Login;

import util.GeraEvidencias;

public class CT002 {
	public static WebDriver driver;
	GeraEvidencias evidencia = new GeraEvidencias();
	Home home = new Home();
	Login login = new Login();
	Document document;

	@Before
	public void setUp() throws DocumentException, MalformedURLException, IOException {
		Path userDirPath = Paths.get(System.getProperty("user.dir"));
		Path webdriverPath = userDirPath.resolve("src/main/java/util/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", webdriverPath.toString());
		driver = new ChromeDriver();
		document = evidencia.iniciarEvidencia("002", "Validar inserção do Nome Completo");
	}

	@Test
	public void executarCasoTeste() throws Exception {
		login.direcionaSite(driver);
		evidencia.TakeScreenShot("Step 1 - Acessar site http://desafio.geofusion.tech", document);
		
		home.inserirNomeCompleto(driver, "Bruno Eduardo Alexandrino dos Santos");
		evidencia.TakeScreenShot("Step 2 - Inserir nome completo", document);
		
		home.clicarPesquisar(driver);
		evidencia.TakeScreenShot("Step 3 - Clicar em pesquisar", document);
		
		Assert.assertTrue(home.validarProdutos(driver));
		evidencia.encerrarEvidencia(document);

	}

	@AfterClass
	public static void afterClass() {
		driver.quit();
	}

}
