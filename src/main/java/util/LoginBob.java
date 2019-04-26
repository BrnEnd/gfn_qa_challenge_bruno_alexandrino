package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBob {

	public WebDriver driver;

	public void direcionaSite(WebDriver driver, String ambiente) {
		if (ambiente.equalsIgnoreCase("stage")) {
			driver.get("http://bob-stage-br-aws.wwbr.com.br");
			driver.manage().window().maximize();
		} else {
			driver.get("https://bob.westwing.com.br");
			driver.manage().window().maximize();
		}
	}
	
	public void clicarLogin(WebDriver driver) {
		driver.findElement(By.id("login")).click();
	}
	
	public void realizarLogin(WebDriver driver, String email, String senha) throws InterruptedException {
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("next")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("Passwd")).sendKeys(senha);
		driver.findElement(By.id("signIn")).click();
	}
	
}
