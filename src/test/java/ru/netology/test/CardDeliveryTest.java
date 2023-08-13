package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.Data;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;

public class CardDeliveryTest {

    @Test
    void shouldDeliverCardAgain() {

        Data data = new Data(generateCity(), generateName(), generatePhone());
        String planningDayFirst = DataGenerator.generateDate();
        String planningDaySecond = DataGenerator.generateDate();

        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue(data.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDayFirst);
        $("[data-test-id='name'] input").setValue(data.getName());
        $("[data-test-id='phone'] input").setValue(data.getPhone());
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $(".notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + planningDayFirst));
        $(".icon-button.notification__closer").click();

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDaySecond);
        $("[class='button__content']").click();
        $("[data-test-id=replan-notification] .notification__title").shouldBe(visible).shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] .button").click();
        $(".notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + planningDaySecond));
    }
}
