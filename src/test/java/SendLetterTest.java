import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SendLetterTest {

    private WebDriver webDriver;
    private LoginPageObject loginPageObject;
    private UserEmailPageObject userEmailPageObject;
    private String mail;
    private String password;
    private String contactAddress;

    //Set up the environment before every test
    @Before
    public void setUpBeforeTestMethod (){

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://mail.ru/");

        mail = "testbox6894847";
        password = "AutomationTestBox";
        contactAddress = "Testbox6894847@mail.ru";

        loginPageObject = new LoginPageObject(webDriver);
        loginPageObject.setMailRuDomain();
        loginPageObject.setUserEmailAddres(mail);
        loginPageObject.pressEnterPasswordButton();
        loginPageObject.setUserPassword(password);
        userEmailPageObject = loginPageObject.loginToUserMailPage();

        WebDriverWait await = new WebDriverWait(webDriver,7);
        await.until(ExpectedConditions.presenceOfElementLocated(userEmailPageObject.getWriteLetterButtonXpath()));
    }

    //Check the state of letter form fields
    @Test
    public void contentBoxComponentsIsEnabledTest () {

       userEmailPageObject.writeLetterButtonClick();
       Assert.assertTrue(userEmailPageObject.getSendButtonParams().isEnabled());
       Assert.assertTrue(userEmailPageObject.getContactAddressFieldParams().isEnabled());
       Assert.assertTrue(userEmailPageObject.getSubjectFieldParams().isEnabled());
       Assert.assertTrue(userEmailPageObject.getLetterBodyFieldParams().isEnabled());


    }

    //Send letter to myself
    @Test
    public void sendLetterTest (){
        userEmailPageObject.writeLetterButtonClick();
        userEmailPageObject.setContactAddress(contactAddress);
        userEmailPageObject.setLetterSubject();
        userEmailPageObject.setLetterBody();
        userEmailPageObject.sendLetter();
        String letterSentMSG = userEmailPageObject.getLetterSentMSG();
        Assert.assertEquals("Письмо отправлено", letterSentMSG);
    }

    @After
    public void tearDownAfterTestMethod (){
        webDriver.close();
    }


}
