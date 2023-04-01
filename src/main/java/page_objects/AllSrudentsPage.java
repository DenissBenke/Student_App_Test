package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AllSrudentsPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public AllSrudentsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(how = How.XPATH, using = "//div[@class='ant-table-title']//button")
    public WebElement addStudentButton ;

    public void  waitAndClickOnStudentButton() {
        webDriverWait.until(elementToBeClickable(addStudentButton));
        addStudentButton.click();
    }
}
