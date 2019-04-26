package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAlice {

	public WebDriver driver;

	public static void geraEvidencia() throws AWTException {
		String format = "jpg";
		Robot robot = new Robot();

		Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

	}

	public void direcionaSite(WebDriver driver, String ambiente) {
		// instanciaDriver();
		if (ambiente.equalsIgnoreCase("stage")) {
			driver.get("https://alice-stage-br-aws.wwbr.com.br/campaign/");
			driver.manage().window().maximize();
			String teste = driver.getTitle();
		} else {
			driver.get("https://www.westwing.com.br/");
			driver.manage().window().maximize();
			String teste = driver.getTitle();
		}
	}

	//

	public void encerraBrowser(WebDriver drive) {
		driver.close();
	}

	public void aguardarCarregar() {
		driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
	}

	public void realizaLogin(WebDriver driver, String email, String senha, String ambiente) throws Exception {
		try {
			if (ambiente.equalsIgnoreCase("stage")) {
				driver.findElement(
						By.xpath("//*[@id=\"customerAccountCreate\"]/section/div[1]/div[2]/div[2]/button[1]")).click();
				driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
				driver.findElement(By.id("signup-resp__form__input--password")).sendKeys(senha);
				driver.findElement(By.cssSelector("img[class='signup-resp__loading is-hidden']")).click();
			} else {
				driver.findElement(By.xpath("//*[@id=\"customerAccountCreate\"]/section/div[1]/div[2]/div[2]/a"))
						.click();
				driver.findElement(By.cssSelector("input[type='email']")).sendKeys(email);
				driver.findElement(By.id("signup-resp__form__input--password")).sendKeys(senha);
				driver.findElement(By.cssSelector("img[class='signup-resp__loading is-hidden']")).click();

			}
		} catch (Exception e) {
			throw new Exception("Erro ao realizar Login");
		}
	}

	public boolean validarLogin(WebDriver driver, String ambiente) throws InterruptedException, Exception {
		try {
			boolean validado = true;
			String validar;
			Thread.sleep(5000);
			if (ambiente.equalsIgnoreCase("stage")) {
				validar = driver
						.findElement(By.xpath(
								"//*[@id=\"campaignIndex\"]/div[2]/header/div[2]/div/div/div[3]/ul/li[1]/span[1]"))
						.getText();
				System.out.println(validar);
			} else {
				validar = driver
						.findElement(By.xpath(
								"//*[@id=\"campaignIndex\"]/div[2]/header/div[2]/div/div/div[3]/ul/li[1]/span[1]"))
						.getText();
			}
			
			if (validar.equalsIgnoreCase("Minha conta")) {
				System.out.println("Login realizado com sucesso");
			} else {
				validado = false;
				System.out.println("Não foi realizado o login");
			}
			return validado;
		} catch (Exception e) {
			throw new Exception("Erro ao recuperar elemento");
		}
	}

}