package resources;

import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import sam.utils.EnvironmentUtils;
import sam.utils.ListasUtils;

public class Parametros {
	public static Dotenv env = EnvironmentUtils.getEnvironment();
	public static String user = Parametros.env.get("user");
	public static String pass = Parametros.env.get("pass");
	public static Boolean notificarClientes = Boolean.parseBoolean(env.get("notificarClientes"));
	public static Boolean notificarDeTodosModos = Boolean.parseBoolean(env.get("notificarDeTodosModos"));
	public static String emailsInternos = env.get("emailsInternos");
	public static String emailsClientes = env.get("emailsClientes");
	public static List<ListasUtils> listaPasos = new ArrayList<ListasUtils>();
	public static List<ListasUtils> listaSkip = new ArrayList<ListasUtils>();
	
	public static String photoDirectoryPath = "src\\test\\resources\\screenshotsFail\\";
}
