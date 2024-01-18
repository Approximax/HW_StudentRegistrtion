package github;

import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragDrop {

    @BeforeEach
    void BeforeEach() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @Test
    void DragDropActionsTest() {

//        - Откройте https://the-internet.herokuapp.com/drag_and_drop
//        - Перенесите прямоугольник А на место В
//        - Проверьте, что прямоугольники действительно поменялись
//        - В Selenide есть команда $(element).dragAndDrop($(to-element)), проверьте работает ли тест, если использовать её вместо actions()

        SelenideElement BoxA = $("#column-a");
        SelenideElement BoxB = $("#column-b");
        actions().clickAndHold(BoxA).moveToElement(BoxB).release().perform();
        $(BoxB).shouldHave(text("A"));
    }

    @Test
    void DragDropTest() {
        SelenideElement BoxA = $("#column-a");
        SelenideElement BoxB = $("#column-b");
        $(BoxA).dragAndDrop(DragAndDropOptions.to(BoxB));
        $(BoxB).shouldHave(text("A"));
    }
}
