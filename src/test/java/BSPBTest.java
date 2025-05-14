import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.MainPage;
import pages.VerifyBySmsPage;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class BSPBTest extends BaseTest {

    private final List<String> expectedContents = List.of(
            "Travel *6192",
            "Золотая *2224",
            "Яркая *9074",
            "Standard КХЛ *1111",
            "Детская *4123",
            "Виртуальная *0556",
            "Срок действия карты Единая карта петербуржца *1123 заканчивается 13.06.2025",
            "Платиновая *5123");

    @Test
    public void testBSPB_Successfully() {

        AuthPage authPage = open("https://idemo.bspb.ru", AuthPage.class);
        //Войти в систему под учетной записью demo / demo
        VerifyBySmsPage verifyPage = authPage.authenticate("demo", "demo");
        //Отображается форма двухфакторной аутентификации (определелим по одному видимому элементу)
        verifyPage.getOptCodeText().shouldBe(visible);
        //Ввести в поле ввода кода подтверждения 0000. Нажать на кнопку войти
        MainPage mainPage = verifyPage.confirmByCode("0000");
        //Осуществлён вход в систему
        Assert.assertEquals(driver.getTitle(), "Старт - Интернет банк - Банк Санкт-Петербург", "Вход в систему не выполнен");
        //Найти на странице «Финансовая свобода», отображается блок «Финансовая свобода»
        //с указанием суммы в формате “123 456 789.00 ₽”
        mainPage.getFinanceFreedom().shouldBe(visible).$x(".//span[@class='can-spend-amount']").shouldHave(matchText("^\\d{1,3}(?: \\d{3})*\\.\\d{2} ₽$"));
        ElementsCollection cards = mainPage.getCards();
        Actions actions = new Actions(driver);
        cards.forEach(card -> {
            //Навести курсор на символы карт
            actions.moveToElement(card).perform();
            //Появляется надпись:«Travel *6192»,«Золотая *2224»,...
            //надпись в доме появляется динамически следующим элементом после ссылки на карту
            String text = card.shouldBe(visible).$x("./following-sibling::*[contains(@class,'popover')]").shouldBe(visible).$x(".//*[@class='popover-content']").getText();
            Assert.assertTrue(expectedContents.contains(text), "Карта с надписью '%s' не ожидалась".formatted(text));
        });
    }
}