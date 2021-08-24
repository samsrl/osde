package pages;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;

import portal.utils.Ingreso;
import resources.Parametros;
import sam.utils.pom.Base;

public class IntranetLogin extends Base {
	public IntranetLogin(WebDriver driver) {
		super(driver);
	}
	
	public void ingreso() throws InterruptedException, IOException, MessagingException {
		Ingreso.loginIntranet(Parametros.user, Parametros.pass, driver, 
							Parametros.listaPasos, Parametros.listaSkip);
	}
}
