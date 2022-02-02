package tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import pages.MenuPage;
import pages.WebViewPage;
import support.BaseTest;

public class WebViewTeste extends BaseTest {
	
	private MenuPage menuPage = new MenuPage();
	private WebViewPage webPage = new WebViewPage();
	
	@Test
	public void deveFazerLogin() throws InterruptedException {
		menuPage.acessarSeuBarrigaHibrido();
		
		Thread.sleep(3000);
		webPage.entrarContextoWeb();
		webPage.setEmail("nilton01@teste.com");
		webPage.setSenha("niltonteste");
		webPage.clicarBotaoEntrar();
		
		assertEquals("Bem vindo, Nilton!", webPage.obterMensagem());
	}
	
	@After
	public void tearDown() {
		webPage.sairContextoWeb();
	}

}
