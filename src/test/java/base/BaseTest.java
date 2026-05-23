package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;
import utils.WaitHelper;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader configReader;
    protected WaitHelper waitHelper;

    @org.testng.annotations.BeforeMethod
    public void setUp() {
        configReader = new ConfigReader();
        String browser = configReader.getProperty("browser");
        String baseUrl = configReader.getProperty("baseUrl");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        waitHelper = new WaitHelper(driver);
        driver.get(baseUrl);
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
