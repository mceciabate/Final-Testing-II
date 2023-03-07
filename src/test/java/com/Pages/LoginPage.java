package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

  //  static WebElement botonIngresar = driver.findElement(By.linkText("Iniciar sesión"));
    static WebElement mailBox = driver.findElement(By.id("email"));
    static WebElement passwordBox = driver.findElement(By.id("password"));
    static WebElement botonBuscar = driver.findElement(By.xpath("/html/body/div/main/div/form/button"));
    static  WebElement mensajeAlerta = driver.findElement(By.className("form-login"));

    public static  void ingresarMailYContraseña (String mail, String password) throws InterruptedException{
//       botonIngresar.click();
        Thread.sleep(3000);
        mailBox.clear();
        mailBox.sendKeys(mail);
        passwordBox.clear();
        passwordBox.sendKeys(password);
        Thread.sleep(1000);
    }

    public static void login() throws InterruptedException {
        botonBuscar.click();
        Thread.sleep(1000);
    }

    public static String obtenerMensaje() throws InterruptedException {
        Thread.sleep(3000);
        String frase = mensajeAlerta.getText();
        return frase;
    }



}
