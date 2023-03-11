package com.Tests;

import com.ExtentReport.ExtentFactory;
import com.Pages.RegistrationPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalBookingCrearCuentaRefactor {


    static WebDriver driver;
    static WebDriverWait wait;
    //REPORTE
    static ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
    static ExtentReports report;

    ExtentTest test;




    @BeforeAll
    public void setUp(){
        driver = RegistrationPage.setup();
        RegistrationPage.url("http://testing.ctd.academy/registro");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        report = ExtentFactory.getInstance();
        report.attachReporter(spark);

    }

    @Test

    public void crearCuenta(){

        test = report.createTest("Create account");
        test.log(Status.INFO, "INICIA EL TEST");
        //capturo los elementos en una lista y espero que sean visibles
        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();
        /*PAUTA IMPLICITA*/
        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        //limpio los campos de los elementos de la lista
        RegistrationPage.limpiarCampos(elementosPresentes);
        //completo los campos
        test.log(Status.PASS, "Se capturan y visualizan los elementos");
        RegistrationPage.completarCampos("Juan", "Perez", "mariaceciliaabate2@mail.com", "123456", "123456");
        test.log(Status.PASS, "Se completaron los campos");
        //submit
        RegistrationPage.enviarForm();
        //espero a que cambie la url y la capturo
        /*PAUTA IMPLICITA*/

        test.addScreenCaptureFromPath("../resources/capturas", "creacion_cuenta.jpg" );
        boolean envioExitoso = wait.until(ExpectedConditions.urlToBe("http://testing.ctd.academy/registro-exitoso"));

        assertTrue(envioExitoso);
        test.log(Status.INFO, "se terminó el test");



    }

    @Test
    public void crearCuentaNegativo()  {
        //capturo los elementos en una lista y espero que sean visibles
        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();
        /*PAUTA IMPLICITA*/
        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        //limpio los campos de los elementos de la lista
        RegistrationPage.limpiarCampos(elementosPresentes);
        //completo los campos
        RegistrationPage.completarCampos("Juan", "Perez", "mariaceciliaabate1@mail.com", "123456", "123456");
        //submit
        RegistrationPage.enviarForm();

        //espero hasta que aparezca el texto en el formulario
        /*PAUTA IMPLICITA*/
        wait.until(ExpectedConditions.textToBePresentInElement(RegistrationPage.capturarFormulario(), "Ese email ya se encuentra registrado"));
        //capturo el parrafo y su texto
        WebElement parrafo = RegistrationPage.registroFallido();
        String envioFallido = parrafo.getText();


       assertTrue(envioFallido.contentEquals("Ese email ya se encuentra registrado"));

    }

    @AfterAll
    public void salir(){
        report.flush();
        RegistrationPage.salir();
    }



}
