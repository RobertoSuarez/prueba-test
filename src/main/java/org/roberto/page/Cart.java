package org.roberto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {

    WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement btnCheckout() {
        return driver.findElement(By.id("checkout"));
    }

    public void irCheckout() {
        btnCheckout().click();
    }
}
