package demoqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    SelenideElement fullNameInput = $("#userName"),
                    emailInput = $("#userEmail"),
                    currentAddressInput = $("#currentAddress"),
                    permanentAddressInput = $("#permanentAddress"),
                    submitButton = $("#submit"),
                    result = $("#output");

    public TextBoxPage openPage() {
        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);

        return this;
    }

    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage submitButtonClick() {
        submitButton.click();

        return this;
    }

    public TextBoxPage checkOutputAppearance() {
        result.shouldBe(visible);

        return this;
    }

    public TextBoxPage checkResultValue(String key, String value) {
        result.shouldHave(text(key)).shouldHave(text(value));

        return this;
    }
}
