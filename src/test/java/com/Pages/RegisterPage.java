package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends BasePage {

    static WebElement name = driver.findElement(By.id("customer.firstName"));

    static WebElement lastName = driver.findElement(By.id("customer.lastName"));

    static WebElement adress = driver.findElement(By.id("customer.address.street"));

    static WebElement city = driver.findElement(By.id("customer.address.city"));

    static WebElement state = driver.findElement(By.id("customer.address.state"));

    static WebElement zipCode = driver.findElement(By.id("customer.address.zipCode"));

    static WebElement phone = driver.findElement(By.id("customer.phoneNumber"));

    static WebElement ssnNumber = driver.findElement(By.id("customer.ssn"));

    static WebElement userName = driver.findElement(By.id("customer.username"));

    static WebElement password = driver.findElement(By.id("customer.password"));

    static WebElement repassword = driver.findElement(By.id("repeatedPassword"));

    static WebElement buttomSendForm = driver.findElement(By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input"));



    public static List<WebElement> obtenerElementosFormulario(){
        List<WebElement> lista = new ArrayList<>();
        lista.add(name);
        lista.add(lastName);
        lista.add(adress);
        lista.add(city);
        lista.add(state);
        lista.add(zipCode);
        lista.add(phone);
        lista.add(ssnNumber);
        lista.add(userName);
        lista.add(password);
        lista.add(repassword);

        return lista;
    }

    public static void limpiarCampos(List<WebElement> lista) {
        for (WebElement webElement : lista) {
            webElement.clear();
        }
    }

    public static void completarCampos(String nombre, String apellido, String direccion, String ciudad, String estado, String codigoPostal, String telefono, String numSegSocial, String nombreUsuario, String contrasenia, String reContrasenia ) {
        name.sendKeys(nombre);
        lastName.sendKeys(apellido);
        adress.sendKeys(direccion);
        city.sendKeys(ciudad);
        state.sendKeys(estado);
        zipCode.sendKeys(codigoPostal);
        phone.sendKeys(telefono);
        ssnNumber.sendKeys(numSegSocial);
        userName.sendKeys(nombreUsuario);
        password.sendKeys(contrasenia);
        repassword.sendKeys(reContrasenia);
    }


    public static void enviarForm(){
        buttomSendForm.click();
    }





}
