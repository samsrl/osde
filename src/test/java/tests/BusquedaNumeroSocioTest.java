package tests;

import org.testng.annotations.Test;

import pages.IntranetHome;
import pages.IntranetLogin;
import pages.PortalCorporativo;
import sam.utils.pom.BrowserManager;

public class BusquedaNumeroSocioTest extends BrowserManager {

	@Test(testName="Búsqueda de Socio por Número de Socio")
	public void test() throws Exception {
		IntranetLogin login = new IntranetLogin(getDriver());
		login.ingreso();
		
		IntranetHome home = new IntranetHome(getDriver());
		home.validarIngresoIntranet();
		home.abrirMisHerramientas();
		home.clickearEnPortalCorporativo();
		
		PortalCorporativo portal = new PortalCorporativo(getDriver());
		portal.ingresoPortalCorporativo();
		portal.buscarNumeroSocio();
		portal.CerrarNuevoContacto();
		portal.SeleccionarFechaRegistro();
		portal.SeleccionarPrimerResultado();
		portal.ValidarRegistro();
	}
}
