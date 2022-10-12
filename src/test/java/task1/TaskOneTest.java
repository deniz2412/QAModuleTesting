package task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.List;

@Test()
public class TaskOneTest {
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
    public void TestCase1() {
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

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed();
        //Verify if elements are present
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("bm-burger-button")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
        //Facebook,LinkedIn and Twitter
        List<WebElement> socialLinks = driver.findElements(By.cssSelector(".social li"));
        System.out.println(socialLinks.size());
        for (WebElement link : socialLinks) {
            Assert.assertTrue(link.isDisplayed());
        }
        //Logout
        driver.findElement(By.className("bm-burger-button")).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("logout_sidebar_link")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();

    }


}




