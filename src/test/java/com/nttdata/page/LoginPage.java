package com.nttdata.page;

import org.openqa.selenium.By;

public class LoginPage {

    //Localizadores de elementos
    public static By userInput = By.xpath("//input[@name='email']");
    public static By passInput = By.xpath("//input[@name='password']");
    public static By loginButton = By.xpath("//button[contains(text(), 'SIGN IN')]");

}
