package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage extends BasePage {

//    static WebElement formulario = driver.findElement(By.className("form-registro"));
    static WebElement nombre = driver.findElement(By.id("firstName"));
    static WebElement apellido = driver.findElement(By.id("lastName"));
    static WebElement email = driver.findElement(By.id("email"));
    static WebElement password = driver.findElement(By.id("password"));
    static WebElement rePassword = driver.findElement(By.id("repassword"));
    static WebElement botonEnviar =driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/button"));


    public static void limpiarCampos(WebElement e){
        e.clear();
    }

    public static void completarCampos(String text, WebElement e){
        e.sendKeys(text);
    }

    public static void tabular(WebElement e){
        e.sendKeys(Keys.TAB);
    }







}
