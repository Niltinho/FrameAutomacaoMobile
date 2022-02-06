package screens;

import static support.DriverFactory.getDriver;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.BasePage;

public class ExemploClasseScreen extends BasePage {

	public ExemploClasseScreen() {
		super();
	}

	//Exemplos de inspecionar elementos Nativo
	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoEscrever;

	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoCombo;

	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoClicar;
	
	
	//Exemplo de inspecionar elementos Webview
	public MobileElement exemploElementoEscrever() {
		return getDriver().findElement(By.xpath("mapear"));
	}

}
