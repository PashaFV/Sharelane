import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    //Путь к chromedriver.exe
    public String pathChromedriver = "src/test/resources/chromedriver.exe";
    //Адрес страницы регистрации
    public String pageRegistration = "https://www.sharelane.com/cgi-bin/register.py";


    @Test
    public void ZipCodeShouldAccept5Digits() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

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
    public void ZipCodeShouldAccept4Digits() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

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
        driver.quit();

    }

    @Test
    public void ZipCodeShouldAccept6Digits() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

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
    public void ZipCodeAccept0Digits() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Не вводить цифры и нажать кнопку Continue
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что после ввода невалидных данных появляется уведомление "Oops, error on page. ZIP code should have 5 digits"
        boolean isDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(isDisplayed, "Oops, error on page. ZIP code should have 5 digit");
        String expectedTitle = "Oops, error on page. ZIP code should have 5 digits";
        String originalTitle = driver.findElement(By.cssSelector(".error_message")).getText();
        Assert.assertEquals(originalTitle, expectedTitle);

        //Закрыть браузер
        driver.quit();


    }


    @Test
    public void successfulRegistration() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Пройти шаг заполнения поля "zip code"
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Заполнитиь поля валидными данными
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Pavel");
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Petrov");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("pavel123@mail.ru");
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys("12345678");
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("12345678");

        //Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        //Убедиться что мы на странице успешной регистрации
        boolean confirmMessageDisplayed = driver.findElement(By.cssSelector(".confirmation_message")).isDisplayed();
        Assert.assertTrue(confirmMessageDisplayed);
        Assert.assertEquals(driver.findElement(By.cssSelector(".confirmation_message")).getText(), "Account is created!", "Сообщения не соответствует требованиям");

        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void invalidEmailRegistration() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Пройти шаг заполнения поля "zip code"
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Заполнитиь полe email невалидными данными, остальные поля валидными данными
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Pavel");
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Petrov");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("123");
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys("12345678");
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("12345678");

        //Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        //Убедиться что при регистрации с невалидным email, появляется уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        boolean errorOnPageDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(errorOnPageDisplayed);
        Assert.assertEquals(driver.findElement(By.cssSelector(".error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Сообщения не соответствует требованиям");


        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void passwordMatching() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Пройти шаг заполнения поля "zip code"
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Заполнитиь полe Confirm password данными не совпадающими с полем Password, остальные поля валидными данными
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Pavel");
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Petrov");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("pavel123@test.by");
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys("12345678");
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("123");

        //Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        //Убедиться что после ввода несовпадающих паролей, появляется уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        boolean errorOnPageDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(errorOnPageDisplayed);
        Assert.assertEquals(driver.findElement(By.cssSelector(".error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Сообщения не соответствует требованиям");

        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void registerWithoutData() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Пройти шаг заполнения поля "zip code"
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);


        //Нажимаем кнопку Register без заполнения формы
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        //Убедиться что при регистрации без заполнения формы, появляется уведомление "Oops, error on page. Some of your fields have invalid data or email was previously used"
        boolean errorOnPageDisplayed = driver.findElement(By.cssSelector(".error_message")).isDisplayed();
        Assert.assertTrue(errorOnPageDisplayed);
        Assert.assertEquals(driver.findElement(By.cssSelector(".error_message")).getText(), "Oops, error on page. Some of your fields have invalid data or email was previously used", "Сообщения не соответствует требованиям");

        //Закрыть браузер
        driver.quit();

    }

    @Test
    public void registerWithoutLastName() {
        //Открытие страницы https://www.sharelane.com/cgi-bin/register.py
        System.setProperty("webdriver.chrome.driver", pathChromedriver);
        WebDriver driver = new ChromeDriver();
        driver.get(pageRegistration);

        //Пройти шаг заполнения поля "zip code"
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[value=Continue]"));
        continueButton.click();

        //Убедиться что мы на странице SignUp
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        //Заполнитиь все поля кроме Last Name (Поле Last name является необязательным для заполнения)
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Pavel");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("pavel123@test.by");
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys("12345678");
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("12345678");

        //Нажимаем кнопку Register
        WebElement registerButton = driver.findElement(By.cssSelector("[value=Register]"));
        registerButton.click();

        //Убедиться что мы на странице успешной регистрации
        boolean confirmMessageDisplayed = driver.findElement(By.cssSelector(".confirmation_message")).isDisplayed();
        Assert.assertTrue(confirmMessageDisplayed);
        Assert.assertEquals(driver.findElement(By.cssSelector(".confirmation_message")).getText(), "Account is created!", "Сообщения не соответствует требованиям");

        //Закрыть браузер
        driver.quit();

    }


}
