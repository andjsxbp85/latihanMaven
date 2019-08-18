package test;

import net.serenitybdd.jbehave.SerenityStory;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class LoginReq extends SerenityStory {
    @Steps
    LoginReqSteps LoginReqSteps;

    //login 1
    @Given("I'm on https://Reqres.in")
    public void givenImOnHttpsReqresin() {
        LoginReqSteps.halamanAwal();
    }

    @When("I click button \"login\"")
    public void whenIClickButtonlogin() {
        LoginReqSteps.klikLogin();
    }

    @Then("I Will go to dashboard")
    public void thenIWillGoToDashboard() {
        LoginReqSteps.getData();
    }

    //Register
    @Given("i'm on https://Reqres.in lol")
    public void givenImOnHttpsReqresinLol() {
        LoginReqSteps.halamanAwal();
    }
    @When("I click button register")
    public void whenIClickButtonRegister() {
        LoginReqSteps.klikRegister();
    }

    @Then("user will showed about recommended friend")
    public void thenUserWillShowedAboutRecommendedFriend() {
        LoginReqSteps.getFriend();
    }
}
