package pages;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;

import portal.utils.Ingreso;
import resources.Parametros;
import sam.utils.pom.Base;

public class IntranetHome extends Base {
	public IntranetHome(WebDriver driver) {
		super(driver);
	}
	
	public void validarIngresoIntranet() throws IOException {
		Ingreso.validarIntranet(driver, Parametros.listaPasos, Parametros.listaSkip);
	}
	
	public void abrirMisHerramientas () throws InterruptedException, IOException, MessagingException {
		Ingreso.IngresoMisHerramientas(driver, Parametros.listaPasos, Parametros.listaSkip);
	}
	
	public void clickearEnPortalCorporativo () throws Exception {
		Ingreso.DeMisHerramientasAPortal(driver, Parametros.listaPasos, Parametros.listaSkip);
	}
}
