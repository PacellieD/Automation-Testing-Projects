import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.junit.jupiter.api.BeforeAll;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class DataDrivenTestCSVJunit {

    public static WebDriver driver;
    public static Properties locators = new Properties();
    int nbHeure=5;
     String titreObtenu;
     String titreAttendu;
     String login = "6283658";
     String pw = "12345678";
     String url = "http://localhost:8083";
    SoftAssert monAssertion=new SoftAssert();

     void Login(String url,String pw,String login) {
        String link_Connexion = locators.getProperty("link_Connexion");
        String txt_UserName = locators.getProperty("txt_UserName");
        String txt_Password = locators.getProperty("txt_Password");
        String btn_Login = locators.getProperty("btn_Login");

        driver.get(url);
        driver.findElement(By.xpath(link_Connexion)).click();
        driver.findElement(By.name(txt_UserName)).sendKeys(login);
        driver.findElement(By.id(txt_Password)).sendKeys(pw);
        driver.findElement(By.name(btn_Login)).click();
        titreObtenu= driver.getTitle();
        titreAttendu="Redmine G1274";
        assertEquals(titreObtenu,titreAttendu);
    }

    void logout(){
        String lnk_Deconnexion=locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }

    void saisirTempsPasseNominal(String projet, String demande, String date, String nbHeures, String commentaire, String activite)
    {
        String lnk_Projets = locators.getProperty("lnk_Projets");
        String lnk_TempsPasse = locators.getProperty("lnk_TempsPasse");
        String lnk_SaisirTemps = locators.getProperty("lnk_SaisirTemps");
        String input_Projet = locators.getProperty("input_Projet");
        String input_Demande = locators.getProperty("input_Demande");
        String input_Date = locators.getProperty("input_Date");
        String input_Heures = locators.getProperty("input_Heures");
        String input_Commentaire = locators.getProperty("input_Commentaire");
        String input_Activite = locators.getProperty("input_Activite");
        String btn_Creer = locators.getProperty("btn_Creer");
        String lbl_Succes = locators.getProperty("lbl_Succes");
        driver.findElement(By.xpath(lnk_Projets)).click();
        driver.findElement(By.xpath(lnk_TempsPasse)).click();
        driver.findElement(By.xpath(lnk_SaisirTemps)).click();

        WebElement selectProjet = driver.findElement(By.xpath(input_Projet));
        new Select(selectProjet).selectByVisibleText(projet);

        driver.findElement(By.xpath(input_Demande)).sendKeys(demande);
        driver.findElement(By.xpath(input_Date)).sendKeys(date);
        driver.findElement(By.xpath(input_Heures)).sendKeys(nbHeures);
        driver.findElement(By.xpath(input_Commentaire)).sendKeys(commentaire);
        WebElement selectActivite = driver.findElement(By.xpath(input_Activite));
        new Select(selectActivite).selectByVisibleText(activite);
        driver.findElement(By.xpath(btn_Creer)).click();
        driver.findElement(By.xpath(lbl_Succes)).isDisplayed();
    }
    void SupprimerTempsPasse(String projet, String demande) {
        String lnk_Projets = locators.getProperty("lnk_Projets");
        String lnk_TempsPasse = locators.getProperty("lnk_TempsPasse");
        String lnk_Projet_Part1 = locators.getProperty("lnk_Projet_Part1");
        String lnk_Projet_Part2 = locators.getProperty("lnk_Projet_Part2");
        String input_Filtre = locators.getProperty("input_Filtre");
        String input_FiltreDemande = locators.getProperty("input_FiltreDemande");
        String lnk_Appliquer = locators.getProperty("lnk_Appliquer");
        String btn_Supprimer = locators.getProperty("btn_Supprimer");
        driver.findElement(By.xpath(lnk_Projets)).click();
        driver.findElement(By.xpath(lnk_Projet_Part1 + projet + lnk_Projet_Part2)).click();
        driver.findElement(By.xpath(lnk_TempsPasse)).click();
        WebElement selectFiltre = driver.findElement(By.xpath(input_Filtre));
        new Select(selectFiltre).selectByVisibleText("Demande");
        driver.findElement(By.xpath(input_FiltreDemande)).sendKeys(demande);
        driver.findElement(By.xpath(lnk_Appliquer)).click();
        driver.findElement(By.xpath(btn_Supprimer)).click();
        driver.switchTo().alert().accept();
    }
   @org.junit.jupiter.api.BeforeAll
     void SetUpBeforeClass() throws IOException {
       System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Login(url,pw,login);
        configurerMaxHeures("9");
    }
    @ParameterizedTest
    @CsvFileSource(resources = "dataSaisieTempsPasse.csv", delimiter = ',', numLinesToSkip = 1)
    void testSaisirTempsPasseNominal(String projet, String demande, String date, String nbHeures, String commentaire, String activite) {
        saisirTempsPasseNominal(projet, demande, date, nbHeures, commentaire, activite);
        SupprimerTempsPasse(projet, demande);
    }

     void configurerMaxHeures(String nbHeures) {
        String lnk_Administration = locators.getProperty("lnk_Administration");
        String lnk_Configuration = locators.getProperty("lnk_Configuration");
        String lnk_SuiviTemps = locators.getProperty("lnk_SuiviTemps");
        String txt_MaxHeures = locators.getProperty("txt_MaxHeures");
        String btn_SauvMaxHeures = locators.getProperty("btn_SauvMaxHeures");
        driver.findElement(By.xpath(lnk_Administration)).click();
        driver.findElement(By.xpath(lnk_Configuration)).click();
        driver.findElement(By.xpath(lnk_SuiviTemps)).click();
        driver.findElement(By.xpath(txt_MaxHeures)).clear();
        driver.findElement(By.xpath(txt_MaxHeures)).sendKeys(nbHeures);
        driver.findElement(By.xpath(btn_SauvMaxHeures)).click();
    }
    @org.junit.jupiter.api.AfterAll
   void tearDownAfterClass() {
         logout();
      if (driver != null) {
           driver.quit();
       }
   }
}

