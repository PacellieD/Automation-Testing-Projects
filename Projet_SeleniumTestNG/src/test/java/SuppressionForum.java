import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuppressionForum {
    public static WebDriver driver;
    public static Properties locators = new Properties();
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";

    void Login(String url, String pw, String login) {
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
        assertEquals(titreAttendu, titreObtenu);
    }
    void logout() {
        String lnk_Deconnexion = locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }
    @Test
    void testSupressionForum() {
        String projet = "projetpacellie";
        String nomForum = "Forum2";
        SupressionForum(projet, nomForum);
    }
    void SupressionForum(String projet, String NomForum) {
        String lnk_Projet = locators.getProperty("lnk_Projet");
        String lnk_NomProjet_Part1 = locators.getProperty("lnk_NomProjet_Part1");
        String lnk_NomProjet_Part2 = locators.getProperty("lnk_NomProjet_Part2");
        String lnk_SupprimerPart1 = locators.getProperty("lnk_SupprimerPart1");
        String lnk_SupprimerPart2 = locators.getProperty("lnk_SupprimerPart2");
        String lnk_Configuration = locators.getProperty("lnk_Configuration");
        String lnk_Forums = locators.getProperty("lnk_Forums");
        String MessageSuccesLocator=locators.getProperty("message_Succes");

        driver.findElement(By.xpath(lnk_Projet)).click();
        driver.findElement(By.xpath(lnk_NomProjet_Part1 + projet + lnk_NomProjet_Part2)).click();
        driver.findElement(By.xpath(lnk_Configuration)).click();
        driver.findElement(By.xpath(lnk_Forums)).click();
        driver.findElement(By.xpath(lnk_SupprimerPart1 + NomForum + lnk_SupprimerPart2)).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String MessageSucesObtenu=driver.findElement(By.xpath(MessageSuccesLocator)).getText();
        String MessageSuccesAttendu="Suppression effectuée avec succès.";
        assertEquals(MessageSucesObtenu,MessageSuccesAttendu);
    }

    @BeforeClass
    void SetUpBeforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        FileInputStream profile = new FileInputStream("src/test/java/Locators.properties");
        locators.load(profile);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Login(url, pw, login);
    }

    @AfterClass
    void tearDownAfterClass() {
        logout();
        if (driver != null) {
            driver.quit();
        }
    }
}
