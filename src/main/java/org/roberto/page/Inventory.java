package org.roberto.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Inventory {

    WebDriver driver;

    public Inventory(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement menuButton() {
        // Obtengo el boton del menu
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    private WebElement btnLogout() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public void cerrarSesion() {
        menuButton().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        btnLogout().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }

    private List<WebElement> bntsProductos() {
        return driver.findElements(By.className("btn_inventory"));
    }


    // Obtenemos el elemento select para ordenar los productos
    private WebElement fieldSelectOrder() {
        return driver.findElement(By.className("product_sort_container"));
    }

    public void irAlCarrito() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    // para ordenar producto en el campo, se puede ingresar: name, price
    public void ordenarProductos(String field, Boolean desc) {
        WebElement webElselect = fieldSelectOrder();
        Select select = new Select(webElselect);
        if (field.equals("name")) {
            if (desc) {
                select.selectByValue("az");
            } else {
                select.selectByValue("za");
            }
        }

        if (field.equals("price")) {
            if (desc) {
                select.selectByValue("lohi");
            } else {
                select.selectByValue("hilo");
            }
        }
    }

    // Agregar los productos al carrito de compra
    public void agregarAlCarrito(Integer cantidad) {
        List<WebElement> btns = bntsProductos();
        if (cantidad > btns.size()) {
            cantidad = btns.size();
        }
        for (int i = 0; i < cantidad; i++) {
            btns.get(i).click();
        }
    }

}
