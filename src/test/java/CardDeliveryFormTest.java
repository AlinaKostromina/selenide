import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CardDeliveryFormTest {

    @Test
    void shouldSendForm() {
        // командой gradlew test -Dselenide.headless=true запускать в IDEA тесты
        
        String date = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Новосибирск");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date);
        $("[data-test-id='name'] input").setValue("Костромина Алина");
        $("[data-test-id='phone'] input").setValue("+79139116137");
        $("[data-test-id=agreement]").click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.ownText(date));
    }
}


