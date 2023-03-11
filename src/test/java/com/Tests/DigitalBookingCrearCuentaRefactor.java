package com.Tests;

import com.Pages.RegistrationPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalBookingCrearCuentaRefactor {


    static WebDriver driver;
    static WebDriverWait wait;



    @BeforeAll
    public void setUp(){
        driver = RegistrationPage.setup();
        RegistrationPage.url("http://testing.ctd.academy/registro");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));


    }

    @Test

    public void crearCuenta(){
        //capturo los elementos en una lista y espero que sean visibles
        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();
        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        //limpio los campos de los elementos de la lista
        RegistrationPage.limpiarCampos(elementosPresentes);
        //completo los campos
        RegistrationPage.completarCampos("Juan", "Perez", "cecilia1@mail.com", "123456", "123456");
        //submit
        RegistrationPage.enviarForm();
        //espero a que cambie la url y la capturo
        boolean envioExitoso = wait.until(ExpectedConditions.urlToBe("http://testing.ctd.academy/registro-exitoso"));

        assertTrue(envioExitoso);


    }

    @Test
    public void crearCuentaNegativo()  {
        //capturo los elementos en una lista y espero que sean visibles
        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();
        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        //limpio los campos de los elementos de la lista
        RegistrationPage.limpiarCampos(elementosPresentes);
        //completo los campos
        RegistrationPage.completarCampos("Juan", "Perez", "cecilia0@mail.com", "123456", "123456");
        //submit
        RegistrationPage.enviarForm();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.form-feedback")));

        WebElement parrafo = RegistrationPage.registroFallido();
        String envioFallido = parrafo.getText();


       assertTrue(envioFallido.contentEquals("Ese email ya se encuentra registrado"));

    }

    @AfterAll
    public void salir(){
        RegistrationPage.salir();
    }



}
