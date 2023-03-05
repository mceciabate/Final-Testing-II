package com.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigitalHouseLogoClassName {

    private WebDriver driver;

    @Test
    public void findLogo() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get( "https://www.digitalhouse.com");
        WebElement logo = driver.findElement(By.className("sr-only"));
        String res = logo.getText();
        Thread.sleep(1000);
        assertTrue(res.contains("Digital House"));
        driver.quit();
    }

}
