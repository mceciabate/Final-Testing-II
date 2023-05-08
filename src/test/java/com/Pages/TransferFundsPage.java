package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage extends BasePage {
    static WebElement titulo = driver.findElement(By.className("title"));

    static WebElement amount = driver.findElement(By.xpath("//*[@id=\"amount\"]"));

    static Select cuentaInicial = new Select(driver.findElement(By.id("fromAccountId")));

    static Select cuentaDestino = new Select(driver.findElement(By.id("toAccountId")));

    static WebElement botonTransferir = driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/form/div[2]/input"));

    public static WebElement capturarTitulo(){
        return titulo;
    }

    public static void ingresarMonto(String monto){
        amount.clear();
        amount.sendKeys(monto);
    }

    public static void seleccionarCuentas(int cuenta1, int cuenta2){
        cuentaInicial.selectByIndex(cuenta1);
        cuentaDestino.selectByIndex(cuenta2);
        botonTransferir.click();
    }




}
