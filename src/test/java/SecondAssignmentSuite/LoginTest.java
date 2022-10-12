package SecondAssignmentSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LoginTest {

    public String testURL ="https://www.saucedemo.com/";
    public WebDriver driver;

    @BeforeMethod
    public void initialSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\denizh\\Documents\\TestEnv\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get(testURL);
    }


    @AfterMethod
    public void afterRun(){
        driver.quit();
    }

    @Test
    public void loginWithValidCredentials(){
        logMeIn("standard_user","secret_sauce");
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed());
    }

    @Test
    public void loginWithProblemUser(){
        logMeIn("problem_user","secret_sauce");
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed());
    }
    @Test
    public void loginWithPerformanceUser(){
        logMeIn("performance_glitch_user","secret_sauce");
        Assert.assertTrue(new WebDriverWait(driver, Duration.ofSeconds(4)).until(driver -> driver.findElement(By.id("header_container"))).isDisplayed());
    }
    public void logMeIn(String username, String password){
        List<WebElement> login = new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> driver.findElements(By.cssSelector("input")));
        login.get(0).sendKeys(username);
        login.get(1).sendKeys(password);
        login.get(2).click();

    }
}
