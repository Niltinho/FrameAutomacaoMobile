package suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import support.DriverFactory;
import tests.FormularioTeste;
import tests.WebViewTeste;

@RunWith(Suite.class)
@SuiteClasses({
	FormularioTeste.class,
	WebViewTeste.class
})

public class SuiteTeste {
	
	/*@BeforeClass
	public static void inicializar() {
	
	}*/
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
	
}
