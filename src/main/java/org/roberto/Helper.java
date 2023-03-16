package org.roberto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helper {

    private final WebDriver driver;

    public Helper(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void esperar(Integer segundos) {
        try {
            Thread.sleep(segundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
