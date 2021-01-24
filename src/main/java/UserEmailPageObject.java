import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserEmailPageObject {

    private WebDriver driver;

    private By writeLetterButton = By.xpath("//a[@data-title-shortcut='N']");
    private By letterContactAddres = By.xpath("(//input[@class='container--H9L5q size_s--3_M-_'])[1]");
    private By letterSubject = By.xpath("(//input[@class='container--H9L5q size_s--3_M-_'])[2]");
    private By letterBody = By.xpath("//div[@contenteditable='true']//div[1]");
    private By sendLetterButton = By.xpath("//span[text()[normalize-space()='Отправить']]");
    private By letterSentMSG = By.xpath("//a[text()[normalize-space()='Письмо отправлено']]");
    private By userAccountName = By.xpath("//i[@class='x-ph__menu__button__text x-ph__menu__button__text_auth']");

    public UserEmailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void writeLetterButtonClick (){
        driver.findElement(writeLetterButton).click();
    }

    public void setContactAddress (String contactAddress) {
        driver.findElement(letterContactAddres).sendKeys(contactAddress);
    }

    public void setLetterSubject (){
        driver.findElement(letterSubject).sendKeys("Some awesome subject");
    }

    public void setLetterBody () {
        driver.findElement(letterBody).sendKeys("Hi. Lets talk about carrots and rabbits, in a polymorphism context.");
    }

    public void sendLetter (){
        driver.findElement(sendLetterButton).click();
    }

    public WebElement getSendButtonParams (){
        return driver.findElement(sendLetterButton);
    }

    public WebElement getContactAddressFieldParams (){
        return driver.findElement(letterContactAddres);
    }

    public WebElement getSubjectFieldParams (){
        return driver.findElement(letterSubject);
    }

    public WebElement getLetterBodyFieldParams (){
        return driver.findElement(letterBody);
    }

    public String getLetterSentMSG () {
        return driver.findElement(letterSentMSG).getText();
    }

    public By getWriteLetterButtonXpath (){
        return writeLetterButton;
    }

    public String getUserAccountName () {
        return driver.findElement(userAccountName).getText();
    }

}
