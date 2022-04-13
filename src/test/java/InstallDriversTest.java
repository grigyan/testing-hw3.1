import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class InstallDriversTest {
    private WebDriver driver;
    private final String URL = "https://www.nike.com/";

    @BeforeSuite
    public void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("chrome driver is ready!");
    }

    @BeforeMethod
    public void driverGetUrl() {
        driver.get(URL);
    }


    @Test
    public void driverTestNikePage() {
        driver.findElement(By.cssSelector("#gen-nav-footer > nav > div > div > div:nth-child(2) > div > a.hf-language-menu-item.ncss-col-sm-12.ncss-col-md-4.ncss-col-lg-3.selected"))
                .click();
        driver.findElement(By.id("VisualSearchInput"))
                .sendKeys("Air Force 1", Keys.ENTER);

        String title = driver.getTitle();
        String airForce1Text = driver.findElement(By.xpath("//*[@id=\"Wall\"]/div/div[3]/header/div/h1/span[2]"))
                .getText();

        assertEquals(title, "Products. Nike.com");
        assertEquals(airForce1Text, "Air Force 1");
    }


    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}
