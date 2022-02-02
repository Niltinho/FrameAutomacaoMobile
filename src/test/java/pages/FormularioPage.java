package pages;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import screens.FormularioScreen;
import support.BasePage;
import support.DriverFactory;

public class FormularioPage extends BasePage {

	FormularioScreen formularioScreen = new FormularioScreen();

	public void setNome(String nome) {
//		escreverCampo(MobileBy.AccessibilityId("nome"), nome);
//		escreverCampo(formularioScreen.nome, nome);
		formularioScreen.nome.sendKeys(nome);
	}

	public String obterNome() {
		return obterTexto(MobileBy.AccessibilityId("nome"));
	}

	public void setConsole(String console) {
		selecionarCombo(MobileBy.AccessibilityId("console"), console);
	}

	public String obterConsole() {
		return obterTexto(MobileBy.id("android:id/text1"));
	}

	public void clicarCheckBox() {
		clicar(MobileBy.AccessibilityId("check"));
	}

	public void clicarSwitch() {
		clicar(MobileBy.AccessibilityId("switch"));
	}

	public boolean isCheckMarcado() {
		return isCheckMarcado(MobileBy.AccessibilityId("check"));
	}

	public boolean isSwitchMarcado() {
		return isCheckMarcado(MobileBy.AccessibilityId("switch"));
	}

	public void clicarSeekBar(double posicao) {
		int delta = 50;
		MobileElement seek = DriverFactory.getDriver().findElement(MobileBy.AccessibilityId("slid"));
		int y = seek.getLocation().y + (seek.getSize().height / 2);
		System.out.println(y);

		int xinicial = seek.getLocation().x + delta;
		int x = (int) (xinicial + ((seek.getSize().width - 2 * delta) * posicao));
		System.out.println(x);

		tap(x, y);
	}

	public void clicarSalvar() {
		clicarPorTexto("SALVAR");
	}

	public void clicarProximoMes() {
		clicar(MobileBy.id("android:id/next"));
	}

	public void clicarAnteriorMes() {
		clicar(MobileBy.id("android:id/prev"));
	}

	public String obterNomeCadastrado() {
		return obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Nome:')]"));
	}

	public String obterConsoleCadastrado() {
		return obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]"));
	}

	public String obterCheckCadastrado() {
		return obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));
	}

	public String obterSwitchCadastrado() {
		return obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]"));
	}

	public String obterSliderCadastrado() {
		return obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Slider:')]"));
	}

}
