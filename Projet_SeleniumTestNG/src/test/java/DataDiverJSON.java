import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.testng.AssertJUnit.assertTrue;

public class DataDiverJSON {

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
        assertEquals(titreObtenu, titreAttendu);
    }

    void logout() {
        String lnk_Deconnexion = locators.getProperty("lnk_Deconnexion");
        driver.findElement(By.xpath(lnk_Deconnexion)).click();
    }
    void CreationForum(String projet, String NomForum, String Description) {
        String lnk_Projet = locators.getProperty("lnk_Projet");
        String lnk_NomProjet_Part1 = locators.getProperty("lnk_NomProjet_Part1");
        String lnk_NomProjet_Part2 = locators.getProperty("lnk_NomProjet_Part2");
        String lnk_Configuration = locators.getProperty("lnk_Configuration");
        String lnk_Forums = locators.getProperty("lnk_Forums");
        String btn_NouveauForum = locators.getProperty("btn_NouveauForum");
        String txt_NomForum = locators.getProperty("txt_NomForum");
        String txt_Description = locators.getProperty("txt_Description");
        String btn_Creer = locators.getProperty("btn_Creer");

        driver.findElement(By.xpath(lnk_Projet)).click();
        driver.findElement(By.xpath(lnk_NomProjet_Part1 + projet + lnk_NomProjet_Part2)).click();
        driver.findElement(By.xpath(lnk_Configuration)).click();
        driver.findElement(By.xpath(lnk_Forums)).click();
        driver.findElement(By.xpath(btn_NouveauForum)).click();
        if (!NomForum.isEmpty()) {
            driver.findElement(By.xpath(txt_NomForum)).sendKeys(NomForum);
        }

        if (!Description.isEmpty()) {
            driver.findElement(By.xpath(txt_Description)).sendKeys(Description);
        }
        driver.findElement(By.xpath(btn_Creer)).click();
    }

    @BeforeClass
    void SetUpBeforeClass() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Python312\\Scripts\\chromedriver.exe");
        driver = new ChromeDriver();
        FileInputStream profile = new FileInputStream("src//test//java//Locators.properties");
        locators.load(profile);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Login(url, pw, login);
    }

    @DataProvider(name = "JSONDataProvider")
    public Iterator<Object[]> refDataProvider() {
        List<Object[]> data = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(new File("src/test/resources/data.json"));
            if (rootNode != null && rootNode.has("root")) {
                JsonNode rowsNode = rootNode.get("root").get("row");
                if (rowsNode != null && rowsNode.isArray()) {
                    for (JsonNode rowNode : rowsNode) {
                        String Projet = getTextNodeValue(rowNode, "Projet", "");
                        String NomForum = getTextNodeValue(rowNode, "NomForum", "");
                        String Description = getTextNodeValue(rowNode, "Description", "");
                        String Message = getTextNodeValue(rowNode, "Message", "");
                        data.add(new Object[]{Projet, NomForum, Description, Message});
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.iterator();
    }

    private String getTextNodeValue(JsonNode node, String fieldName, String defaultValue) {
        JsonNode fieldNode = node.get(fieldName);
        return (fieldNode != null) ? fieldNode.asText() : defaultValue;
    }

    @Test(dataProvider = "JSONDataProvider")
    void TestCreationForum(String projet, String NomForum, String Description, String Message) {
        CreationForum(projet, NomForum, Description);
        String message_error = locators.getProperty("message_error");
        String actualMessage = driver.findElement(By.xpath(message_error)).getText();
        if (NomForum.isEmpty() && Description.isEmpty()) {
            assertTrue("message", actualMessage.contains("Nom doit être renseigné(e)"));
            assertTrue("message", actualMessage.contains("Description doit être renseigné(e)"));
        } else if (NomForum.isEmpty()) {
            assertTrue("message", actualMessage.contains(Message));
        } else if (Description.isEmpty()) {
            assertTrue("message", actualMessage.contains(Message));
        }
    }
    @AfterClass
    void tearDownAfterClass() {
        logout();
        if (driver != null) {
            driver.quit();
        }
    }


}
