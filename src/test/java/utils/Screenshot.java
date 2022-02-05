package utils;

import static support.DriverFactory.getDriver;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Screenshot {

	public static void tirar(AppiumDriver<MobileElement> appiumDriver, String arquivo) {
		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(arquivo));
		} catch (Exception e) {
			System.out.println("Houveram problemas ao copiar o arquvivo para a pasta");
		}
	}

}
