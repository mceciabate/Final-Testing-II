package com.Tests;


import com.ExtentReport.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BackendParaBankTest {

    static ExtentSparkReporter spark = new ExtentSparkReporter("src/test/java/com/ExtentReport/Reports/BackendRegressionSuiteTest.html");
    static ExtentReports report;
    ExtentTest test;
    private String username = "naruto";
    private String password = "naruto1234";
    private int cuentaTipoSavings = 1;
    private int amount = 50;
    private String baseURL = "https://parabank.parasoft.com/parabank/register.htm";
    private String accountURL = "https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount";
    private String overviewURL = "http://parabank.parasoft.com/parabank/services/bank/customers/{costumerId}/accounts";
    private String baseLoginURL = "http://parabank.parasoft.com/parabank/services/bank/login";
    private String baseTransferURL = "https://parabank.parasoft.com/parabank/services_proxy/bank/transfer";
    private String transactionsURL = "https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/{accountId}/transactions/month/{month}/type/{type}";


    public Integer obtenerCostumerId(){
        Response response =
        given().accept(ContentType.JSON)
                .pathParam("username", username)
                .pathParam("password", password)
                .when()
                .get(baseLoginURL+"/{username}/{password}");
        Integer costumerId = response.jsonPath().getInt("id");
        return costumerId;
    }

    public Integer obtenerCuentaBase(){
        Integer costumerId = obtenerCostumerId();
        Response response =
        given().accept(ContentType.JSON)
                .auth().basic(username, password)
                .pathParam("costumerId", costumerId)
                .when()
                .get(overviewURL);
        Integer cuentaBase = response.jsonPath().getInt("[0].id");
        return  cuentaBase;
    }


    public Integer obtenerSegundaCuenta(){
        Integer costumerId = obtenerCostumerId();
        Response response =
                given().accept(ContentType.JSON)
                        .auth().basic(username, password)
                        .pathParam("costumerId", costumerId)
                        .when()
                        .get(overviewURL);
        Integer cuentaAuxiliar = response.jsonPath().getInt("[1].id");
        return  cuentaAuxiliar;

    }

    @BeforeAll
    public void initReport(){
        report = ExtentFactory.getInstance();
        report.attachReporter(spark);
    }



    @Test
    @Tag("regression")
    public void AgetHome(){
        test = report.createTest("Test Get Home");
        test.log(Status.INFO, "Inicia el Test");
        given()
                .when()
                .get(baseURL)
                .then()
                .assertThat()
                .statusCode(200);
        test.log(Status.PASS, "Se obtiene respuesta exitosa");
    }

    @Test
    @Tag("regression")
    public void BiniciarSesion(){
        test = report.createTest("Tes de inicio de Sesión desde Backend");
        test.log(Status.INFO, "Inicia el Test");
        given()
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .pathParam("password", password)
                .when()
                .get(baseLoginURL+"/{username}/{password}")
                .then().assertThat().statusCode(200).and().log().body();
        test.log(Status.PASS, "Se obtiene respuesta exitosa");
    }

    @Test
    @Tag("regression")
    public void CcreateAccount(){
        test = report.createTest("Test de Crear Nueva Cuenta");
        test.log(Status.INFO, "Inicia el Test");
        Integer costumerId = obtenerCostumerId();
        Integer cuentaBase = obtenerCuentaBase();
        test.log(Status.PASS, "Se obtienen el id del usuario: " + costumerId);
        test.log(Status.PASS, "Se obtiene el numero de cuenta base: " + cuentaBase);
        given()
                .accept(ContentType.JSON)
                .with()
                .queryParam("customerId", costumerId)
                .queryParam("newAccountType", cuentaTipoSavings)
                .queryParam("fromAccountId", cuentaBase)
                .auth().basic(username, password)
                .when()
                .post(accountURL)
                .then().assertThat().statusCode(200)
                .and().log().body();
        test.log(Status.PASS, "Se obtiene respuesta exitosa");

    }

    @Test
    @Tag("regression")
    public void DresumenCuenta(){
        test = report.createTest("Test para obtener resumen de cuentas");
        test.log(Status.INFO, "Inicia el Test");
        Integer costumerId = obtenerCostumerId();
        test.log(Status.PASS, "Se obtienen el id del usuario: " + costumerId);
        given()
                .accept(ContentType.JSON)
                .auth().basic(username, password)
                .pathParam("costumerId", costumerId)
                .when()
                .get(overviewURL)
                .then()
                .assertThat().statusCode(200).log().all();
        test.log(Status.PASS, "Se obtiene respuesta exitosa");
    }

    @Test
    @Tag("regression")
    public void Etransferir(){
        test = report.createTest("Test de Transferencias");
        test.log(Status.INFO, "Inicia el Test");
        Integer cuentaBase = obtenerCuentaBase();
        Integer cuentaAuxiliar = obtenerSegundaCuenta();
        test.log(Status.PASS, "Se obtiene el numero de cuenta base: " + cuentaBase);
        test.log(Status.PASS, "Se obtiene el numero de cuenta auxiliar: " + cuentaAuxiliar);
        given()
                .accept(ContentType.JSON)
                .with()
                .queryParam("fromAccountId", cuentaBase)
                .queryParam("toAccountId", cuentaAuxiliar)
                .queryParam("amount", amount)
                .auth().basic(username, password)
                .when()
                .post(baseTransferURL)
                .then().assertThat().statusCode(200)
                .and().log().body();
        test.log(Status.PASS, "Se obtiene respuesta exitosa");
    }

    @Test
    @Tag("regression")
    public void FactividadCuenta(){
        test = report.createTest("Test de Actividad de Cuenta");
        test.log(Status.INFO, "Inicia el Test");
        Integer cuentaBase = obtenerCuentaBase();
        test.log(Status.PASS, "Se obtiene el numero de cuenta base: " + cuentaBase);
        given()
                .accept(ContentType.JSON)
                .pathParam("accountId", cuentaBase)
                .pathParam("month", "All")
                .pathParam("type", "All")
                .auth().basic(username, password)
                .when()
                .get(transactionsURL)
                .then().assertThat().statusCode(200)
                .and().log().all();
        test.log(Status.PASS, "Se obtiene respuesta exitosa");


    }

    @AfterAll
    public void cerrarReporte(){

        report.flush();
    }





}
