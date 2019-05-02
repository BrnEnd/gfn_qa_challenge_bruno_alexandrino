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
import telas.Produtos;
import util.GeraEvidencias;

public class CT003 {
	public static WebDriver driver;
	static GeraEvidencias evidencia = new GeraEvidencias();
	Home home = new Home();
	Login login = new Login();
	static Document document;
	Produtos produtos = new Produtos();
	
	@Before
	public void setUp() throws DocumentException, MalformedURLException, IOException {
		Path userDirPath = Paths.get(System.getProperty("user.dir"));
		Path webdriverPath = userDirPath.resolve("src/main/java/util/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", webdriverPath.toString());
		driver = new ChromeDriver();
		document = evidencia.iniciarEvidencia("003", "Validar inserção de novo produto");
	}

	@Test
	public void executarCasoTeste() throws Exception {
		login.direcionaSite(driver);
		evidencia.TakeScreenShot("Step 1 - Acessar site http://desafio.geofusion.tech", document);
		
		home.inserirNomeCompleto(driver, "Bruno Eduardo Alexandrino dos Santos");
		evidencia.TakeScreenShot("Step 2 - Inserir nome completo", document);
		
		home.clicarPesquisar(driver);
		evidencia.TakeScreenShot("Step 3 - Clicar em pesquisar", document);
		
		home.acessarNovoProduto(driver);
		evidencia.TakeScreenShot("Step 4 - Clicar em novo produto", document);
		
		produtos.inserirNomeProduto(driver, "Teste");
		evidencia.TakeScreenShot("Step 5 - Inserir nome produto", document);
		
		produtos.inserirPreco(driver, "50000");
		evidencia.TakeScreenShot("Step 6 - Inserir Preco", document);
		
		produtos.selecionarData(driver);
		evidencia.TakeScreenShot("Step 7 - Selecionar data", document);
		
		produtos.salvarProduto(driver);
		evidencia.TakeScreenShot("Step 8 - Salvar Produto", document);
		
		Assert.assertTrue(home.validarProdutos(driver));
		evidencia.TakeScreenShot("Step 9 - Validar redirecionamento para tela Produtos", document);
		
	

	}

	@AfterClass
	public static void afterClass() {
		evidencia.encerrarEvidencia(document);
		driver.quit();
	}

}
