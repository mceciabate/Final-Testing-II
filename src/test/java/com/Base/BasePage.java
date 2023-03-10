package com.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriver driver;


    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //seteo ChromeOptions para evitar incopatibilidad con la nueva version 111, maximizo la ventana al inicio
        options.addArguments("--remote-allow-origins=*", "start-maximized");
        //paso estas opciones como argumento cuando instancio el ChromeDriver, reemplaza a window().maximaze()
        driver = new ChromeDriver(options);


    }

    public static void url(String URL) {
        driver.get(URL);
    }

    public static void salir() {
        driver.quit();
    }

}
