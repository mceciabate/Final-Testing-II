package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends BasePage {

//    static WebElement formulario = driver.findElement(By.className("form-registro"));
    static WebElement nombre = driver.findElement(By.id("firstName"));
    static WebElement apellido = driver.findElement(By.id("lastName"));
    static WebElement email = driver.findElement(By.id("email"));
    static WebElement password = driver.findElement(By.id("password"));
    static WebElement rePassword = driver.findElement(By.id("repassword"));

    static WebElement botonEnviar =driver.findElement(By.xpath("//*[@id=\"root\"]/main/div/form/button"));


    public static List<WebElement> obtenerElementosFormulario(){
        List<WebElement> lista = new ArrayList<>();
        lista.add(nombre);
        lista.add(apellido);
        lista.add(email);
        lista.add(password);
        lista.add(rePassword);
        return lista;
    }


    public static void limpiarCampos(List<WebElement> lista){
        for (WebElement webElement : lista) {
            webElement.clear();
        }

    }

    public static void completarCampos(String name, String lastName, String mail, String contrasenia, String recontrasenia ){
        nombre.sendKeys(name);
        apellido.sendKeys(lastName);
        email.sendKeys(mail);
        password.sendKeys(contrasenia);
        rePassword.sendKeys(recontrasenia);
    }

    public static void enviarForm(){

        botonEnviar.click();
    }









}
