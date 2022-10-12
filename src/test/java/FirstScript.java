import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

@Test()
public class FirstScript {
    public String testURL ="https://www.selenium.dev/selenium/web/web-form.html";
    public WebDriver driver;

    @BeforeTest
    public void initialSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\denizh\\Documents\\TestEnv\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get(testURL);
    }




    @Test
    public void login(){
        driver.get(testURL);
        Assert.assertEquals("Web form", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        Assert.assertEquals("Received!",value);
        System.out.println(driver.getTitle());

    }



}
