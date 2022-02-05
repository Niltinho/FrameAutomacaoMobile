package suites;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import support.DriverFactory;
import tests.ExemploClasseTeste;

@RunWith(Suite.class)
@SuiteClasses({
	// Informar a(s) classe(s) abaixo
	ExemploClasseTeste.class,
})

public class SuiteTeste {
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
	
}
