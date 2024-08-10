package com.nttdata.stepsdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.time.Duration;

public class StoreSteps {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("estoy en la página de la tienda")
    public void estoy_en_la_página_de_la_tienda() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://qalab.bensg.com/store");
    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String username, String password) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("loginButton"));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @When("navego a la categoría {string} y subcategoría {string}")
    public void navego_a_la_categoria_y_subcategoria(String categoria, String subcategoria) {
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(categoria)));
        WebElement subcategoryLink = driver.findElement(By.linkText(subcategoria));

        categoryLink.click();
        subcategoryLink.click();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agrego_unidades_del_primer_producto_al_carrito(int unidades) {
        WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product")));
        WebElement addToCartButton = firstProduct.findElement(By.cssSelector(".add-to-cart"));

        for (int i = 0; i < unidades; i++) {
            addToCartButton.click();
        }
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmacion_del_producto_agregado() {
        WebElement confirmationPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmationPopup")));
        Assert.assertTrue(confirmationPopup.isDisplayed());
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        WebElement totalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("totalAmount")));
        // Validar el monto total aquí, por ejemplo:
        String expectedAmount = "$40.00";
        Assert.assertEquals(expectedAmount, totalAmount.getText());
    }

    @When("finalizo la compra")
    public void finalizo_la_compra() {
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();
    }

    @Then("valido el título de la página del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals("Carrito de Compras", pageTitle);
    }

    @And("vuelvo a validar el cálculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        WebElement totalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartTotal")));
        // Validar nuevamente el monto total aquí
        String expectedAmount = "$40.00";
        Assert.assertEquals(expectedAmount, totalAmount.getText());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
