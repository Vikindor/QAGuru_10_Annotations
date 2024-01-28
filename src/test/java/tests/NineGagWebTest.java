package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("9GAG Web Test")
public class NineGagWebTest extends TestBase {

    @BeforeEach
    void beforeEach() {
        open("https://9gag.com/");
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Page header should have logo with the '9GAG' label")
    void pageHeaderShouldHaveNineGagLabel() {
        $(".nav-wrap .logo").shouldHave(text("9GAG"));
    }

    @ParameterizedTest(name = "Interest page {0} should have corresponding header")
    @Tag("SMOKE")
    @ValueSource(strings = {
            "Humor", "Memes", "Animals & Pets"
    })
    @DisplayName("Interest page should have corresponding header")
    void interestPageShouldHaveCorrespondingLabel(String interest) {
        $(".drawer-container").$(byText(interest)).click();
        $(".tag-interest-list-header__title").shouldBe(visible).shouldHave(text(interest));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "Home , 9GAG - Headquarters of the Best Funny Memes",
            "Top, Best Memes of the Week - 9GAG",
            "Trending, Popular Memes Right Now - 9GAG",
            "Fresh, New and Fresh Funny Memes - 9GAG",
            "Ask 9GAG, 9GAG - Best Funny Memes and Breaking News"
    })
    @DisplayName("Each website tab should have correct browser tab title")
    void eachTabShouldHaveCorrectTitle(String tabName, String expectedTitle) {
        $(".drawer-container ul").$(byText(tabName)).click();
        sleep(2500); //Wait until tab title changes
        String actualTitle = Selenide.title();
        assertEquals(expectedTitle, actualTitle);
    }

}