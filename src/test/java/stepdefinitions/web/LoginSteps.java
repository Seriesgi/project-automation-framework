package stepdefinitions.web;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import utils.ConfigManager;
import utils.DriverManager;

public class LoginSteps {
    private LoginPage loginPage;

    @Given("User is on login page")
    public void openLoginPage() {
        DriverManager.init();
        loginPage = new LoginPage(DriverManager.getDriver(), DriverManager.waitForDriver());
        loginPage.open(ConfigManager.get("web.base.url"));
    }

    @When("User login with username {string} and password {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("User should be redirected to homepage")
    public void verifyLogin() {
        assertThat(loginPage.getCurrentUrl(), containsString("inventory"));
        assertThat(loginPage.isInventoryVisible(), equalTo(true));
    }

    @Then("User should see login error message {string}")
    public void verifyLoginError(String expectedMessage) {
        assertThat(loginPage.getErrorMessage(), containsString(expectedMessage));
    }

    @After("@web")
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            DriverManager.captureScreenshot(scenario.getName());
        }
        DriverManager.quit();
    }
}
