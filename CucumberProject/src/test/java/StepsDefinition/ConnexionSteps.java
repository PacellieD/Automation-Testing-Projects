package StepsDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class ConnexionSteps {
    public WebDriver driver;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083/login";

    @Given("user is on login page")
    public void user_is_on_login_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }
    @When("user enter username")
    public void user_enter_username() {
        driver.findElement(By.name("username")).sendKeys(login);
    }
    @And("user enter password")
    public void user_enter_password() {
        driver.findElement(By.name("password")).sendKeys(pw);
    }
    @And("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.name("login")).click();
    }
    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        String dashboard= driver.findElement(By.id("loggedas")).getText();
        assertTrue(dashboard.contains("Connecté en tant que"));
    }
    @And("user click logout button")
    public void user_click_logout_button() {
            driver.findElement(By.xpath("//a[@class='logout']")).click();
            driver.quit();
    }


}
