package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.Categories;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public SelenideElement catalogButton = $("[data-test=catalog-button]"),
            searchNotActive = $(".search-and-suggest "),
            searchInput = $("[name=q]"),
            lowestSections = $(".lowest-sections "),
            productTitle = $("[data-test=product-title]"),
            productAddToCart = $("[data-test=product-add-to-cart]"),
            mainPageBanner = $(".main-page-banner");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        mainPageBanner.shouldBe(visible);
        return this;
    }

    @Step("Раскрыть каталог")
    public MainPage openCatalogue() {
        catalogButton.shouldBe(visible).click();

        return this;
    }

    @Step("Проверить категории каталога")
    public MainPage checkCategories(Categories categories) {
        $$(".sections-list-link-inner ").findBy(partialText(categories.description)).shouldBe(visible);
        return this;
    }

    @Step("Ввод в поисковую строку")
    public MainPage inputInSearchBar(String productName) {
        searchNotActive.click();
        searchInput.setValue(productName).pressEnter();
        return this;
    }

    @Step("Поиск с помощью поисковой строки")
    public MainPage checkSearchUsingSearchBar(String productName) {
        lowestSections.shouldHave(text(productName)).shouldBe(visible);
        productTitle.shouldHave(text(productName));
        return this;
    }

    @Step("Добавить продукт в корзину")
    public MainPage addToCart() {
        productAddToCart.shouldBe(visible).scrollIntoView(false).click();
        return this;
    }

}
