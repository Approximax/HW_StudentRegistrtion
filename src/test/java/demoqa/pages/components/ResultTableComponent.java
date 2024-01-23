package demoqa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {
    SelenideElement modalTable = $(".modal-open"),
                    tableValue = $(".table-responsive");

    public ResultTableComponent checkAppearance() {
        modalTable.shouldBe(visible);

        return this;
    }

    public ResultTableComponent checkAbsence() {
        modalTable.shouldNotBe(visible);

        return this;
    }

    public ResultTableComponent checkTableValue (String key, String value) {
        tableValue.shouldHave(text(key)).shouldHave(text(value));

        return this;
    }
}
