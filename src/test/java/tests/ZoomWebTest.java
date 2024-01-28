package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import testdata.ZoomLanguages;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ZoomWebTest extends TestBase {

    @BeforeEach
    void beforeEach() {
        open("https://zoom.us/");
    }

    @ParameterizedTest
    @EnumSource(ZoomLanguages.class)
    @DisplayName("Each language should have corresponding page description")
    void shouldCheckDescriptionForLanguages(ZoomLanguages languages) {
        $(".dropdown-language").click();
        $(".dropdown-language").$("ul.dropdown-menu").$(byText(languages.name())).click();
        $(".title").shouldHave(text(languages.description));
    }

    static Stream<Arguments> shouldCheckButtonsForLanguages() {
        return Stream.of(
                Arguments.of(ZoomLanguages.English, List.of("Search\n" +
                        "Support\n" +
                        "1.888.799.9666\n" +
                        "Request a Demo\n" +
                        "Join\n" +
                        "Host \n" +
                        "Sign In")),
                Arguments.of(ZoomLanguages.Русский, List.of("Искать\n" +
                        "Поддержка\n" +
                        "1.888.799.9666\n" +
                        "Запросить демо-версию\n" +
                        "Присоединиться\n" +
                        "Организатор \n" +
                        "Войти в систему"))
        );
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("Each language should have corresponding buttons in header")
    void shouldCheckButtonsForLanguages(ZoomLanguages languages, List<String> expectedButtons) {
        $(".dropdown-language").click();
        $(".dropdown-language").$("ul.dropdown-menu").$(byText(languages.name())).click();
        $$("ul.list-inline").filter(visible).shouldHave(texts(expectedButtons));
    }

}