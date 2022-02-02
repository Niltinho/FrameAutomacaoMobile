package pages;

import support.BasePage;

public class MenuPage extends BasePage {
	
	public void acessarFormulario() {
		clicarPorTexto("Formul�rio");
	}
	
	public void acessarSplash() {
		clicarPorTexto("Splash");
	}
	
	public void acessarAlertas() {
		clicarPorTexto("Alertas");
	}
	
	public void acessarAbas() {
		clicarPorTexto("Abas");
	}
	
	public void acessarAccordion() {
		clicarPorTexto("Accordion");
	}
	
	public void acessarCliques() {
		clicarPorTexto("Cliques");
	}
	
	public void acessarOpcaoEscondida() {
		clicarPorTexto("Op��o bem escondida");
	}
	
	public void acessarSwipe() {
		clicarPorTexto("Swipe");
	}
	
	public void acessarSwipeList() {
		scrollDown();
		clicarPorTexto("Swipe List");
	}
	
	public void acessarDragNDrop() {
		scrollDown();
		clicarPorTexto("Drag and drop");
	}
	
	public void acessarSeuBarrigaHibrido() {
		clicarPorTexto("SeuBarriga H�brido");
	}
	
	public void acessarSeuBarrigaNativo() {
		clicarPorTexto("SeuBarriga Nativo");
	}

}
