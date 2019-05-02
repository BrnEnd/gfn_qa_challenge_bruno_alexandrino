package telas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Produtos {
	public WebDriver driver;

	public void inserirNomeProduto(WebDriver driver, String nomeProduto) throws Exception {
		try {
			driver.findElement(By.id("campo1")).sendKeys(nomeProduto);
		} catch (Exception e) {
			throw new Exception("Erro ao inserir Nome Produto");
		}
	}

	public void inserirPreco(WebDriver driver, String precoProduto) throws Exception {
		try {
			driver.findElement(By.cssSelector("input[ng-model='product.price']")).sendKeys(precoProduto);
		} catch (Exception e) {
			throw new Exception("Erro ao inserir precos");
		}
	}

	public void clicarPesquisar(WebDriver driver) throws Exception {
		try {
			driver.findElement(By.cssSelector("button[ng-click='setOwner(ownerName)']")).click();
		} catch (Exception e) {
			throw new Exception("Erro ao clicar em Pesquisar");
		}
	}

	public void selecionarData(WebDriver driver) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		
		Date data = new Date();
		GregorianCalendar dataCal = new GregorianCalendar();
		
		dataCal.setTime(data);
		int dataC = dataCal.get(Calendar.DAY_OF_MONTH);
		String dia = sdf.format(dataC);
		
		driver.findElement(By.id("campo3")).click();
		
		WebElement dateWidgetFrom = driver.findElement(By.xpath("/html/body/div[3]"));
		List<WebElement> columns = dateWidgetFrom.findElements(By.tagName("td"));
		
		for (WebElement cell : columns) {
			if (cell.getText().equals(dia)) {
				cell.click();
				break;
			}
		}
	}

	public void salvarProduto(WebDriver driver) {
		driver.findElement(By.cssSelector("button[ng-click='addProduct()']")).click();
	}

}
