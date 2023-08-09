package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.PhoneNumber;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {


    @Test
    void shouldDeliverCardAgain() {

        open("http://localhost:9999/");
        String city = GenerateData.generateCity();
        String planningDayFirst = GenerateData.generateDate();
        String planningDaySecond = GenerateData.generateDate();
        String name = GenerateData.generateName();
        String phone = GenerateData.generatePhone();
        DataClass data1 = new DataClass(city, planningDayFirst, name, phone);

        $("[data-test-id='city'] input").setValue(data1.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(data1.getDate());
        $("[data-test-id='name'] input").setValue(data1.getName());
        $("[data-test-id='phone'] input").setValue(data1.getPhone());
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $(".notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDayFirst), Duration.ofSeconds(15)).shouldBe(visible);

        open("http://localhost:9999/");
        DataClass data2 = new DataClass(city, planningDaySecond, name, phone);
        $("[data-test-id='city'] input").setValue(data2.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.UP), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(data2.getDate());
        $("[data-test-id='name'] input").setValue(data2.getName());
        $("[data-test-id='phone'] input").setValue(data2.getPhone());
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $(".notification__title").shouldHave(Condition.text("Успешно!"), Duration.ofSeconds(15)).shouldBe(visible);
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDaySecond), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
