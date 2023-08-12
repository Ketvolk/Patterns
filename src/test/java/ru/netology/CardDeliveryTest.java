package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldDeliverCardAgain() {

        open("http://localhost:9999/");

        var planningDayFirst = DataClass.generateDate();
        var planningDaySecond = DataClass.generateDate();

        $("[data-test-id='city'] input").setValue(DataClass.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDayFirst);
        $("[data-test-id='name'] input").setValue(DataClass.generateName());
        $("[data-test-id='phone'] input").setValue(DataClass.generatePhone());
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
