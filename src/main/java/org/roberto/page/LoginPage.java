package org.roberto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.roberto.Helper;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private final WebDriver driver;
    private final Helper helper;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
    }

    private WebElement emailField() {
        return driver.findElement(By.id("user-name"));
    }

    private WebElement passwordField() {
        return driver.findElement(By.id("password"));
    }

    private WebElement loginButton() {
        return driver.findElement(By.id("login-button"));
    }

    public void iniciarSesion(String email, String password) {
        helper.enterText(emailField(), email);
        helper.enterText(passwordField(), password);
        loginButton().click();
    }

    public Boolean inicioExitoso() {
        try {
            Thread.sleep(3); // Espera 10 segundos (10,000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement welcome;
        try {
            welcome = driver.findElement(By.className("app_logo"));
            if (welcome == null ) { return false; }
        } catch (NoSuchElementException e) {
            return false;
        }

        return welcome.getText().contains("Swag Labs");
    }
}
