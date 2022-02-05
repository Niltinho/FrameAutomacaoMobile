package tests;

import org.junit.Test;

import pages.ExemploClassePage;
import support.BaseTest;

public class ExemploClasseTeste extends BaseTest {

	private ExemploClassePage exemploClassePage = new ExemploClassePage();
	
	@Test
	public void exemploMetodoTeste() {
		exemploClassePage.exemploEscrever("Escrever um texto");
		exemploClassePage.exemploCombo("Escrever o texto para selecionar no combo");
		exemploClassePage.exemploClicar();
	}

}
