package com.Tests;

import com.Base.BasePage;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.junit.jupiter.api.Test;
public class DigitalBookingRefactorAnotacion {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://youtube.com/");
        Thread.sleep(5000);

    }

    @Test
    public void testeandoYoutube() throws InterruptedException {
        WebElement buscador = driver.findElement(By.name("search_query"));
        buscador.clear();
        buscador.sendKeys("Eve");
        buscador.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String titulo = driver.getTitle();
        //WebElement primerVideo = driver.findElement(By.linkText("thumbnail"));
        //primerVideo.click();
        //Thread.sleep(5000);
        //WebElement titulo = driver.findElement(By.xpath("//*[@id=\"title\"]/h1"));


        assertTrue(titulo.contentEquals("Eve - YouTube"));


    }

    @AfterAll
    public static void cerrarNavegador(){
        driver.quit();
        System.out.println("se termino el test");
    }



}
