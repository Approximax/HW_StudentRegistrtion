package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class SoftAssertsTest {

    @BeforeAll
    static void BeforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void SelenideSoftAssertsTest() {

//        - Откройте страницу Selenide в Github
//        - Перейдите в раздел Wiki проекта
//        - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
//        - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-body").shouldHave(text("Soft assertions"));


        open("/selenide/selenide/wiki/SoftAssertions");
        $("#wiki-body").shouldHave(text("Using JUnit5 extend test class:"));
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "@Test\n" +
                "void test() {\n" +
                "Configuration.assertionMode = SOFT;\n" +
                "open(\"page.html\");\n" +
                "$(\"#first\").should(visible).click();\n" +
                "$(\"#second\").should(visible).click();\n" +
                "}\n" +
                "}\n"));

    }
}
