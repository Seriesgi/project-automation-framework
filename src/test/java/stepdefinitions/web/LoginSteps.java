package stepdefinitions.web;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import utils.BaseApi;
import utils.DriverManager;

public class LoginSteps {
    private LoginPage loginPage;

    @Given("User is on login page")
    public void openLoginPage() {
        DriverManager.init();
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open(BaseApi.getConfig("web.base.url"));
    }

    @When("User login with username {string} and password {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("User should be redirected to homepage")
    public void verifyLogin() {
        assertThat(DriverManager.getDriver().getCurrentUrl(), containsString("inventory"));
    }

    @After("@web")
    public void tearDown() {
        DriverManager.quit();
    }
}
