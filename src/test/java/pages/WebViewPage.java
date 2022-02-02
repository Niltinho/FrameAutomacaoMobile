package pages;

import org.openqa.selenium.By;

import support.BasePage;

public class WebViewPage extends BasePage {

	public void setEmail(String email) {
		escreverCampo(By.id("email"), email);
	}

	public void setSenha(String senha) {
		escreverCampo(By.id("senha"), senha);
	}

	public void clicarBotaoEntrar() {
		clicar(By.xpath("//button[text()='Entrar']"));
	}

	public String obterMensagem() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}

}
