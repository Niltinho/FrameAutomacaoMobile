package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import pages.FormularioPage;
import pages.MenuPage;
import support.BaseTest;
import support.DriverFactory;

public class FormularioTeste extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private FormularioPage formPage = new FormularioPage();

	@Ignore
	public void deveInstalarApp() {

	}

	@Test
	public void devePreencherCampoTexto() {
		menuPage.acessarFormulario();
		formPage.setNome("Nilton");
		assertEquals("Nilton", formPage.obterNome());
	}

	@Test
	public void deveInteragirCombo() {
		menuPage.acessarFormulario();
		formPage.setConsole("PS4");
		assertEquals("PS4", formPage.obterConsole());
	}

	@Test
	public void deveInteragirSwitchCheckBox() {
		menuPage.acessarFormulario();
		assertFalse(formPage.isCheckMarcado());
		assertTrue(formPage.isSwitchMarcado());

		formPage.clicarCheckBox();
		formPage.clicarSwitch();

		assertTrue(formPage.isCheckMarcado());
		assertFalse(formPage.isSwitchMarcado());
	}

	@Test
	public void deveRealizarCadastro() {
		menuPage.acessarFormulario();
		formPage.setNome("Nilton");
		formPage.setConsole("XBox One");
		formPage.clicarCheckBox();
		formPage.clicarSwitch();
		formPage.clicarSalvar();

		System.out.println(formPage.obterNomeCadastrado());
		System.out.println(formPage.obterConsoleCadastrado());
		System.out.println(formPage.obterCheckCadastrado());
		System.out.println(formPage.obterSwitchCadastrado());

		assertEquals("Nome: Nilton", formPage.obterNomeCadastrado());
		assertEquals("Console: xone", formPage.obterConsoleCadastrado());
		assertEquals("Checkbox: Marcado", formPage.obterCheckCadastrado());
		assertEquals("Switch: Off", formPage.obterSwitchCadastrado());
	}

	@Test
	public void deveRealizarCadastroDemorado() {
		menuPage.acessarFormulario();
		formPage.setNome("Nilton");
		formPage.clicarSalvar();

		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Nome: Nilton']")));

		assertEquals("Nome: Nilton", formPage.obterNomeCadastrado());
	}

	@Test
	public void deveAlterarData() {
		menuPage.acessarFormulario();

		formPage.clicarPorTexto("01/01/2000");
		formPage.clicarPorTexto("2000");
		formPage.clicarPorTexto("2001");
		formPage.clicarProximoMes();
		formPage.clicarProximoMes();
		formPage.clicarPorTexto("27");
		formPage.clicarPorTexto("OK");

		assertTrue(formPage.existeElementoPorTexto("27/4/2001"));
	}

	@Test
	public void deveAlterarHora() {
		menuPage.acessarFormulario();

		formPage.clicarPorTexto("06:00");
		formPage.clicar(MobileBy.AccessibilityId("9"));
		formPage.clicar(MobileBy.AccessibilityId("50"));
		formPage.clicarPorTexto("OK");

		assertTrue(formPage.existeElementoPorTexto("9:50"));
	}

	@Test
	public void deveInteragirComSeekBar() {
		menuPage.acessarFormulario();

		formPage.clicarSeekBar(0.60);
		formPage.clicarSalvar();

		assertEquals("Slider: 60", formPage.obterSliderCadastrado());
	}
}
