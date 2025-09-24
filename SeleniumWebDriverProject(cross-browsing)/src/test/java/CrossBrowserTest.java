import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class CrossBrowserTest {
    public WebDriver driver;
    public static Properties locators = new Properties();
    int nbHeure = 5;
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";
    SoftAssert monAssertion = new SoftAssert();

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        if (browser.equalsIgnoreCase("Firefox")) {
           System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Python312\\Scripts\\geckodriver.exe");
           driver = new FirefoxDriver();    }
       else
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\Program Files\\Python312\\Scripts\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void Login(String url, String pw, String login) {
        String link_Connexion = locators.getProperty("link_Connexion");
        String txt_UserName = locators.getProperty("txt_UserName");
        String txt_Password = locators.getProperty("txt_Password");
        String btn_Login = locators.getProperty("btn_Login");

        driver.get(url);
        driver.findElement(By.xpath(link_Connexion)).click();
        driver.findElement(By.name(txt_UserName)).sendKeys(login);
        driver.findElement(By.id(txt_Password)).sendKeys(pw);
        driver.findElement(By.name(btn_Login)).click();

        titreObtenu = driver.getTitle();
        titreAttendu = "Redmine G1274";
        assertEquals(titreObtenu, titreAttendu);
    }

    @Test
    public void mettreNombreHeuresA8() throws Exception {
        System.out.println("Test parallele : Premier test START => " + Thread.currentThread().getName());
        Thread.sleep(300);
        nbHeure += 3;
        Login(url, pw, login);
        configurerMaxHeures();
        System.out.println("Test parallele : Premier test STOP => " + Thread.currentThread().getName());
        logout();
    }

    public void configurerMaxHeures() {
        String lnk_Admin = locators.getProperty("link_Administration");
        String lnk_setting = locators.getProperty("link_Setting");
        String btn_time = locators.getProperty("btn_Time_tracking");
        String btn_save = locators.getProperty("btn_Save");
        String msg_succes = locators.getProperty("msg_succes");

        driver.findElement(By.xpath(lnk_Admin)).click();
        titreAttendu = "Administration - Redmine G1274";
        titreObtenu = driver.getTitle();
        assertEquals(titreObtenu, titreAttendu);

        driver.findElement(By.xpath(lnk_setting)).click();
        titreAttendu = "Configuration - Administration - Redmine G1274";
        titreObtenu = driver.getTitle();
        assertEquals(titreObtenu, titreAttendu);

        driver.findElement(By.id(btn_time)).click();
        driver.findElement(By.id("settings_timelog_max_hours_per_day")).clear();
        driver.findElement(By.id("settings_timelog_max_hours_per_day")).sendKeys(String.valueOf(nbHeure));
        driver.findElement(By.xpath(btn_save)).click();

        WebElement barreSucces = driver.findElement(By.id(msg_succes));
        String successMessage = barreSucces.getText();
        monAssertion.assertEquals(successMessage, "Mise à jour effectuée avec succès.");
        monAssertion.assertAll();
    }

    public void logout() {
        String lnk_Deconnexion = locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }
}
