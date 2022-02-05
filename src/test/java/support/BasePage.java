package support;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static support.DriverFactory.getDriver;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	public BasePage() {
		initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	private By getElement(String element) {
		element = element.replace("Located by By.chained({By.", "").replace("})", "");
		if (element.contains("xpath: ") || element.contains("//")) {
			return MobileBy.xpath(element.replace("xpath: ", "").replace("By.", ""));
		} else if (element.contains("AccessibilityId: ")) {
			return MobileBy.AccessibilityId(element.replace("AccessibilityId:", "").substring(1));
		} else {
			return MobileBy.id(element.replace("id: ", "").replace("By.", ""));
		}
	}

	public void escreverCampo(MobileElement elemento, String texto) {
		elemento.sendKeys(texto);
	}

	public void escreverCampoPorTexto(String texto, String valor) {
		getDriver().findElement(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")).sendKeys(valor);
	}

	public String obterTexto(MobileElement elemento) {
		return elemento.getText();
	}

	public void clicar(MobileElement elemento) {
		elemento.click();
	}

	public void clicarPorTexto(String texto) {
		getDriver().findElement(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")).click();
	}

	public void selecionarComboPorTexto(MobileElement elemento, String valor) {
		elemento.click();
		clicarPorTexto(valor);
	}

	public void selecionarCombo(MobileElement elemento, String valor) {
		Select selecionar = new Select(elemento);
		selecionar.selectByVisibleText(valor);
	}

	public boolean isCheckMarcado(MobileElement elemento) {
		return elemento.getAttribute("checked").equalsIgnoreCase("true");
	}

	public boolean existeElementoPorTexto(String texto) {
		List<MobileElement> elementos = getDriver()
				.findElements(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']"));
		return elementos.size() > 0;
	}

	private boolean existeElemento(By by) {
		boolean status = false;
		if (DriverFactory.getDriver().findElements(by).size() > 0) {
			status = true;
		}
		return status;
	}

	public void tap(int x, int y) {
		new TouchAction<>(getDriver()).tap(point(x, y)).waitAction().perform();
	}

	public void aguardarElementoPorTexto(String texto) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(visibilityOfElementLocated(MobileBy.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")));
	}

	public void aguardarElementoAparecer(String elemento) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(visibilityOfElementLocated(getElement(elemento)));
	}

	public void aguardarElementoDesaparecer(String elemento) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(invisibilityOfElementLocated(getElement(elemento)));
	}

	private void scroll(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();

		int x = size.width / 2;

		int start_y = (int) (size.height * inicio);
		int end_y = (int) (size.height * fim);

		new TouchAction<>(getDriver()).press(point(x, start_y)).waitAction().moveTo(point(x, end_y)).release()
				.perform();
	}

	public void scrollElement(WebElement element, double inicio, double fim) {
		int x = element.getLocation().x + (element.getSize().width / 2);

		int start_y = (int) (element.getSize().height * inicio);
		int end_y = (int) (element.getSize().height * fim);

		new TouchAction<>(getDriver()).press(point(x, start_y)).waitAction().moveTo(point(x, end_y)).release()
				.perform();
	}

	public void scrollAteEncontrarElemento(String elemento) {
		for (int qtd = 0; !existeElemento(getElement(elemento)) && qtd < 10; qtd++) {
			scrollDown();
		}
	}

	private void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();

		int y = size.height / 2;

		int start_x = (int) (size.width * inicio);
		int end_x = (int) (size.width * fim);

		new TouchAction<>(getDriver()).press(point(start_x, y)).waitAction().moveTo(point(end_x, y)).release()
				.perform();
	}

	public void swipeElement(WebElement element, double inicio, double fim) {
		int y = element.getLocation().y + (element.getSize().height / 2);

		int start_x = (int) (element.getSize().width * inicio);
		int end_x = (int) (element.getSize().width * fim);

		new TouchAction<>(getDriver()).press(point(start_x, y)).waitAction().moveTo(point(end_x, y)).release()
				.perform();
	}

	public void scrollDown() {
		scroll(0.9, 0.1);
	}

	public void scrollUp() {
		scroll(0.1, 0.9);
	}

	public void swipeLeft() {
		swipe(0.9, 0.1);
	}

	public void swipeRight() {
		swipe(0.1, 0.9);
	}

	public void esconderTeclado() {
		getDriver().hideKeyboard();
	}

	public void clicarLongoPorTexto(String texto) {
		MobileElement element = getDriver()
				.findElement(MobileBy.xpath("//*[@text='" + texto + "' or @name='" + texto + "']"));
		new TouchAction<>(getDriver()).longPress(element(element)).perform();
	}

	public void entrarContextoWeb() {
		if (verificaQtdContexto() >= 1) {
			getDriver().context((String) getDriver().getContextHandles().toArray()[verificaQtdContexto()]);
			System.out.println("Trocado para o Contexto Webview");
		} else {
			System.out.println("Não foi possível trocar para o Contexto Webview");
		}
	}

	public void entrarContextoWeb(int index) {
		if (verificaQtdContexto() >= 1 && index <= verificaQtdContexto() && index > 0) {
			getDriver().context((String) getDriver().getContextHandles().toArray()[index]);
			System.out.println("Trocado para o Contexto Webview");
		} else {
			System.out.println(
					"Não foi possível trocar para o Contexto Webview, quantidade contexto = " + verificaQtdContexto());
		}
	}

	public void entrarContextoWeb(String nomeContexto) {
		if (nomeContexto != "NATIVE_APP") {
			getDriver().context(nomeContexto);
			System.out.println("Trocado para o Contexto Webview " + nomeContexto);
		}
		if (nomeContexto == "NATIVE_APP") {
			System.out.println("Não foi possível trocar para o Contexto Webview");
		}
	}

	public void sairContextoWeb() {
		getDriver().context((String) getDriver().getContextHandles().toArray()[0]);
		System.out.println("Trocado para o Contexto Nativo");
	}

	public void verificaContexto() {
		Set<String> contextHandles = getDriver().getContextHandles();
		for (String context : contextHandles) {
			System.out.println(context);
		}
	}

	private int verificaQtdContexto() {
		Set<String> contextHandles = getDriver().getContextHandles();
		int convertIndex = contextHandles.size() - 1;
		return convertIndex;
	}

}
