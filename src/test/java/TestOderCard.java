import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestOderCard {

    @Test
    void shouldTest() {

        open("http://localhost:7777");
        $("[placeholder=\"Город\"]").setValue("Москва");
        $("[placeholder=\"Дата встречи\"]").setValue("05.11.2022");
        $("[name=\"name\"]").setValue("Инесса Рюриковна");
        $("[name=\"phone\"]").setValue("+79340248197");
        $("[class=\"checkbox__box\"]").click();
        $("[class=\"button__content\"]").click();

        $x("//*[contains(text(),\"Успешно!\")]").should(visible, Duration.ofSeconds(15));
        $x("//*[contains(text(),\"Встреча успешно забронирована на\")]");
        $x("//*[contains(text(),\"05.11.2022\")]");
    }

}
