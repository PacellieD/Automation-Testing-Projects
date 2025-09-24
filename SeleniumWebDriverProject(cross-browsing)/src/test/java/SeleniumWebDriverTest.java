import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.Random.class)

class SeleniumWebDriverTest {
    public static WebDriver driver;
    public static Properties locators = new Properties();
    int nbHeure=5;
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";
    SoftAssert monAssertion=new SoftAssert();

    @BeforeAll
    static void setUpBeforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();

        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);

        driver.manage().window().maximize();
    }

//    @AfterEach
//    void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

//@Test
//void redmine_Login() {
//    String link_Connexion = locators.getProperty("link_Connexion");
//    String txt_UserName = locators.getProperty("txt_UserName");
//    String txt_Password = locators.getProperty("txt_Password");
//    String btn_Login = locators.getProperty("btn_Login");
//    String login = "6283658";
//    String pw = "12345678";
//    String url = "http://localhost:8083";
//
//    driver.get(url);
//    driver.findElement(By.xpath(link_Connexion)).click();
//    driver.findElement(By.name(txt_UserName)).sendKeys(login);
//    driver.findElement(By.id(txt_Password)).sendKeys(pw);
//    driver.findElement(By.name(btn_Login)).click();
//    String titreObtenu= driver.getTitle();
//    String titreAttendu="Redmine G1274";
//    assertEquals(titreObtenu,titreAttendu);
//}
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
@org.junit.jupiter.api.Test
void mettreNombreHeuresA8() throws Exception{
    System.out.println("Test parallele : Premier test START => "            + Thread.currentThread().getName());
    System.out.println("Execution du premier test");
    Thread.sleep(300);
    nbHeure+=3;
    Login(url,pw,login);
    configurerMaxHeures();
    System.out.println("Test parallele : Premier test STOP => "            + Thread.currentThread().getName());
    logout();
}

//@RepeatedTest(4)
@org.junit.jupiter.api.Test
void mettreNombreHeuresA9() throws Exception{
    System.out.println("Test parallele : Deuxieme test START => "            + Thread.currentThread().getName());
    System.out.println("Execution du deuxieme test");
    Thread.sleep(300);
    nbHeure+=4;
    Login(url,pw,login);
    configurerMaxHeures();
    System.out.println("Test parallele : Deuxieme test STOP => "            + Thread.currentThread().getName());
    logout();

}
void configurerMaxHeures(){

    String lnk_Admin=locators.getProperty("link_Administration");
    String lnk_setting=locators.getProperty("link_Setting");
    String btn_time=locators.getProperty("btn_Time_tracking");
    String btn_save=locators.getProperty("btn_Save");
    String msg_succes=locators.getProperty("msg_succes");

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.findElement(By.xpath(lnk_Admin)).click();
    titreAttendu="Administration - Redmine G1274";
    titreObtenu= driver.getTitle();
    assertEquals(titreObtenu,titreAttendu);
    driver.findElement(By.xpath(lnk_setting)).click();
    titreAttendu="Configuration - Administration - Redmine G1274";
    titreObtenu= driver.getTitle();
    assertEquals(titreObtenu,titreAttendu);
    driver.findElement(By.id(btn_time)).click();
    driver.findElement(By.id("settings_timelog_max_hours_per_day")).clear();

    driver.findElement(By.id("settings_timelog_max_hours_per_day")).sendKeys(String.valueOf(nbHeure));
    driver.findElement(By.xpath(btn_save)).click();

    WebElement barreSucces= driver.findElement(By.id(msg_succes));
    String successMessage = barreSucces.getText();
    monAssertion.assertEquals(successMessage, "Mise à jour effectuée avec succès.");
    monAssertion.assertAll();
}
    void logout(){
        String lnk_Deconnexion=locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }
//    @Test
//    void TC1(){
//        Login(url,pw,login);
//        configurerMaxHeures();
//        logout();
//
//    }
}
