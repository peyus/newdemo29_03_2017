package Test.Project;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Piyush on 29/01/2017.
 */
public class FunctionalStepdefs {

    HomePage homePage;
    RegistrationPage registrationPage;

    @Given("^User on home page$")
    public void userOnHomePage() throws Throwable {

    }

    @When("^he goto registration$")
    public void heGotoRegistration() throws Throwable {
        homePage=new HomePage();
        homePage.meth_goto_registerpage();
    }

    @And("^he enters details$")
    public void heEntersDetails() throws Throwable {
        registrationPage=new RegistrationPage();
        registrationPage.todoRegister();

    }

    @Then("^he registered sucessfully$")
    public void heRegisteredSucessfully() throws Throwable {
        registrationPage=new RegistrationPage();
        registrationPage.meth_check_content("Your registration completed");
    }

}
