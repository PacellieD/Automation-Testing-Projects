import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataDriverXML {
    public static WebDriver driver;
    public static Properties locators = new Properties();
    String titreObtenu;
    String titreAttendu;
    String login = "6283658";
    String pw = "12345678";
    String url = "http://localhost:8083";
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
    void CreationForum(String projet, String NomForum, String Description){
        String lnk_Projet=locators.getProperty("lnk_Projet");
        String lnk_NomProjet_Part1 = locators.getProperty("lnk_NomProjet_Part1");
        String lnk_NomProjet_Part2 = locators.getProperty("lnk_NomProjet_Part2");
        String lnk_Configuration=locators.getProperty("lnk_Configuration");
        String lnk_Forums=locators.getProperty("lnk_Forums");
        String btn_NouveauForum=locators.getProperty("btn_NouveauForum");
        String txt_NomForum=locators.getProperty("txt_NomForum");
        String txt_Description=locators.getProperty("txt_Description");
        String btn_Creer=locators.getProperty("btn_Creer");
        String messageSuccesLocator = locators.getProperty("message_Succes");
        String messageSuccesAttendu="Création effectuée avec succès.";

        driver.findElement(By.xpath(lnk_Projet)).click();
        driver.findElement(By.xpath(lnk_NomProjet_Part1 + projet + lnk_NomProjet_Part2)).click();
        driver.findElement(By.xpath(lnk_Configuration)).click();
        driver.findElement(By.xpath(lnk_Forums)).click();
        driver.findElement(By.xpath(btn_NouveauForum)).click();
        driver.findElement(By.xpath(txt_NomForum)).sendKeys(NomForum);
        driver.findElement(By.xpath(txt_Description)).sendKeys(Description);
        driver.findElement(By.xpath(btn_Creer)).click();
        String messageSuccesObtenu= driver.findElement(By.xpath(messageSuccesLocator)).getText();
        assertEquals(messageSuccesObtenu,messageSuccesAttendu);
    }

  @BeforeClass
    void SetUpBeforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Login(url,pw,login);
    }
    @DataProvider(name = "XMLDataProvider")
    public Iterator<Object[]> preparerDataProvider() {
        List<Object[]> data = new ArrayList<>();
        try {
            File inputFile = new File("src/test/resources/data.xml");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element rootElement = document.getRootElement();
            List<Element> rows = rootElement.getChildren("row");
            for (Element row : rows) {
                String Projet = row.getChildText("Projet");
                String NomForum = row.getChildText("NomForum");
                String Description = row.getChildText("Description");
                data.add(new Object[]{Projet, NomForum, Description});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.iterator();
    }

    @Test(dataProvider ="XMLDataProvider" )
    void TestCreationForum(String projet, String NomForum, String Description) {
        CreationForum(projet, NomForum, Description);
       }

@AfterClass
    void tearDownAfterClass() {
        logout();
        if (driver != null) {
            driver.quit();
        }
    }
}
