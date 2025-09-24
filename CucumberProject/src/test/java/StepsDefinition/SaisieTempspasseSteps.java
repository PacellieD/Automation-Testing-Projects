package StepsDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SaisieTempspasseSteps {
    public WebDriver driver;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083/login";


    @Given("user is logger")
    public void user_is_logger() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(pw);
        driver.findElement(By.name("login")).click();
    }
    @And("Max heures set to")
    public void max_heures_set_to() {
        String lnk_Administration ="lnk_Administration";
        String lnk_Configuration ="lnk_Configuration";
        String lnk_SuiviTemps ="lnk_SuiviTemps";
        String txt_MaxHeures ="txt_MaxHeures";
        String btn_SauvMaxHeures ="btn_SauvMaxHeures";
        int nbHeures=6;

        driver.findElement(By.xpath("//a[@class='administration' and @href='/admin']")).click();
        driver.findElement(By.xpath("//a[@class='icon icon-settings settings' and @href='/settings']")).click();
        driver.findElement(By.xpath("//a[@id='tab-timelog']")).click();
        driver.findElement(By.xpath("//input[@id='settings_timelog_max_hours_per_day']")).clear();
        driver.findElement(By.xpath("//input[@id='settings_timelog_max_hours_per_day']")).sendKeys("16");
        driver.findElement(By.xpath("//form[@action='/settings/edit?tab=timelog']//input[@type='submit']")).click();

    }
    @And("user is on temps passe form")
    public void user_is_on_temps_passe_form() {
        String lnk_Projets ="lnk_Projets";
        String lnk_TempsPasse = "lnk_TempsPasse";
        String lnk_SaisirTemps = "lnk_SaisirTemps";

        driver.findElement(By.xpath("//a[text()='Projets']")).click();
        driver.findElement(By.xpath("//a[@class='time-entries']")).click();
        driver.findElement(By.xpath("//a[@class='icon icon-time-add']")).click();

    }
    @When("user enters data and click creer button")
    public void user_enters_data_and_click_creer_button() {

//        String input_Projet = "//select[@id='time_entry_project_id']";
//        String input_Demande = "//input[@id='time_entry_issue_id']";
//        String input_Date = "//input[@id='time_entry_spent_on']";
//        String input_Heures = "//input[@id='time_entry_hours']";
//        String input_Commentaire = "//input[@id='time_entry_comments']";
//        String input_Activite = "//select[@id='time_entry_activity_id']";
//        String btn_Creer = "//input[@name='commit']";
//        String lbl_Succes = "//div[@id='flash_notice' and text()='Création effectuée avec succès.']";

        WebElement selectProjet = driver.findElement(By.xpath("//select[@id='time_entry_project_id']"));
        new Select(selectProjet).selectByVisibleText("projetpacellie");
        driver.findElement(By.xpath("//input[@id='time_entry_issue_id']")).sendKeys("958");
        driver.findElement(By.xpath("//input[@id='time_entry_spent_on']")).sendKeys("16/07/2024");
        driver.findElement(By.xpath("//input[@id='time_entry_hours']")).sendKeys("6");
        driver.findElement(By.xpath("//input[@id='time_entry_comments']")).sendKeys("testonsensemble");
        WebElement selectActivite = driver.findElement(By.xpath("//select[@id='time_entry_activity_id']"));
        new Select(selectActivite).selectByVisibleText("Analyse");
        driver.findElement(By.xpath("//input[@name='commit']")).click();


    }
    @When("user enters data and click creer button combinaison2")
    public void user_enters_data_and_click_creer_button_combinaison2() {

//        String input_Projet = "//select[@id='time_entry_project_id']";
//        String input_Demande = "//input[@id='time_entry_issue_id']";
//        String input_Date = "//input[@id='time_entry_spent_on']";
//        String input_Heures = "//input[@id='time_entry_hours']";
//        String input_Commentaire = "//input[@id='time_entry_comments']";
//        String input_Activite = "//select[@id='time_entry_activity_id']";
//        String btn_Creer = "//input[@name='commit']";
//        String lbl_Succes = "//div[@id='flash_notice' and text()='Création effectuée avec succès.']";

        WebElement selectProjet = driver.findElement(By.xpath("//select[@id='time_entry_project_id']"));
        new Select(selectProjet).selectByVisibleText("projetpacellie");
        driver.findElement(By.xpath("//input[@id='time_entry_issue_id']")).sendKeys("958");
        driver.findElement(By.xpath("//input[@id='time_entry_spent_on']")).sendKeys("17/07/2024");
        driver.findElement(By.xpath("//input[@id='time_entry_hours']")).sendKeys("4");
        driver.findElement(By.xpath("//input[@id='time_entry_comments']")).sendKeys("testonsensemble");
        WebElement selectActivite = driver.findElement(By.xpath("//select[@id='time_entry_activity_id']"));
        new Select(selectActivite).selectByVisibleText("Analyse");
        driver.findElement(By.xpath("//input[@name='commit']")).click();


    }

    @When("^user enters data (.*) and (.*) and (.*) and (.*) and (.*) and (.*) and click creer button")
    public void user_enters_data_and_click_creer_button(String Projet, String Demande, String Date, String NbHeures, String Commentaire, String Activite) {
        WebElement selectProjet = driver.findElement(By.xpath("//select[@id='time_entry_project_id']"));
        new Select(selectProjet).selectByVisibleText(Projet);
        driver.findElement(By.xpath("//input[@id='time_entry_issue_id']")).sendKeys(Demande);
        driver.findElement(By.xpath("//input[@id='time_entry_spent_on']")).sendKeys(Date);
        driver.findElement(By.xpath("//input[@id='time_entry_hours']")).sendKeys(NbHeures);
        driver.findElement(By.xpath("//input[@id='time_entry_comments']")).sendKeys(Commentaire);
        WebElement selectActivite = driver.findElement(By.xpath("//select[@id='time_entry_activity_id']"));
        new Select(selectActivite).selectByVisibleText(Activite);
        driver.findElement(By.xpath("//input[@name='commit']")).click();
    }
    @Then("succes message is displayed")
    public void succes_message_is_displayed() {
        driver.findElement(By.xpath("//div[@id='flash_notice' and text()='Création effectuée avec succès.']")).isDisplayed();
    }
    @And("temps passe is deleted")
    public void temps_passe_is_deleted() {
        String lnk_Projets ="//a[text()='Projets']";
        String lnk_TempsPasse ="//a[@class='time-entries']";
        String lnk_Projet_Part1 ="//a[@href='/projects/";
        String lnk_Projet_Part2 ="']";
        String input_Filtre ="//select[@id='add_filter_select']";
        String input_FiltreDemande ="//input[@id='values_issue_id_1']";
        String lnk_Appliquer ="//a[text()='Appliquer']";
        String btn_Supprimer ="//a[@data-method='delete']";

        driver.findElement(By.xpath(lnk_Projets)).click();
        driver.findElement(By.xpath(lnk_Projet_Part1 + "projetpacellie" + lnk_Projet_Part2)).click();
        driver.findElement(By.xpath(lnk_TempsPasse)).click();
        WebElement selectFiltre = driver.findElement(By.xpath(input_Filtre));
        new Select(selectFiltre).selectByVisibleText("Demande");
        driver.findElement(By.xpath(input_FiltreDemande)).sendKeys("958");
        driver.findElement(By.xpath(lnk_Appliquer)).click();
        driver.findElement(By.xpath(btn_Supprimer)).click();
        driver.switchTo().alert().accept();

    }
    @And("logout")
    public void logout() {
        String lnk_Deconnexion="//a[@class='logout']";
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }
}
