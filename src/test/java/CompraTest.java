import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.roberto.Helper;
import org.roberto.Parametros;
import org.roberto.page.Cart;
import org.roberto.page.Checkout;
import org.roberto.page.Inventory;
import org.roberto.page.LoginPage;

import java.io.IOException;

public class CompraTest {

    @Test
    public void login() {
        WebDriverManager.edgedriver().setup();
        // creamos la instancia del driver
        WebDriver driver = new EdgeDriver();

        driver.get("https://www.saucedemo.com/");

        // Iniciamos sesión en el sistema
        LoginPage login = new LoginPage(driver);
        login.iniciarSesion("standard_user", "secret_sauce");

        // verificamos que el login sea existo
        Boolean respueta = login.inicioExitoso();
        Assertions.assertTrue(respueta);

        driver.quit();
    }

    @Test
    public void procesoCompra() {
        WebDriverManager.edgedriver().setup();
        // creamos la instancia del driver
        WebDriver driver = new EdgeDriver();
        // clases que controlan las paginas
        LoginPage login = new LoginPage(driver);
        Helper helper = new Helper(driver);
        Cart cart = new Cart(driver);
        Checkout checkout = new Checkout(driver);

        String usuario;
        String password;
        String ordenadoPor;
        Boolean desc;
        Integer cantidad;
        String nombre;
        String apellido;
        String codigo;


        try {
            // hoja de Excel con parametros
            Parametros parametros = new Parametros();
            parametros.loadData();
            System.out.println("Usuario: " + parametros.getUser());
            // obtenemos valores del excel
            usuario = parametros.getUser();
            password = parametros.getPassword();

            ordenadoPor = parametros.getOrdenadoPor();
            desc = parametros.getDesc();

            cantidad = parametros.getCantidadProducto();
            nombre = parametros.getNombre();
            apellido = parametros.getApellido();
            codigo = parametros.getCodigo();


            parametros.cerrar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.get("https://www.saucedemo.com/");

        // Iniciamos sesión en el sistema
        login.iniciarSesion(usuario, password);

        // verificamos que el login sea existo
        Boolean ok = login.inicioExitoso();
        Assertions.assertTrue(ok);

        // Inventario
        Inventory inventory = new Inventory(driver);
        inventory.ordenarProductos(ordenadoPor, desc);

        // agregamos la cantidad de producto al carrito de compra
        inventory.agregarAlCarrito(cantidad);

        inventory.irAlCarrito();


        cart.irCheckout();


        checkout.registrarInfo(nombre, apellido, codigo);
        checkout.checkoutTwo();

        helper.esperar(3);

        Boolean finalizado = checkout.finalizado();
        Assertions.assertTrue(finalizado);

        helper.esperar(3);

        inventory.cerrarSesion();

        driver.quit();
    }
}
