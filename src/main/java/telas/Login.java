package telas;

import org.openqa.selenium.WebDriver;

public class Login {
	public WebDriver driver;
	String teste;

	public void direcionaSite(WebDriver driver) {
		driver.get("http://desafio.geofusion.tech");
		driver.manage().window().maximize();
		teste = driver.getTitle();
	}

	public boolean validarLogin() {
		boolean validado = true;
		if (teste.equals("CRUD - Golang Lucas")) {
			System.out.println("Acesso realizado com sucesso");
		} else {
			System.out.println("Não foi possivel realizar o acesso ao site");
			validado = false;
		}
		return validado;
	}
}
