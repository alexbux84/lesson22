package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class VerifyBySmsPage {
    private String otpCodeField = "otp-code";
    private String confirmButton = "login-otp-button";
    private String optCodeText = "otp-code-text";

    public MainPage confirmByCode(String code) {
        $(By.id(otpCodeField)).shouldBe(visible, enabled).setValue(code);
        $(By.id(confirmButton)).shouldBe(visible, enabled).click();
        return page(MainPage.class);
    }

    public SelenideElement getOptCodeText() {
        return $(By.id(optCodeText));
    }
}
