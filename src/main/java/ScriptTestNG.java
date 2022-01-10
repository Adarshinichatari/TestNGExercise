import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

public class ScriptTestNG extends ActionUI{
    //getProperties();
   // public ActionUI actionUI;

        @Test
    public void test1(){
            getProperties();
            WebElement search = driver.findElement(By.cssSelector(properties.getProperty("searchlocator")));
            webInputData(search,properties.getProperty("search"));
            click(driver.findElement(By.cssSelector(properties.getProperty("searchbutton"))));
            System.out.println();
        }
    @BeforeSuite
    public void before() throws IOException {
        getProperties();
        browser(properties.getProperty("url"));
    }
    @AfterSuite
    public void after(){
        driver.quit();
    }


}
