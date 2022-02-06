package utils;

import static org.apache.commons.io.FileUtils.copyFile;
import static support.DriverFactory.getDriver;
import static utils.Log.info;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Screenshot {

	/**
	 * @author Nilton L. Correia 
	 * Método para tirar evidência
	 */
	public static void tirar(AppiumDriver<MobileElement> appiumDriver, String arquivo) {
		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			copyFile(screenshot, new File(arquivo));
		} catch (Exception e) {
			info("Houveram problemas ao copiar o arquivo para a pasta");
		}
	}

}
