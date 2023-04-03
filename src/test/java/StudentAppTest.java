import com.github.javafaker.Faker;
import constants.AllConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.AddStudentPage;
import page_objects.AllSrudentsPage;
import page_objects.Notification;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class StudentAppTest {

    WebDriver driver;
    WebDriverWait driverWait;
    Faker dataFaker = new Faker();
    AllSrudentsPage allSrudentsPage;

    AddStudentPage addStudentPage;
    Notification notifications;

    private final String APP_URL = "http://app.acodemy.lv/";

    @BeforeMethod
    public void initialize() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(APP_URL);
        allSrudentsPage = new AllSrudentsPage(driver);
        addStudentPage = new AddStudentPage(driver);
        notifications = new Notification(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void openStudentApp() {
        allSrudentsPage.waitAndClickOnStudentButton();
        String name = addStudentPage.waitAndSetValueForNameField();
        addStudentPage.waitAndSetValueForEmailField();
        addStudentPage.waitAndSetGender(AllConstants.GenderConstatnts.OTHER);
        addStudentPage.setClickOnSubmitButton();
        Assert.assertEquals(notifications.getMessageFromNotification(), "Student successfully added");
        Assert.assertEquals(notifications.getDescriptionFromNotification(), name + " was added to the system");
        notifications.getPopUpCloseButton().click();
        assertTrue(driverWait.until(ExpectedConditions.invisibilityOf(notifications.getPopUpCloseButton())));
    }
}


//         driver.findElement(By.xpath("//div[@class='ant-form-item-control-input-content']//button")).click();
//        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ant-table-title']//button")));