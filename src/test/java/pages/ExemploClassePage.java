package pages;

import static org.junit.Assert.assertEquals;

import screens.ExemploClasseScreen;
import support.BasePage;

public class ExemploClassePage extends BasePage {

	ExemploClasseScreen formularioScreen = new ExemploClasseScreen();

	public void exemploEscrever(String texto) {
		scrollAteEncontrarElemento(formularioScreen.exemploElementoEscrever.toString());
		escreverCampo(formularioScreen.exemploElementoEscrever, texto);
		assertEquals(texto, obterTexto(formularioScreen.exemploElementoEscrever));
	}
	
	public void exemploClicar() {
		scrollAteEncontrarElemento(formularioScreen.exemploElementoClicar.toString());
		clicar(formularioScreen.exemploElementoClicar);
	}

	public void exemploCombo(String texto) {
		scrollAteEncontrarElemento(formularioScreen.exemploElementoCombo.toString());
		selecionarComboPorTexto(formularioScreen.exemploElementoCombo, texto);
	}

}
