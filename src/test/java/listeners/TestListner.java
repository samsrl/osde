package listeners;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import resources.Parametros;
import sam.utils.EmailUtils;
import sam.utils.ListasUtils;
import sam.utils.ScreenshotsUtils;
import sam.utils.pom.StepFailException;
import sam.utils.pom.StepSkipException;

public class TestListner implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		sendEmail(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		StepFailException error = (StepFailException) result.getThrowable();
		System.out.println(error.getMessage());
		try {
			String photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla("", Parametros.photoDirectoryPath, error.driver);
			ListasUtils pasos = new ListasUtils(error.url, "", error.getMessage(),
					photoFilePath, "Portal Corporativo", (Exception) error.getCause());
			Parametros.listaPasos.add(pasos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(error.msjFail + error.getCause());
		sendEmail(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		StepSkipException error = (StepSkipException) result.getThrowable();
		System.out.println(error.msjSkip);
		System.out.println(error.getMessage());
		System.out.println("Error" + error.getCause());
		String photoFilePath;
		try {
			photoFilePath = ScreenshotsUtils.TomarCapturaDePantalla("Intranet", Parametros.photoDirectoryPath, error.driver);
			ListasUtils pasos = new ListasUtils(error.url, "", error.getMessage(), 
					photoFilePath,"Intranet", error);
			Parametros.listaSkip.add(pasos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendEmail(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {
	}
	
	private void sendEmail(ITestResult result) {
		try {
			Method method = result.getMethod().getConstructorOrMethod().getMethod();
		    Test test = method.getAnnotation(Test.class);
		    String testName = test.testName();
		    
			EmailUtils.NotificarPorMail("Portal Corporativo", testName, Parametros.emailsInternos, 
					Parametros.emailsClientes, Parametros.listaPasos, Parametros.listaSkip,
					Parametros.notificarDeTodosModos, Parametros.notificarClientes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
