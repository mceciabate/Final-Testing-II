package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.plaf.PanelUI;

public class LoggedUserMenu extends BasePage {

   static WebElement crearCuenta = driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a"));

   static WebElement transferencias = driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[3]/a"));

   static WebElement balanceCuentas = driver.findElement(By.xpath("//*[@id=\"leftPanel\"]/ul/li[2]/a"));


   public static void irAAbrirNuevaCuenta(){
      crearCuenta.click();
   }

   public static void irATransferencias(){
      transferencias.click();
   }

   public static void ingresarAActividadCuentas(){
      balanceCuentas.click();
   }



}
