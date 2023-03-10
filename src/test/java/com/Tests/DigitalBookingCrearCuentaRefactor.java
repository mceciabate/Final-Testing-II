package com.Tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.Tests.DigitalBookingCrearCuenta.driver;

public class DigitalBookingCrearCuentaRefactor {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));

    @BeforeAll

    static void setUpDriver(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testing.ctd.academy/registro");
    }

    @Test

    public void crearCuenta(){


    }



}
