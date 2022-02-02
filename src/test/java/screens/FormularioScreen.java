package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import support.BasePage;

public class FormularioScreen extends BasePage {
	
	public FormularioScreen() {
		super();
	}

	@AndroidFindBy(accessibility = "nome")
	@iOSXCUITFindBy(accessibility = "mapear")
	public MobileElement nome;

}
