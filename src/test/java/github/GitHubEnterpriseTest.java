package github;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;

public class GitHubEnterpriseTest {
    
    @Test
    void enterpriseTest() {

        open("https://github.com");
        $(".Header-old").$(byText("Solutions")).hover();
        $$(".HeaderMenu-dropdown-link").filterBy(text("Enterprise")).first().click();

        $("#hero-section-brand-heading").shouldHave(text("The AI-powered"));
    }
}
