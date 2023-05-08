package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewAccountPage extends BasePage {

    static WebElement checkboxTipoCuenta = driver.findElement(By.xpath("//*[@id=\"type\"]"));

    static WebElement cuentaSavings = driver.findElement(By.xpath("//*[@id=\"type\"]/option[2]"));

    static WebElement botonCrearCuenta = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div/input"));

    public static void crearCuentaSavings(){
        checkboxTipoCuenta.click();
        cuentaSavings.click();
        botonCrearCuenta.click();
    }



}
