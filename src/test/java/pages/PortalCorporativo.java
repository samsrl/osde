package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import portal.utils.BusquedaUsuario;
import portal.utils.Ingreso;
import resources.Parametros;
import sam.utils.ListasUtils;
import sam.utils.pom.Base;
import sam.utils.pom.StepFailException;

public class PortalCorporativo extends Base {
	public PortalCorporativo(WebDriver driver) {
		super(driver);
	}
	
	private String numeroSocio = Parametros.env.get("numeroSocio");
	private String window = "Portal Corporativo";
	// ----- Locators -----
	private By btnMasFiltros = By.xpath("//a[@class='engineIconContainer']");
	private By tabFiltro(String filtro) {
		return By.xpath("//div[@id='searchFiltersEngine-tabla']//a[contains(text(),'" + filtro + "')]");
	}
	private By btnArbol1 = By.xpath("//span[@id='motivoContactoLabel']//parent::td//following-sibling::td/img");
	private By btnArbol2 = By.xpath("//span[@id='servicioLabel']//parent::td//following-sibling::td/img");
	private By calendarioDesde = By.xpath("//input[@name='fechaDesde']/following-sibling::img");
	private By calendarioHasta = By.xpath("//input[@name='fechaHasta']/following-sibling::img");
	private By diaActual = By.xpath("//td[@data-handler='selectDay' and contains(@class, 'ui-datepicker-today')]/a");
	private By diaSeleccionado(int actual, int mod) {
		return By.xpath("//td[@data-handler='selectDay']/a[text()=" + (actual + mod) + "]");
	}
	private By usuarioFiltro = By.xpath("//input[@id=\"usuarioIntra\"]");
	private By resultadoDropdown = By.xpath("//li[@role='presentation']/a");
	private By lupaConsulta = By.xpath("//img[@id='buttonSearchBiblos']");
	private By resultadosHeader = By.xpath("//span[@class='portalWidgetHeaderTitle' and contains(text(), 'Actividades')]");
	private By resultadosFiltro = By.xpath("//div[@id='actividadesDiv']//tbody//td");
	private By BtnCerrarNuevoContacto = By.id("closeNuevoContacto");
	private By fechaSeleccionadaFiltroRegistro = By.id("lblPeriodoSeleccionado");
	private By FitroUltSemana = By.id("busquedaUltimaSemana");
	private By PrimerResultadoTablaFiltro = By.xpath("//table[@id='DataTables_Table_0']//span[1]");
	private By TabDetalle = By.id("tab-selector-detalle");
	private By DetalleContenido = By.xpath("//span[@id='estadoActual']/parent::td/preceding-sibling::td");
	private By TabObservaciones = By
			.xpath("//li[@id='tab-selector-detalle']/following-sibling::li[@id='tab-selector-observaciones']");
	private By ObservacionesContenido = By.xpath("//div[@id='observacionesActividad']/table");
	
	// ----- Mensajes -----
	private String msjOkBtnMasFiltros = "El test hizo click en el botón de 'Mostrar más filtros' correctamente";
	private String msjOkTabFiltro = "El test seleccionó la pestaña de 'Actividades' correctamente";
	private String msjOkFechas = "El test seleccionó las fechas del filtro correctamente";
	private String msjOkDropdownUsuario = "El test seleccionó el usuario del menú dropdown correctamente";
	private String msjOkFiltrar = "El test realizó la búsqueda correctamente";
	private String msjOkCerrarContacto = "El test cerró el nuevo contacto correctamente";
	private String msjOkSeleccionarFecha = "El test seleccionó el filtro de fecha correctamente";
	private String msjOkSeleccionarRegistro = "El test seleccionó el registro correctamente";
	private String msjOkValidarTabDetalle = "El test validó la pestaña de 'Detalle' correctamente";
	private String msjOkValidarTabObservaciones = "El test validó la pestaña de 'Observaciones' correctamente";
	
	private String msjErrorCerrarContacto = "El test falló al cerrar el nuevo contacto";
	private String msjErrorSeleccionarFecha = "El test falló al seleccionar el filtro de fecha";
	private String msjErrorSeleccionarFiltro = "El test falló al seleccionar el filtro de 'Actividades'";
	private String msjErrorSeleccionarFechas = "El test falló al seleccionar las fechas del filtro";
	private String msjErrorDropdownUsuario = "El test falló al seleccionar al usuario en el menú dropdown";
	private String msjErrorFiltrar = "El test falló al buscar los resultados del filtro";
	private String msjErrorSeleccionarRegistro = "El test falló al seleccionar el registro";
	private String msjErrorValidarTabs = "El test falló al validar las pestañas del registro";
	// ----- Métodos -----
	public void ingresoPortalCorporativo() throws IOException, StepFailException {
		Ingreso.IngresoPortalCorporativo(driver, Parametros.listaPasos);
	}
	
