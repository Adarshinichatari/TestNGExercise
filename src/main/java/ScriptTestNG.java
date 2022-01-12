import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

import static java.lang.Float.*;
import static java.lang.Integer.parseInt;

public class ScriptTestNG extends ActionUI{

        @Test (priority = 1)
    public void search(){
            getProperties();
            WebElement search = driver.findElement(By.xpath(properties.getProperty("searchlocator")));
            webInputData(search,properties.getProperty("search"));
            click(driver.findElement(By.xpath(properties.getProperty("searchbutton"))));
        }
        @Test (priority = 2)
        public void productNamesAndSize(){
            List<WebElement> products = driver.findElements(By.xpath(properties.getProperty("productlistxpath")));
            for(int i=0;i<products.size();i++) {
                System.out.println(products.get(i).getText());
            }
            System.out.println("Total number of products = " + products.size());
        }
        @Test (priority = 0)
        public void titleOfPage(){
            System.out.println("TITLE =" + driver.getTitle());
        }
        @Test (priority = 3)
        public void display() {
            boolean Display = driver.findElement(By.cssSelector(properties.getProperty("productslocator"))).isDisplayed();
            System.out.println("Product displayed is product searched:"+Display);
            //input[value='dresses']
        }
            @Test (priority = 4)
            public void highAndLowPrices() {
                Map<Double, String> map=new HashMap<>();
               // driver.findElement(By.cssSelector("#search_query_top")).sendKeys("Dresses"+ Keys.ENTER);
                List<WebElement> list_of_products = driver.findElements(By.xpath(properties.getProperty("productlistxpath")));
                List<WebElement> list_of_products_price = driver.findElements(By.xpath(properties.getProperty("productpricexpath")));
                List<String> list = new ArrayList<>();
                for (WebElement a:list_of_products_price) {list.add(a.getText());}
                for(int i=0;i<list_of_products_price.size();i++) {
                    map.put(Double.valueOf(list.get(i).replaceAll("[$]","")),list_of_products.get(i).getText());
                    System.out.println(map);

                }
                Set<Double> allkeys = map.keySet();
                ArrayList<Double> product_prices = new ArrayList<>(allkeys);

                Collections.sort(product_prices);
                Double low_price = product_prices.get(0);
                Double highPrice = product_prices.get(product_prices.size()-1);
                System.out.println("Lowest product Name:" + map.get(low_price));
                System.out.println("Lowest price:" + low_price);
                System.out.println("High product Name:" + map.get(highPrice));
                System.out.println("High price:" + highPrice);

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
