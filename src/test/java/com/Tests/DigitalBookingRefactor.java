package com.Tests;
import com.Pages.SearchPage;
import org.junit.jupiter.api.Test;


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
}
