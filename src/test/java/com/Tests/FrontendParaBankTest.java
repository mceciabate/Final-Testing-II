package com.Tests;

import com.Base.BasePage;
import com.ExtentReport.ExtentFactory;
import com.Pages.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FrontendParaBankTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static ExtentSparkReporter spark = new ExtentSparkReporter("src/test/java/com/ExtentReport/Reports/FrontendRegressionSuiteTest.html");
    static ExtentReports report;
    static String montoATransferir = "300";
    private String username = "naruto";
    private String password = "naruto1234";
    static int indiceCuentaOrigen = 0;
    static int indiceCuentaDestino = 1;
    ExtentTest test;
    ExtentTest test1;
    private String urlBase = "https://parabank.parasoft.com/parabank/index.htm";
    private String urlOverview = "https://parabank.parasoft.com/parabank/overview.htm";

    @BeforeAll
    public void initReport(){
        report = ExtentFactory.getInstance();
        report.attachReporter(spark);

    }

    @BeforeEach
    public void setUp() {
        driver = BasePage.setup();
        BasePage.url(urlBase);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    @Tag("regression")
    public void Aregistro() {
        test = report.createTest("Test Registro Usuario");
        test.log(Status.INFO, "INICIA EL TEST");
        HomePage.ingresarARegistrar();

        List<WebElement> formulario = RegisterPage.obtenerElementosFormulario();
        wait.until(ExpectedConditions.visibilityOfAllElements(formulario));
        test.log(Status.PASS, "Se capturan y visualizan los elementos del formulario");

        RegisterPage.limpiarCampos(formulario);
        test.log(Status.PASS, "Se limpian los campos del formulario");

        RegisterPage.completarCampos(
                "Naruto",
                "Shippuden",
                "Calle Falsa 123",
                "Konoha",
                "Tokyo",
                "1234",
                "123456",
                "123456",
                username,
                password,
                password
        );
        test.log(Status.PASS, "Se completan los campos del formulario");

        RegisterPage.enviarForm();
        test.log(Status.PASS, "Se envía el formulario de manera exitosa");

        WebElement registroExitoso = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/p"));
        wait.until(ExpectedConditions.visibilityOf(registroExitoso));
        String mensaje = registroExitoso.getText();
        test.log(Status.INFO, "Respuesta de registro exitoso :" + mensaje);

        assertTrue(mensaje.equals("Your account was created successfully. You are now logged in."));
        test.log(Status.INFO, "Se terminó el test de Registro de Usuario");

    }

    @Test
    @Tag("regression")
    public void BcrearNuevaCuenta() throws InterruptedException {
        test1 = report.createTest("Test Creación Cuenta");
        test1.log(Status.INFO, "INICIA EL TEST");

        test1.log(Status.INFO, "Se realiza el login con credenciales del usuario");
        List<WebElement> login = CustumerLoginPage.obtenerElementosLogin();
        wait.until(ExpectedConditions.visibilityOfAllElements(login));
        CustumerLoginPage.limpiarCampos(login);
        CustumerLoginPage.ingresar(username, password);

        wait.until(ExpectedConditions.urlToBe(urlOverview));
        test1.log(Status.PASS, "Logueo exitoso");

        test1.log(Status.PASS, "Se accede al menú de crear cuentas");
        LoggedUserMenu.irAAbrirNuevaCuenta();
        NewAccountPage.crearCuentaSavings();
        //TODO: COLOCO PAUTA EXPLICITA PORQUE EL RENDERIZADO ES MUY LENTO
        Thread.sleep(5000);

        WebElement creacionExitosa = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/p[1]"));
        wait.until(ExpectedConditions.visibilityOf(creacionExitosa));
        String mensaje = creacionExitosa.getText();
        test1.log(Status.PASS, "Se crea una nueva cuenta exitosamente");
        test1.log(Status.INFO, "Respuesta de creación de cuenta exitosa : " +mensaje);

        assertTrue(mensaje.equals("Congratulations, your account is now open."));
        test1.log(Status.INFO, "Se terminó el test de Creación de Nueva Cuenta");



    }

    @Test
    @Tag("regression")
    public void CtransferirFondos() throws InterruptedException {
        test1 = report.createTest("Test Transferencia de Fondos");
        test1.log(Status.INFO, "INICIA EL TEST");

        test1.log(Status.INFO, "Se realiza el login con credenciales del usuario");
        List<WebElement> login = CustumerLoginPage.obtenerElementosLogin();
        wait.until(ExpectedConditions.visibilityOfAllElements(login));

        CustumerLoginPage.limpiarCampos(login);
        CustumerLoginPage.ingresar(username, password);

        wait.until(ExpectedConditions.urlToBe(urlOverview));
        test1.log(Status.PASS, "Logueo exitoso");

        LoggedUserMenu.irATransferencias();
        wait.until(ExpectedConditions.visibilityOf(TransferFundsPage.capturarTitulo()));
        test1.log(Status.PASS, "Se accede al menú de transferencias");

        TransferFundsPage.ingresarMonto(montoATransferir);
        TransferFundsPage.seleccionarCuentas(indiceCuentaOrigen, indiceCuentaDestino);
        test1.log(Status.PASS, "Se completan y seleccionan los campos de transacción");

        Thread.sleep(5000);

        WebElement transferenciaRealizada = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/h1"));
        wait.until(ExpectedConditions.visibilityOf(transferenciaRealizada));
        String mensajeTransferenciaExitosa = transferenciaRealizada.getText();
        test1.log(Status.PASS, "Se realiza la transacción de manera exitosa");
        test1.log(Status.INFO, "Respuesta de transacción exitosa : " +mensajeTransferenciaExitosa);
        assertTrue(mensajeTransferenciaExitosa.equals("Transfer Complete!"));
        test1.log(Status.INFO, "Se terminó el test de Transacción");

    }


    @Test
    @Tag("regression")
    public void DactividadMensual() throws InterruptedException {
        Thread.sleep(5000);
        test1 = report.createTest("Test Actividades de Cuenta");
        test1.log(Status.INFO, "INICIA EL TEST");

        test1.log(Status.INFO, "Se realiza el login con credenciales del usuario");
        CustumerLoginPage.obtenerElementosLogin();
        CustumerLoginPage.ingresar(username, password);
        test1.log(Status.PASS, "Logueo exitoso");

        wait.until(ExpectedConditions.urlToBe(urlOverview));
        LoggedUserMenu.ingresarAActividadCuentas();
        test1.log(Status.PASS, "Se accede al menú de actividad de Cuentas");

        Thread.sleep(5000);

        WebElement numeroCuenta = driver.findElement(By.xpath("//*[@id=\"accountTable\"]/tbody/tr[1]/td[1]/a"));
        String obtenerNumeneroCuenta = numeroCuenta.getText();
        test1.log(Status.PASS, "Se captura el número de la primer cuenta de la lista ");
        wait.until(visibilityOfElementLocated(By.linkText(obtenerNumeneroCuenta))).click();
        wait.until(ExpectedConditions.urlContains("activity.htm?"));
        test1.log(Status.PASS, "Se ingresa al detalle de la primer cuenta");

        WebElement tituloDetalle = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div[1]/h1"));
        String titulo = tituloDetalle.getText();
        test1.log(Status.PASS, "Se realiza la visualiza la información de la cuenta de manera exitosa de manera exitosa");
        test1.log(Status.INFO, "Respuesta de detalles de cuenta : " + titulo);

        assertTrue(titulo.equals("Account Details"));
        test1.log(Status.INFO, "Se terminó el test de Actividad Mensual de Cuenta");
    }


    @AfterEach
    public void salir() {
            BasePage.salir();
    }

    @AfterAll
    public void cerrarReporte(){
        report.flush();
    }


}
