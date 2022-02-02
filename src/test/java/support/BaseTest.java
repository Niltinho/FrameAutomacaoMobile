package support;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import utils.Generator;
import utils.Screenshot;

public class BaseTest {

	@Rule
	public TestName testname = new TestName();

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		String arquivoPrint = "target" + File.separator + "print" + File.separator + testname.getMethodName() + "_"
				+ Generator.dataHoraParaArquivo() + ".png";
		Screenshot.tirar(DriverFactory.getDriver(), arquivoPrint);

		DriverFactory.getDriver().resetApp();
	}

	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
}