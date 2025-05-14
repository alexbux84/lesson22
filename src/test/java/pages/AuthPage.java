package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AuthPage {

    private String loginField =  "username";
    private String passwordField = "password";
    private String loginButton = "login-button";

    public VerifyBySmsPage authenticate(String username, String password) {
        $(By.id(loginField)).shouldBe(visible, enabled).setValue(username);
        $(By.name(passwordField)).shouldBe(visible, enabled).setValue(password);
        $(By.id(loginButton)).shouldBe(visible, enabled).click();
        return page(VerifyBySmsPage.class);
    }
}
