package support;

import static support.DriverFactory.getDriver;
import static support.DriverFactory.killDriver;
import static utils.Generator.dataHoraParaArquivo;
import static utils.Screenshot.tirar;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class BaseTest {

	@Rule
	public TestName testname = new TestName();

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		String arquivoPrint = "target" + File.separator + "print" + File.separator + testname.getMethodName() + "_"
				+ dataHoraParaArquivo() + ".png";
		tirar(getDriver(), arquivoPrint);

		getDriver().resetApp();
	}

	@AfterClass
	public static void finaliza() {
		killDriver();
	}
}