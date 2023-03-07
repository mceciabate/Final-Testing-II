package com.Tests;
import com.Pages.LoginPage;
import com.Pages.SearchPage;
import org.junit.jupiter.api.Test;


import static com.Base.BasePage.setup;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DigitalBookingRefactor {

    @Test
    public void testSearch() throws InterruptedException {
        SearchPage.setup();
        SearchPage.url("http://testing.ctd.academy/");

        SearchPage.escribirCampoBusqueda("punta del este");
        SearchPage.clickBuscar();

        assertTrue(SearchPage.obtenerTexto().contains("Villa Kantounes Studio Ostria"));

        SearchPage.salir();
    }

    @Test
    public void loginNegativo() throws InterruptedException{
        LoginPage.setup();
        LoginPage.url("http://testing.ctd.academy/login/");
        LoginPage.ingresarMailYContraseña("unmail@elmail.com", "123456");
        LoginPage.login();
        assertTrue(LoginPage.obtenerMensaje().contains("Sus credenciales son inválidas. Por favor, vuelva a intentarlo"));
        LoginPage.salir();
    }
}
