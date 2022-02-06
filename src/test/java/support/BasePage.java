package support;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static support.DriverFactory.getDriver;
import static utils.Log.info;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BasePage {

	/**
	 * @author Nilton L. Correia 
	 * Método para inicializar os elementos android e iOS
	 */
	public BasePage() {
		initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	/**
	 * @author Nilton L. Correia 
	 * Método que converte um elemento By(xpath,
	 *         accessibity e id) para um elemento String
	 */
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
	
	/**
	 * @author Nilton L. Correia 
	 * Método que escreve num campo de texto
	 */
	public void escreverCampo(MobileElement elemento, String texto) {
		elemento.sendKeys(texto);
		info(texto + " escrito com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que escreve num campo de texto, passando o texto do elemento
	 */
	public void escreverCampoPorTexto(String texto, String valor) {
		getDriver().findElement(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")).sendKeys(valor);
		info(valor + " escrito com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que obtém o texto do elemento
	 */
	public String obterTexto(MobileElement elemento) {
		return elemento.getText();
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento
	 */
	public void clicar(MobileElement elemento) {
		elemento.click();
		info("Clique no elemento: " + elemento);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento, passando o texto do elemento
	 */
	public void clicarPorTexto(String texto) {
		getDriver().findElement(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")).click();
		info("Clique no elemento: " + texto);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento do combo, passando o texto do elemento
	 */
	public void selecionarComboPorTexto(MobileElement elemento, String valor) {
		elemento.click();
		clicarPorTexto(valor);
		info(valor + " selecionado com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que clica no elemento visível do combo
	 */
	public void selecionarCombo(MobileElement elemento, String valor) {
		Select selecionar = new Select(elemento);
		selecionar.selectByVisibleText(valor);
		info(valor + " selecionado com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que verifica se o checkBox está selecionado
	 */
	public boolean isCheckSelecionado(MobileElement elemento) {
		return elemento.getAttribute("checked").equalsIgnoreCase("true");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que verifica se existe o elemento, passando o texto do elemento
	 */
	public boolean existeElementoPorTexto(String texto) {
		List<MobileElement> elementos = getDriver()
				.findElements(By.xpath("//*[@text='" + texto + "' or @name='" + texto + "']"));
		return elementos.size() > 0;
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que verifica se existe o elemento
	 */
	private boolean existeElemento(By by) {
		boolean status = false;
		if (getDriver().findElement(by).isDisplayed()) {
			status = true;
		}
		return status;
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que executa o tap na tela com base nas coordenadas
	 */
	public void tap(int x, int y) {
		new TouchAction<>(getDriver()).tap(point(x, y)).waitAction().perform();
		info("Tap executado com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que aguarda o elemento aparecer, passando o texto do elemento
	 */
	public void aguardarElementoPorTexto(String texto) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(visibilityOfElementLocated(MobileBy.xpath("//*[@text='" + texto + "' or @name='" + texto + "']")));
		info(texto + " apareceu com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que aguarda o elemento aparecer
	 */
	public void aguardarElementoAparecer(String elemento) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(visibilityOfElementLocated(getElement(elemento)));
		info(elemento + " apareceu com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que aguarda o elemento desaparecer
	 */
	public void aguardarElementoDesaparecer(String elemento) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(invisibilityOfElementLocated(getElement(elemento)));
		info(elemento + " desapareceu com sucesso");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll com base nas coordenadas
	 */
	private void scroll(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();

		int x = size.width / 2;

		int start_y = (int) (size.height * inicio);
		int end_y = (int) (size.height * fim);

		new TouchAction<>(getDriver()).press(point(x, start_y)).waitAction().moveTo(point(x, end_y)).release()
				.perform();
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll no elemento com base nas coordenadas
	 */
	public void scrollElement(MobileElement elemento, double inicio, double fim) {
		int x = elemento.getLocation().x + (elemento.getSize().width / 2);

		int start_y = (int) (elemento.getSize().height * inicio);
		int end_y = (int) (elemento.getSize().height * fim);

		new TouchAction<>(getDriver()).press(point(x, start_y)).waitAction().moveTo(point(x, end_y)).release()
				.perform();
		info("Realizado o scroll para o elemento: " + elemento);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll até encontrar o elemento
	 */
	public void scrollAteEncontrarElemento(String elemento) {
		for (int qtd = 0; !existeElemento(getElement(elemento)) && qtd < 10; qtd++) {
			scrollDown();
		}
		info("Realizado o scroll para o elemento: " + elemento);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o swipe com base nas coordenadas
	 */
	private void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();

		int y = size.height / 2;

		int start_x = (int) (size.width * inicio);
		int end_x = (int) (size.width * fim);

		new TouchAction<>(getDriver()).press(point(start_x, y)).waitAction().moveTo(point(end_x, y)).release()
				.perform();
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o swipe no elemento com base nas coordenadas
	 */
	public void swipeElement(MobileElement elemento, double inicio, double fim) {
		int y = elemento.getLocation().y + (elemento.getSize().height / 2);

		int start_x = (int) (elemento.getSize().width * inicio);
		int end_x = (int) (elemento.getSize().width * fim);

		new TouchAction<>(getDriver()).press(point(start_x, y)).waitAction().moveTo(point(end_x, y)).release()
				.perform();
		info("Realizado o swipe para o elemento: " + elemento);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll para baixo
	 */
	public void scrollDown() {
		scroll(0.9, 0.1);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o scroll para cima
	 */
	public void scrollUp() {
		scroll(0.1, 0.9);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o swipe para esquerda
	 */
	public void swipeLeft() {
		swipe(0.9, 0.1);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz o swipe para direita
	 */
	public void swipeRight() {
		swipe(0.1, 0.9);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que esconde o teclado
	 */
	public void esconderTeclado() {
		getDriver().hideKeyboard();
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz um clique longo no elemento
	 */
	public void clicarLongo(MobileElement elemento) {
		new TouchAction<>(getDriver()).longPress(element(elemento)).perform();
		info("Clique no elemento: " + elemento);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que faz um clique longo no elemento, passando texto
	 */
	public void clicarLongoPorTexto(String texto) {
		MobileElement element = getDriver()
				.findElement(MobileBy.xpath("//*[@text='" + texto + "' or @name='" + texto + "']"));
		new TouchAction<>(getDriver()).longPress(element(element)).perform();
		info("Clique no elemento: " + element);
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que entra no contexto Webview
	 */
	public void entrarContextoWeb() {
		if (verificaQtdContexto() >= 1) {
			getDriver().context((String) getDriver().getContextHandles().toArray()[verificaQtdContexto()]);
			info("Trocado para o Contexto Webview");
		} else {
			info("Não foi possível trocar para o Contexto Webview");
		}
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que entra no contexto Webview, passando o index
	 */
	public void entrarContextoWeb(int index) {
		if (verificaQtdContexto() >= 1 && index <= verificaQtdContexto() && index > 0) {
			getDriver().context((String) getDriver().getContextHandles().toArray()[index]);
			info("Trocado para o Contexto Webview");
		} else {
			info("Não foi possível trocar para o Contexto Webview, quantidade contexto = " + verificaQtdContexto());
		}
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que entra no contexto Webview, passando o nome do contexto
	 */
	public void entrarContextoWeb(String nomeContexto) {
		if (nomeContexto != "NATIVE_APP") {
			getDriver().context(nomeContexto);
			info("Trocado para o Contexto Webview " + nomeContexto);
		}
		if (nomeContexto == "NATIVE_APP") {
			info("Não foi possível trocar para o Contexto Webview");
		}
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que sai do contexto Webview e retorna para o Nativo
	 */
	public void sairContextoWeb() {
		getDriver().context((String) getDriver().getContextHandles().toArray()[0]);
		info("Trocado para o Contexto Nativo");
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que verifica o(s) contexto(s) ativo(s)
	 */
	public void verificaContexto() {
		Set<String> contextHandles = getDriver().getContextHandles();
		for (String context : contextHandles) {
			info(context);
		}
	}
	
	/**
	 * @author Nilton L. Correia 
	 * Método que verifica a quantidade de contexto
	 */
	private int verificaQtdContexto() {
		Set<String> contextHandles = getDriver().getContextHandles();
		int convertIndex = contextHandles.size() - 1;
		return convertIndex;
	}

}
