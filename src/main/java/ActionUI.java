import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ActionUI {
    WebDriver driver;
    Properties properties;
    public void getProperties() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\TestNGTask\\src\\main\\java\\properties\\userdata.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("userdata not found");
        }
    }

    public void browser(String url) {
        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void webInputData(WebElement element, String inputvalue) {
        if (isElementPresent(element)) {
            element.sendKeys(inputvalue);
        } else {
            System.out.println("Element is not found to give input data");
        }
    }

    public void click(WebElement element) {
        if (isElementPresent(element)) {
            element.click();
        } else {
            System.out.println("element not found to click");
        }
    }

    private boolean isElementPresent(WebElement element) {
        if (element.isDisplayed()) {
            return true;
        } else {
            System.out.println(" Element is not displayed");
            return false;
        }
    }

    public String title() {
        return driver.getTitle();
    }

    public boolean verifyTitle(String expectedTitle) {
        String obtainedTitle = title();
        if (obtainedTitle.equals(expectedTitle)) {
            return true;
        }
        else{
            return false;
        }
    }
}
