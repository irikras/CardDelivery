package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    LocalDate today = LocalDate.now(); // получаем текущую дату
    LocalDate meetingDate = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(formatter.format(meetingDate));
        $("[data-test-id=name] input").setValue("Иванов Василий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
