import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class StudentAppTest {
    @Test
    public  void openStudentApp(){

        WebDriver driver = new EdgeDriver();
        driver.get("http://acodemystudentapp-env.eba-d2vctp4d.eu-north-1.elasticbeanstalk.com/");
        driver.close();
        driver.quit();
    }
}
