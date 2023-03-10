package com.Tests;

import com.Pages.RegistrationPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalBookingCrearCuentaRefactor {

    static WebDriver driver;
//    static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));



    public void setUpDriver(){


    }

    @Test

    public void crearCuenta(){

//        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        //seteo ChromeOptions para evitar incopatibilidad con la nueva version 111, maximizo la ventana al inicio
//        options.addArguments("--remote-allow-origins=*", "start-maximized");
//        //paso estas opciones como argumento cuando instancio el ChromeDriver, reemplaxa a la linea comentada
//        driver = new ChromeDriver(options);
//        //driver.manage().window().maximize();
//        driver.get("http://testing.ctd.academy/registro");

        RegistrationPage.setup();
        RegistrationPage.url("http://testing.ctd.academy/registro");


        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();

//        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        RegistrationPage.limpiarCampos(elementosPresentes);
        RegistrationPage.completarCampos("Juan", "Perez", "mail@mail.com", "123456", "123456");

        RegistrationPage.enviarForm();

        RegistrationPage.salir();

//        boolean envioExitoso = wait.until(ExpectedConditions.urlToBe("http://testing.ctd.academy/registro-exitoso"));

//        assertTrue(envioExitoso);
//




    }


//    @AfterAll
//    public void quitDriver(){
//        driver.quit();
//    }



}
