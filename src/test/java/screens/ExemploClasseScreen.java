package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.BasePage;

public class ExemploClasseScreen extends BasePage {

	public ExemploClasseScreen() {
		super();
	}

	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoEscrever;

	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoCombo;

	@AndroidFindBy(accessibility = "mapear")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement exemploElementoClicar;

}
