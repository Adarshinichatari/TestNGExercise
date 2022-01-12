import org.openqa.selenium.By;
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

        @Test
    public void test1(){
            getProperties();
            WebElement search = driver.findElement(By.cssSelector(properties.getProperty("searchlocator")));
            webInputData(search,properties.getProperty("search"));
            click(driver.findElement(By.cssSelector(properties.getProperty("searchbutton"))));
           //System.out.println();
        }
        @Test
        public void test2(){
            List<WebElement> products = driver.findElements(By.cssSelector(properties.getProperty("productslocator")));
            for(int i=0;i<products.size();i++) {
                System.out.println(products.get(i).getText());
            }
        }
        @Test
        public void test3(){
            System.out.println("TITLE =" + driver.getTitle());
        }
        @Test
        public void test4() {
            boolean Display = driver.findElement(By.cssSelector(properties.getProperty("productslocator"))).isDisplayed();
            System.out.println("Product displayed is product searched:"+Display);
            //input[value='dresses']
        }
            @Test
            public void test5() {
                List<WebElement> list_of_products = driver.findElements(By.cssSelector(properties.getProperty("productslocator")));
                List<WebElement> list_of_products_price = driver.findElements(By.cssSelector(".price.product-price"));

                //Use of HashMaop to store Products and Their prices(after conversion to Integer)
                String product_name;
                String product_price;
                float int_product_price;
                HashMap<Float, String> map_final_products = new HashMap<Float,String>();
                for(int i=0;i<list_of_products.size();i++) {
                    product_name = list_of_products.get(i).getText();//Iterate and fetch product name
                    product_price = list_of_products_price.get(i).getText();//Iterate and fetch product price
                    product_price = product_price.replaceAll("[^0-9]", " ");//Replace anything wil space other than numbers
                   int_product_price = Integer.parseInt(product_price);//Convert to Integer
                   map_final_products.put(int_product_price,product_name);//Add product and price in HashMap
                }
                Set<Float> allkeys = map_final_products.keySet();
                ArrayList<Float> array_list_values_product_prices = new ArrayList<Float>(allkeys);
                Collections.sort(array_list_values_product_prices);
                float low_price = array_list_values_product_prices.get(0);

               Reporter.log("Low Product Price is: " + low_price + " Product name is: " + map_final_products.get(low_price),true);


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
