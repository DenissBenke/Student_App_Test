package page_objects;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddStudentPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private final Faker faker = new Faker();

    public AddStudentPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "name")
    WebElement nameField;

    public void waitAndSetValueForNameField(){
        webDriverWait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(faker.name().firstName());
    }
}
