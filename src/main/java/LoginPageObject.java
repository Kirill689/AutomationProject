import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {

    private WebDriver driver;

    //AddressAndDomainSteps
    private By userEmailAddres = By.xpath("//input[@data-testid='login-input']");
    private By mailRuDomain = By.xpath("//option[@value='@mail.ru']");
    private By domainName = By.xpath("//select[@name='domain']");
    private By enterPasswordButton = By.xpath("//button[@data-testid='enter-password']");

    //PasswordSteps
    private By userPassword = By.xpath("//input[@data-testid='password-input']");
    private By loginToEmailButton = By.xpath("//button[@data-testid='login-to-mail']");

    //Errors
    private By wrongMailAddressMSG = By.xpath("//div[text()='Неверное имя ящика']");
    private By noMailAddressMSG = By.xpath("//div[text()='Введите имя ящика']");
    private By wrongPasswordMSG = By.xpath("//div[text()='Неверное имя или пароль']");
    private By noPasswordMSG = By.xpath("//div[text()='Введите пароль']");


    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }


    //Steps
    public void setUserEmailAddres(String typeAddres) {
        driver.findElement(userEmailAddres).sendKeys(typeAddres);
    }

    public void setUserPassword(String typePassword){
        driver.findElement(userPassword).sendKeys(typePassword);
    }

    public void setMailRuDomain(){ driver.findElement(domainName).findElement(mailRuDomain).click(); }

    public void pressEnterPasswordButton (){
        driver.findElement(enterPasswordButton).click();
    }

    public String getDomainName () { return driver.findElement(domainName).getAttribute("value");}

    public UserEmailPageObject loginToUserMailPage(){
        driver.findElement(loginToEmailButton).click();
        return new UserEmailPageObject(driver);
    }


    //Error MSG's
    public String getWrongMailAddressMSG () { return driver.findElement(wrongMailAddressMSG).getText(); }

    public String getNoMailAddressMSG () { return driver.findElement(noMailAddressMSG).getText(); }

    public String getWrongPasswordMSG () { return driver.findElement(wrongPasswordMSG).getText(); }

    public String getNoPasswordMSG () { return driver.findElement(noPasswordMSG).getText(); };


}
