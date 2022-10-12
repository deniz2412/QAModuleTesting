package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Test()
public class TaskTwoTest {
    public String testURL ="https://www.saucedemo.com/";
    public WebDriver driver;

    @BeforeTest
    public void initialSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\denizh\\Documents\\TestEnv\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get(testURL);
    }

    @AfterTest
    public void afterRun(){
        driver.quit();
    }

    @Test
    public void TestCase2() {
        driver.get(testURL);
        Assert.assertEquals("Swag Labs", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //Pass through the login
        //List of 3 elements first element is username field, second is password, third is login button
        List<WebElement> login = new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElements(By.cssSelector("input")));
        login.get(0).sendKeys("standard_user");
        login.get(1).sendKeys("secret_sauce");
        login.get(2).click();
        Assert.assertEquals("Swag Labs", driver.getTitle());

        //Wait for page to load
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed();

        //Open the Backpack page
        driver.findElement(By.xpath("//*[contains(text(),'Sauce Labs Backpack')]")).click();

        //Verify Price Title and Description of the product
        List<WebElement> backpackDetails = driver.findElement(By.className("inventory_details_desc_container")).findElements(By.xpath("./child::*"));
        Assert.assertEquals(backpackDetails.get(0).getText(),"Sauce Labs Backpack");
        Assert.assertEquals(backpackDetails.get(1).getText(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        Assert.assertEquals(backpackDetails.get(2).getText(),"$29.99");

        //Add to cart and return back to product page
        backpackDetails.get(3).click();
        driver.findElement(By.id("back-to-products")).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed();


        //Find Fleece Jacket and add to cart
        driver.findElement(RelativeLocator.with(By.className("btn_inventory")).below(By.xpath("//*[contains(text(),'Sauce Labs Fleece Jacket')]"))).click();

        //Open shopping cart page and go to checkout
        driver.findElement(By.id("shopping_cart_container")).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed();
        driver.findElement(By.id("checkout")).click();

        //Enter shipping details
        List<WebElement> shippingDetails = new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElements(By.cssSelector("input")));
        shippingDetails.get(0).sendKeys("Deniz");
        shippingDetails.get(1).sendKeys("Hadžirušidović");
        shippingDetails.get(2).sendKeys("76250");
        shippingDetails.get(3).click();

        //Finish and confirm message
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("finish"))).click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText(),"THANK YOU FOR YOUR ORDER");

    }


}
