import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class StudentAppTest {

    WebDriver driver;
    WebDriverWait driverWait;
    Faker dataFaker = new Faker();

    private final String APP_URL = "http://app.acodemy.lv/";

    @BeforeMethod
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(APP_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void openStudentApp() {


        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ant-table-title']//button")));
        WebElement addStudentButton = driver.findElement(By.xpath("//div[@class='ant-table-title']//button"));
        addStudentButton.click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement nameField = driver.findElement(By.id("name"));
        String name = dataFaker.pokemon().name();
        nameField.sendKeys(name);

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys(dataFaker.internet().emailAddress());

        WebElement genderField = driver.findElement(By.id("gender"));
        genderField.click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='MALE']")));
        WebElement valueFromDropdown = driver.findElement(By.xpath("//div[text()='MALE']"));
        valueFromDropdown.click();

        WebElement submitButton = driver.findElement(By.xpath("//div[@class='ant-form-item-control-input-content']//button"));

        submitButton.click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ant-notification-notice-message")));
        WebElement notificationMessage = driver.findElement(By.className("ant-notification-notice-message"));
        WebElement notificationMessageDescription = driver.findElement(By.className("ant-notification-notice-description"));

        Assert.assertEquals(notificationMessage.getText(), "Student successfully added");
        Assert.assertEquals(notificationMessageDescription.getText(), name + " was added to the system");

        System.out.println();


    }
}


//         driver.findElement(By.xpath("//div[@class='ant-form-item-control-input-content']//button")).click();
//        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ant-table-title']//button")));