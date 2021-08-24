package tests;

import org.testng.annotations.Test;

import pages.IntranetHome;
import pages.IntranetLogin;
import pages.PortalCorporativo;
import sam.utils.pom.BrowserManager;

public class ActividadSocioTest extends BrowserManager {
	@Test(testName="Actividad de Socio")
	public void test() throws Exception {
		IntranetLogin login = new IntranetLogin(getDriver());
		login.ingreso();
		
		IntranetHome home = new IntranetHome(getDriver());
		home.validarIngresoIntranet();
		home.abrirMisHerramientas();
		home.clickearEnPortalCorporativo();
		
		PortalCorporativo portal = new PortalCorporativo(getDriver());
		portal.ingresoPortalCorporativo();
		portal.seleccionarFiltro("Actividades");
		portal.cargarOpcionesFiltro();
		portal.buscarYValidar();
	}
}
