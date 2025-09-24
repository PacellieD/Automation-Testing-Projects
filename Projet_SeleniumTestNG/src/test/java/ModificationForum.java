import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModificationForum {
    public static WebDriver driver;
    public static Properties locators = new Properties();
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";
    SoftAssert softAssert = new SoftAssert();

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
    void testModificationForum() {
        String projet = "projetpacellie";
        String nomForum = "Forum1";
        String description = "Nouvelle description du forum modifie";

        ModificationForum(projet, nomForum, description);
        softAssert.assertAll();
    }

    void ModificationForum(String projet, String NomForum, String Description) {
        String lnk_Projet = locators.getProperty("lnk_Projet");
        String lnk_NomProjet_Part1 = locators.getProperty("lnk_NomProjet_Part1");
        String lnk_NomProjet_Part2 = locators.getProperty("lnk_NomProjet_Part2");
        String lnk_ModifierPart1 = locators.getProperty("lnk_ModifierPart1");
        String lnk_ModifierPart2 = locators.getProperty("lnk_ModifierPart2");
        String lnk_Configuration = locators.getProperty("lnk_Configuration");
        String lnk_Forums = locators.getProperty("lnk_Forums");
        String txt_NomForum = locators.getProperty("txt_NomForum");
        String txt_Description = locators.getProperty("txt_Description");
        String btn_Sauvegarder = locators.getProperty("btn_Sauvegarder");
        String MessageSuccesLocator = locators.getProperty("message_Succes");

        driver.findElement(By.xpath(lnk_Projet)).click();
        driver.findElement(By.xpath(lnk_NomProjet_Part1 + projet + lnk_NomProjet_Part2)).click();
        driver.findElement(By.xpath(lnk_Configuration)).click();
        driver.findElement(By.xpath(lnk_Forums)).click();

        try {
            driver.findElement(By.xpath(lnk_ModifierPart1 + NomForum + lnk_ModifierPart2)).click();
            driver.findElement(By.xpath(txt_NomForum)).clear();
            driver.findElement(By.xpath(txt_NomForum)).sendKeys(NomForum);
            driver.findElement(By.xpath(txt_Description)).clear();
            driver.findElement(By.xpath(txt_Description)).sendKeys(Description);
            driver.findElement(By.xpath(btn_Sauvegarder)).click();

            String MessageSucesObtenu = driver.findElement(By.xpath(MessageSuccesLocator)).getText();
            String MessageSuccesAttendu = "Mise à jour effectuée avec succès.";
            assertEquals(MessageSucesObtenu, MessageSuccesAttendu);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, "Le forum n'existe pas encore, il faut d'abord le Creer!", "Alerte", JOptionPane.ERROR_MESSAGE);
        }
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
