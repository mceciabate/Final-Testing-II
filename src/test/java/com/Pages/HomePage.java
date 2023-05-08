package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    static WebElement linkRegister = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a"));



    public static void ingresarARegistrar(){
        linkRegister.click();
    }






}
