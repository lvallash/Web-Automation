package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;

    // Constructor
    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    public void typeUser(String user){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement userInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(LoginPage.userInput));
        userInputElement.sendKeys(user);
    }

    public void typePassword(String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement passInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(LoginPage.passInput));
        passInputElement.sendKeys(password);
    }

    public void login(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("btn")));
        loginButtonElement.click();
    }

    public void loginAndVerify(String user, String password) {
        typeUser(user);
        typePassword(password);
        login();

        // Agrega una pausa para permitir que la página se cargue completamente
        try {
            Thread.sleep(5000); // 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verifica el título
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
        System.out.println("Title: " + titleElement.getText());
    }



}
