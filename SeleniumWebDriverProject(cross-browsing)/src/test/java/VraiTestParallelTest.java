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

public class VraiTestParallelTest {

    public  WebDriver driver;
    public static Properties locators = new Properties();
    int nbHeure=5;
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";
    //SoftAssert monAssertion=new SoftAssert();

    void Login(String url, String pw, String login) {
        String link_Connexion = locators.getProperty("link_Connexion");
        String txt_UserName = locators.getProperty("txt_UserName");
        String txt_Password = locators.getProperty("txt_Password");
        String btn_Login = locators.getProperty("btn_Login");

        driver.get(url);
        driver.findElement(By.xpath(link_Connexion)).click();

        // Récupérer à nouveau les champs de nom d'utilisateur et de mot de passe avant d'interagir
        WebElement usernameField = driver.findElement(By.name(txt_UserName));
        usernameField.sendKeys(login);

        WebElement passwordField = driver.findElement(By.id(txt_Password));
        passwordField.sendKeys(pw);

        driver.findElement(By.name(btn_Login)).click();
        titreObtenu = driver.getTitle();
        titreAttendu = "Redmine G1274";
        assertEquals(titreObtenu, titreAttendu);
    }

    @org.junit.jupiter.api.Test
    void mettreNombreHeuresA8() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();

        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);

        driver.manage().window().maximize();
        System.out.println("Test parallele : Premier test START => "            + Thread.currentThread().getName());
        System.out.println("Execution du premier test");
        nbHeure=3;
        Login(url,pw,login);
    configurerMaxHeures();
        System.out.println("Test parallele : Premier test STOP => "            + Thread.currentThread().getName());
        logout();
        driver.quit();
    }

    @org.junit.jupiter.api.Test
    void mettreNombreHeuresA9() throws Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();

        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);

        driver.manage().window().maximize();
        System.out.println("Test parallele : Deuxieme test START => "            + Thread.currentThread().getName());
        System.out.println("Execution du deuxieme test");
        nbHeure=4;
        Login(url,pw,login);
        configurerMaxHeures();
        System.out.println("Test parallele : Deuxieme test STOP => "            + Thread.currentThread().getName());
        logout();
        driver.quit();

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
//        monAssertion.assertEquals(successMessage, "Mise à jour effectuée avec succès.");
//        monAssertion.assertAll();
    }
    void logout(){
        String lnk_Deconnexion=locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }

}


