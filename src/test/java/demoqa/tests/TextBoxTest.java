package demoqa.tests;

import demoqa.pages.TextBoxPage;
import org.junit.jupiter.api.Test;

public class TextBoxTest extends TestBase{

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillInDataTextBoxTest() {
        textBoxPage.openPage()
                .setFullName("Alex")
                .setEmail("qwerty@a.com")
                .setCurrentAddress("qwerty")
                .setPermanentAddress("qwerty")
                .submitButtonClick();

        textBoxPage.checkOutputAppearance()
                .checkResultValue("Name", "Alex")
                .checkResultValue("Email", "qwerty@a.com")
                .checkResultValue("Current Address", "qwerty")
                .checkResultValue("Permananet Address", "qwerty");
    }
}
