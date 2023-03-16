import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.roberto.Helper;
import org.roberto.page.Cart;
import org.roberto.page.Checkout;
import org.roberto.page.Inventory;
import org.roberto.page.LoginPage;

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

        driver.get("https://www.saucedemo.com/");

        // Iniciamos sesión en el sistema
        login.iniciarSesion("standard_user", "secret_sauce");

        // verificamos que el login sea existo
        Boolean ok = login.inicioExitoso();
        Assertions.assertTrue(ok);

        // Inventario
        Inventory inventory = new Inventory(driver);
        inventory.ordenarProductos("name", true);

        // agregamos la cantidad de producto al carrito de compra
        inventory.agregarAlCarrito(3);

        inventory.irAlCarrito();

        helper.esperar(3);

        cart.irCheckout();

        helper.esperar(3);

        checkout.registrarInfo("Roberto", "Suárez", "123432");
        checkout.checkoutTwo();

        helper.esperar(3);

        Boolean finalizado = checkout.finalizado();
        Assertions.assertTrue(finalizado);

        helper.esperar(3);

        inventory.cerrarSesion();

        driver.quit();
    }
}
