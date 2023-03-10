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





    @Test

    public void crearCuenta(){

        WebDriver driver = RegistrationPage.setup();
        RegistrationPage.url("http://testing.ctd.academy/registro");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        ArrayList<WebElement> elementosPresentes = new ArrayList<>();
        elementosPresentes = (ArrayList<WebElement>) RegistrationPage.obtenerElementosFormulario();

        wait.until(ExpectedConditions.visibilityOfAllElements(elementosPresentes));
        RegistrationPage.limpiarCampos(elementosPresentes);
        RegistrationPage.completarCampos("Juan", "Perez", "mail1@mail.com", "123456", "123456");

        RegistrationPage.enviarForm();

        boolean envioExitoso = wait.until(ExpectedConditions.urlToBe("http://testing.ctd.academy/registro-exitoso"));

        assertTrue(envioExitoso);

        RegistrationPage.salir();
    }



}
