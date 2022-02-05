package support;

import static java.lang.System.getProperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	private static AppiumDriver<MobileElement> driver;

	public static AppiumDriver<MobileElement> getDriver() {
		try {
			if (driver == null && getProp().getProperty("prop.plataforma").equalsIgnoreCase("Android")) {
				createDriverAndroid();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (driver == null && getProp().getProperty("prop.plataforma").equalsIgnoreCase("IOS")) {
				createDriverIOS();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return driver;
	}

	private static void createDriverAndroid() throws IOException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("deviceName", "emulator-5554");
		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability("app", getProp().getProperty("prop.app"));
		try {
			driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	private static void createDriverIOS() throws IOException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		desiredCapabilities.setCapability("platformName", "IOS");
		desiredCapabilities.setCapability("deviceName", MobileCapabilityType.DEVICE_NAME);
		desiredCapabilities.setCapability("automationName", "XCUITest");
		desiredCapabilities.setCapability("platformVersion", MobileCapabilityType.PLATFORM_VERSION);
		desiredCapabilities.setCapability("app", getProp().getProperty("prop.app"));
		try {
			driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	private static void createTestObjectDriver() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("testobject_api_key", "XXXXXXXXXXXXX");
		desiredCapabilities.setCapability("appiumVersion", "1.7.2");
		desiredCapabilities.setCapability("automationName", "uiautomator2");

		try {
			driver = new AppiumDriver<MobileElement>(new URL("https://us1.appium.testobject.com/wd/hub"),
					desiredCapabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				getProperty("user.dir") + "/src/test/resources/properties/config.properties");
		props.load(file);
		return props;
	}
}