	public void seleccionarFiltro(String filtro) throws IOException, StepFailException{
		try {
			Thread.sleep(2000);
			
			waitClick(btnMasFiltros);
			System.out.println(msjOkBtnMasFiltros);
			Parametros.listaPasos.add(new ListasUtils("", msjOkBtnMasFiltros, "", null, null, null));
		
			waitClick(tabFiltro(filtro));
			System.out.println(msjOkTabFiltro);
			Parametros.listaPasos.add(new ListasUtils("", msjOkTabFiltro, "", null, null, null));
		}catch(Exception e) {
			throw new StepFailException(e, msjErrorSeleccionarFiltro, window, getDriver());
		}
	}
	
	public void cargarOpcionesFiltro() throws IOException, StepFailException {
		try {
			waitNoClick(btnArbol1);
			waitNoClick(btnArbol2);
			waitClick(calendarioDesde);
			Thread.sleep(1000);
			waitForElementToAppear(diaActual);
			int fecha = Integer.valueOf(getText(diaActual));
			if (fecha >= 5) {
				click(diaSeleccionado(fecha, -4));
				waitClick(calendarioHasta);
				waitClick(diaActual);
			}else {
				click(diaActual);
				waitClick(calendarioHasta);
				waitClick(diaSeleccionado(fecha, 4));
 			}
			System.out.println(msjOkFechas);
			Parametros.listaPasos.add(new ListasUtils("", msjOkFechas, "", null, null, null));
		}catch(Exception e) {
			throw new StepFailException(e, msjErrorSeleccionarFechas, window, getDriver());
		}
		try {
			waitClick(usuarioFiltro);
			clear(usuarioFiltro);
			typeKeys(Parametros.user, usuarioFiltro);
			waitClick(resultadoDropdown);
			
			System.out.println(msjOkDropdownUsuario);
			Parametros.listaPasos.add(new ListasUtils("", msjOkDropdownUsuario, "", null, null, null));
		}catch(Exception e) {
			throw new StepFailException(e, msjErrorDropdownUsuario, window, getDriver());
		}
	}
	
	public void buscarYValidar() throws IOException, StepFailException {
		try {
			click(lupaConsulta);
			waitForElementToAppear(resultadosHeader);
			waitForElementToAppear(resultadosFiltro);
			System.out.println(msjOkFiltrar);
			Parametros.listaPasos.add(new ListasUtils("", msjOkFiltrar, "", null, null, null));
		}catch(Exception e) {
			throw new StepFailException(e, msjErrorFiltrar, window, getDriver());
		}
	}
	
	public void buscarNumeroSocio() throws IOException, StepFailException {
		BusquedaUsuario.BuscarUsuario(BusquedaUsuario.CriterioUsuario.NUMERO_SOCIO, numeroSocio,
				getDriver(), Parametros.listaPasos);
	}
	
	public void CerrarNuevoContacto() throws IOException, StepFailException {
		try {
			waitClick(BtnCerrarNuevoContacto);
			System.out.println(msjOkCerrarContacto);
			Parametros.listaPasos.add(new ListasUtils("", msjOkCerrarContacto, "", null, null, null));
		} catch (Exception e) {
			throw new StepFailException(e, msjErrorCerrarContacto, window, getDriver());
		}
	}
	
	public void SeleccionarFechaRegistro() throws Exception {
		try {
			waitNoClick(fechaSeleccionadaFiltroRegistro);
			if (!getText(fechaSeleccionadaFiltroRegistro).equals("1 Mes")) {
				throw new Exception();
			}
			click(fechaSeleccionadaFiltroRegistro);
			click(FitroUltSemana);
			System.out.println(msjOkSeleccionarFecha);
			Parametros.listaPasos.add(new ListasUtils("", msjOkSeleccionarFecha, "", null, null, null));
			Thread.sleep(5000);

		} catch (Exception e) {
			throw new StepFailException(e, msjErrorSeleccionarFecha, window, getDriver());
		}
	}

	public void SeleccionarPrimerResultado() throws IOException, StepFailException {
		try {
			waitClick(PrimerResultadoTablaFiltro);
			System.out.println(msjOkSeleccionarRegistro);
			Parametros.listaPasos.add(new ListasUtils("", msjOkSeleccionarRegistro, "", null, null, null));
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new StepFailException(e, msjErrorSeleccionarRegistro, window, getDriver());
		}
	}

	public void ValidarRegistro() throws Exception {
		try {
			waitClick(TabDetalle);
			if (getText(DetalleContenido).contains("Estado")) {
				System.out.println(msjOkValidarTabDetalle);
				Parametros.listaPasos.add(new ListasUtils("", msjOkValidarTabDetalle, "", null, null, null));
			} else
				throw new Exception("No se encontró la pestaña de 'Detalle'");

			click(TabObservaciones);
			if (findElement(ObservacionesContenido).isDisplayed()) {
				System.out.println(msjOkValidarTabObservaciones);
				Parametros.listaPasos.add(new ListasUtils("", msjOkValidarTabObservaciones, "", null, null, null));
			} else
				throw new Exception("No se encontró la pestaña de 'Observaciones'");
		} catch (Exception e) {
			throw new StepFailException(e, msjErrorValidarTabs, window, getDriver());
		}
	}
}
 