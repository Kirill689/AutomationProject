import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class LoginTest {


    private WebDriver webDriver;
    private LoginPageObject loginPageObject;
    private String mail;
    private String password;


    //Set up the environment before every test
    @Before
    public void setUpBeforeTestMethod (){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://mail.ru/");

        mail = "testbox6894847";
        password = "AutomationTestBox";

        loginPageObject = new LoginPageObject(webDriver);
        //Set domain to mail.ru in login module on homepage
        loginPageObject.setMailRuDomain();

    }


    //Check if homepage is reached
    @Test
    public void mainPageIsReachable (){
        String headding = webDriver.getTitle();
        Assert.assertEquals("Mail.ru: почта, поиск в интернете, новости, игры", headding);
    }

    //Error message for wrong email address
    @Test
    public void wrongUserEmailAddressTest (){
        Assert.assertEquals("@mail.ru", loginPageObject.getDomainName());
        loginPageObject.setUserEmailAddres("SomeAnyWrongAddressForTestFalseCase");
        loginPageObject.pressEnterPasswordButton();
        String error = loginPageObject.getWrongMailAddressMSG();
        Assert.assertEquals("Неверное имя ящика", error);
    }

    //Error message for empty email address field
    @Test
    public void noUserEmailAddressTest (){
        Assert.assertEquals("@mail.ru", loginPageObject.getDomainName());
        loginPageObject.setUserEmailAddres("");
        loginPageObject.pressEnterPasswordButton();
        String error = loginPageObject.getNoMailAddressMSG();
        Assert.assertEquals("Введите имя ящика", error);
    }

    //Error message for wrong password
    @Test
    public void wrongPaswordTest () {
        Assert.assertEquals("@mail.ru", loginPageObject.getDomainName());
        loginPageObject.setUserEmailAddres(mail);
        loginPageObject.pressEnterPasswordButton();

        loginPageObject.setUserPassword("SomeAnyWrongPasswordForTestFalseCase");
        loginPageObject.loginToUserMailPage();
        String error = loginPageObject.getWrongPasswordMSG();
        Assert.assertEquals("Неверное имя или пароль", error);
    }

    //Error message for empty password field
    @Test
    public void noPasswordTest () {

        Assert.assertEquals("@mail.ru", loginPageObject.getDomainName());
        loginPageObject.setUserEmailAddres(mail);
        loginPageObject.pressEnterPasswordButton();

        loginPageObject.setUserPassword("");
        loginPageObject.loginToUserMailPage();
        String error = loginPageObject.getNoPasswordMSG();
        Assert.assertEquals("Введите пароль", error);
    }

    //Login user account
    @Test
    public void logInToUserAccount () throws InterruptedException {
        Assert.assertEquals("@mail.ru", loginPageObject.getDomainName());
        loginPageObject.setUserEmailAddres(mail);
        loginPageObject.pressEnterPasswordButton();

        loginPageObject.setUserPassword(password);
        UserEmailPageObject userEmailPageObject = loginPageObject.loginToUserMailPage();
        Thread.sleep(5000);
        Assert.assertEquals("testbox6894847@mail.ru", userEmailPageObject.getUserAccountName());
    }



    @After
    public void tearDownAfterTestMethod (){
        webDriver.close();
    }


}
