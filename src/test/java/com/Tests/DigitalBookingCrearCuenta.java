package com.Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigitalBookingCrearCuenta {
    static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

    @BeforeAll
    static void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testing.ctd.academy/registro");
    }

    @Test

    public void crearCuenta() {



        WebElement formulario = driver.findElement(By.className("form-registro"));
        WebElement nombre = driver.findElement(By.id("firstName"));
        WebElement apellido = driver.findElement(By.id("lastName"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement rePassword = driver.findElement(By.id("repassword"));
        WebElement botonEnviar =driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/button"));


//      Pauta implicita
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("form-registro")));

        wait.until(ExpectedConditions.visibilityOfAllElements(nombre, apellido, email, password,rePassword, botonEnviar));

        nombre.clear();
        nombre.sendKeys("Cecilia");
        nombre.sendKeys(Keys.TAB);

        apellido.clear();
        apellido.sendKeys("Abate");
        apellido.sendKeys(Keys.TAB);

        email.clear();
        email.sendKeys("sarasa@elmail.com");
        email.sendKeys(Keys.TAB);

        password.clear();
        password.sendKeys("123456");
        password.sendKeys(Keys.TAB);

        rePassword.clear();
        rePassword.sendKeys("123456");
        email.sendKeys(Keys.TAB);

        wait.until(ExpectedConditions.elementToBeClickable(botonEnviar));
        botonEnviar.click();

        boolean envioExitoso = wait.until(ExpectedConditions.urlToBe("http://testing.ctd.academy/registro-exitoso"));

        assertTrue(envioExitoso);
    }

    @AfterAll
    static void quitDriver(){
        driver.quit();
    }

}
