import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import static java.lang.Thread.sleep;

public class SignUpLogIN {
    public static void main (String[]args) throws InterruptedException {
        //1.Navigate to..
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://duotify.us-east-2.elasticbeanstalk.com/register.php");
        //2.Verify the title is..
        String title = driver.getTitle();
        String expectedTerm = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedTerm));
        //3.Click on SignUp here
        driver.findElement(By.id("hideLogin")).click();
        //4.Fill out the form with required info
        Faker faker = new Faker();
        String username = faker.name().username();
        String first = faker.address().firstName();
        String last = faker.address().lastName();
        String email = faker.internet().emailAddress();
        String confirmemail = email;
        driver.findElement(By.xpath("//input[@id= 'username']")).sendKeys(username, Keys.TAB, first, Keys.TAB, last, Keys.TAB, email, Keys.TAB, confirmemail, Keys.TAB, "olganik13", Keys.TAB, "olganik13", Keys.ENTER );
        sleep(1000);
        //6. Click on Sign up: performed in the previous step by TAB.Enter
        //driver.findElement(By.name("registerButton")).click();
        //7.Verify that the URL is..
        sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");

        //8. In the left navigation bar, verify that your first and last name matches the first and last name that you used when signing up:
        WebElement leftNavbar = driver.findElement(By.id("nameFirstAndLast"));
        Assert.assertTrue(leftNavbar.getText().contains(first));
        Assert.assertTrue(leftNavbar.getText().contains(last));
        //9. Click on the username on the left navigation bar and verify the username on the main window is correct and then click logout:
        driver.findElement(By.xpath("//span[@id='nameFirstAndLast']")).click();
        driver.findElement(By.id("rafael")).click();
        //10. = 7.
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/settings.php?");
        //11. Login using the same username and password when you signed up.
        driver.findElement(By.name("loginUsername")).sendKeys(username, Keys.TAB, "olganik13", Keys.ENTER);
        //12. Verify successful login by verifying that the home page contains the text "You Might Also Like".
        String expectedTerm1 = "You Might Also Like";
        //String actualTitle1 = driver.getTitle();
        //Assert.assertTrue(actualTitle1.contains(expectedTerm1));
        WebElement tit = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/h1"));
        Assert.assertTrue(tit.getText().contains(expectedTerm1));
        // 13. Log out once again and verify that you are logged out. */
        driver.findElement(By.xpath("//span[@id='nameFirstAndLast']")).click();
        driver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(), "http://duotify.us-east-2.elasticbeanstalk.com/register.php");






    }
}
