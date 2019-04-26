package telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	public WebDriver driver;

	public void inserirNomeCompleto(WebDriver driver, String nome) throws Exception {
		try {
			driver.findElement(By.id("owner")).sendKeys(nome);
		} catch (Exception e) {
			throw new Exception("Erro ao inserir nome completo");
		}
	}

	public void clicarPesquisar(WebDriver driver) throws Exception {
		try {
			driver.findElement(By.cssSelector("button[ng-click='setOwner(ownerName)']")).click();
		} catch (Exception e) {
			throw new Exception("Erro ao clicar em Pesquisar");
		}
	}

	public boolean validarProdutos(WebDriver driver) {
		boolean validado = true;
		String produto = driver.findElement(By.xpath("//*[@id='top']/div[1]/h2")).getText();
		if (produto.contains("Produtos")) {
			System.out.println("Direcionado para a tela correta");
		} else {
			System.out.println("Sistema não foi direcionado");
			validado = false;
		}
		return validado;
	}
}
