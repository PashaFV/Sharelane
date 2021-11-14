import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void ZipCodeShouldAccept5Digits(){
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        //Ввести 5 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");

        //Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void ZipCodeShouldAccept4Digits(){
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        //Ввести 4 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("1234");

        //Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что после ввода невалидных данных появляется сообщение "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed, "Сообщение не появилось");
        String expectedTitle = "Oops, error on page. ZIP code should have 5 digits";
        String originalTitle = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(originalTitle, expectedTitle);

        //Закрыть браузер
        //driver.quit();

    }

    @Test
    public void ZipCodeShouldAccept6Digits(){
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        //Ввести 6 цифр
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("123456");

        //Нажимаем кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что после ввода невалидных данных появляется уведомление "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed, "Сообщение не появилось");
        String expectedTitle = "Oops, error on page. ZIP code should have 5 digits";
        String originalTitle = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(originalTitle, expectedTitle);

        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void ZipCodeAccept0Digits(){
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        //Не вводить цифры и нажать кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что после ввода невалидных данных появляется уведомление "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed,"Oops, error on page. ZIP code should have 5 digit");
        String expectedTitle = "Oops, error on page. ZIP code should have 5 digits";
        String originalTitle = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(originalTitle, expectedTitle);

        //Закрыть браузер
        driver.quit();


    }

}
