package org.roberto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.roberto.Helper;

public class Checkout {

    private WebDriver driver;
    private Helper helper;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper(driver);
    }

    private WebElement fieldName() {
        return driver.findElement(By.id("first-name"));
    }

    private WebElement fieldLastName() {
        return driver.findElement(By.id("last-name"));
    }

    private WebElement fieldCode() {
        return driver.findElement(By.id("postal-code"));
    }

    private WebElement btnContinuar() {
        return driver.findElement(By.id("continue"));
    }

    private WebElement btnFinish() {
        return driver.findElement(By.id("finish"));
    }

    public void registrarInfo(String name, String lastName, String code) {
        // Ingresando datos
        helper.enterText(fieldName(), name);
        helper.enterText(fieldLastName(), lastName);
        helper.enterText(fieldCode(), code);

        // continuamos
        btnContinuar().click();

    }

    // finalizamos el proceso de checkout con le paso 2
    public void checkoutTwo() {
        btnFinish().click();
    }

    public Boolean finalizado() {
        WebElement msg = driver.findElement(By.className("complete-header"));
        return msg.getText().equals("Thank you for your order!");
    }
}
