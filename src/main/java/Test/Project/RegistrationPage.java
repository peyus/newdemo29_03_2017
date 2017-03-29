package Test.Project;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

/**
 * Created by Piyush on 28/03/2017.
 */
public class RegistrationPage extends BaseMain
{
    @FindBy(how= How.NAME,using = "FirstName")
    public WebElement firstName;
    @FindBy(how= How.NAME,using = "LastName")
    public WebElement lastName;
    @FindBy(how= How.NAME,using = "DateOfBirthDay")
    public WebElement dateofbirthday;
    @FindBy(how= How.NAME,using = "DateOfBirthMonth")
    public WebElement dateofbirthmonth;
    @FindBy(how= How.NAME,using = "DateOfBirthYear")
    public WebElement dateofbirthyear;
    @FindBy(how=How.ID,using = "Email")
    public WebElement email;
    @FindBy(how=How.ID,using = "Newsletter")
    public WebElement newsletter;
    @FindBy(how=How.ID,using = "Password")
    public WebElement password;
    @FindBy(how=How.ID,using = "ConfirmPassword")
    public WebElement confirmPassword;
    @FindBy(how= How.NAME,using = "register-button")
    public WebElement registerButton;

    @FindBy(how=How.ID,using = "gender-male")
    public WebElement gmale;
    @FindBy(how=How.ID,using = "gender-female")
    public WebElement gfemale;

    @FindBy(how=How.TAG_NAME,using = "body")
    public WebElement content_DOM;

    public RegistrationPage() {

        PageFactory.initElements(driver, this);

    }

    public void todoRegister() {


        System.out.println("I m on TODOREGISTER");
        selectGender(true);

        firstName.sendKeys("peyus");
        lastName.sendKeys("keni");

        Select daySelect=new Select(dateofbirthday);
        daySelect.selectByVisibleText("10");
        Select monthSelect=new Select(dateofbirthmonth);
        monthSelect.selectByVisibleText("May");
        Select yearSelect=new Select(dateofbirthyear);
        yearSelect.selectByVisibleText("1988");

        email.sendKeys((new Random()).nextInt() + "test@test.com");
        newsletter.click();
        password.sendKeys("test134679");
        confirmPassword.sendKeys("test134679");
        registerButton.click();

        meth_check_content("Your registration completed");
    }

    public void selectGender(boolean male)
    {
        if (male)
        {
           gmale.click();
        }
        else {

          gfemale.click();
        }
    }


    public void meth_check_content(String str_cmpl_reg)
    {

        Assert.assertTrue(content_DOM.getText().contains(str_cmpl_reg));

    }
}
