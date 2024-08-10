package com.nttdata.stepsdefinitions;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class StoreSteps {
    private WebDriver driver;

    @Given("estoy en la página de la tienda")
    public void estoy_en_la_página_de_la_tienda() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qalab.bensg.com/store");
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginButton")).click();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
        driver.findElement(By.linkText(categoria)).click();
        driver.findElement(By.linkText(subcategoria)).click();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int unidades) {
        WebElement firstProduct = driver.findElement(By.cssSelector(".product"));
        for (int i = 0; i < unidades; i++) {
            firstProduct.findElement(By.cssSelector(".add-to-cart")).click();
        }
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmación_del_producto_agregado() {
        WebElement confirmationPopup = driver.findElement(By.id("confirmationPopup"));
        Assert.assertTrue(confirmationPopup.isDisplayed());
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        WebElement totalAmount = driver.findElement(By.id("totalAmount"));
        // Validar el monto total aquí, por ejemplo:
        String expectedAmount = "$40.00";
        Assert.assertEquals(expectedAmount, totalAmount.getText());
    }

    @When("finalizo la compra")
    public void finalizo_la_compra() {
        driver.findElement(By.id("checkout")).click();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Carrito de Compras", pageTitle);
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        WebElement totalAmount = driver.findElement(By.id("cartTotal"));
        // Validar nuevamente el monto total aquí
        String expectedAmount = "$40.00";
        Assert.assertEquals(expectedAmount, totalAmount.getText());
    }
}

