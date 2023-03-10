package com.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {

    protected static WebDriver driver;


    public static WebDriver setup() {
//        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();

        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //seteo ChromeOptions para evitar incopatibilidad con la nueva version 111, maximizo la ventana al inicio
        options.addArguments("--remote-allow-origins=*", "start-maximized");
        //paso estas opciones como argumento cuando instancio el ChromeDriver, reemplaza a window().maximaze()
        driver = new ChromeDriver(options);


        return driver;
    }

    public static void url(String URL) {
        driver.get(URL);
    }

    public static void salir() {
        driver.quit();
    }

}
