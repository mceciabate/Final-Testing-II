package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CustumerLoginPage extends BasePage {



    static WebElement userName = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[1]/input"));

    static WebElement password = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[2]/input"));

    static WebElement botonIngresar = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input"));



    public static List<WebElement> obtenerElementosLogin(){
        List<WebElement> lista = new ArrayList<>();
        lista.add(userName);
        lista.add(password);
        return lista;
    }

    public static void limpiarCampos(List<WebElement> lista) {
        for (WebElement webElement : lista) {
            webElement.clear();
        }
    }

    public static void ingresar(String nombreUsuario, String contrasenia){
        userName.sendKeys(nombreUsuario);
        password.sendKeys(contrasenia);
        botonIngresar.click();

    }




}
