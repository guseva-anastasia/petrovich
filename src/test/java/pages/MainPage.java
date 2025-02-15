package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.Categories;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public SelenideElement catalogButton = $("[data-test=catalog-button]"),
            searchNotActive = $(".search-and-suggest "),
            searchInput = $("[name=q]"),
            lowestSections = $(".lowest-sections "),
            productTitle = $("[data-test=product-title]"),
            productAddToCart = $("[data-test=product-add-to-cart]"),
            cartLink = $("[data-test=cart-link]"),
            emptyProductList = $(".empty-product-list"),
            searchProductField = $("[data-test=search-product-field]"),
            closeModalButton = $("[data-test=close-modal-btn]"),
            mainPageBanner = $(".main-page-banner");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        mainPageBanner.shouldBe(visible);
          return this;
    }

    @Step("Раскрыть каталог")
    public MainPage openCatalogue () {
        catalogButton.shouldBe(visible).click();

        return this;
    }

    @Step("Проверить категории каталога")
    public MainPage checkCategories (Categories categories) {
        $$(".sections-list-link-inner ").findBy(partialText(categories.description)).shouldBe(visible);
        return this;
    }

    @Step("Ввод в поисковую строку")
    public MainPage inputInSearchBar (String productName){
        searchNotActive.click();
        searchInput.setValue(productName).pressEnter();
        return this;
    }

    @Step("Поиск с помощью поисковой строки")
    public MainPage checkSearchUsingSearchBar (String productName){
        lowestSections.shouldHave(text(productName)).shouldBe(visible);
        productTitle.shouldHave(text(productName));
        return this;
    }

    @Step("Добавить продукт в корзину")
    public MainPage addToCart (){
        productAddToCart.shouldBe(visible).scrollIntoView(false).click();
        return this;
    }

    @Step("Перейти в корзину")
    public MainPage goToCart (){
        cartLink.click();
        return this;
    }

    @Step("Проверить, что корзина не пуста")
    public MainPage checkCartNotEmpty (){
        emptyProductList.shouldNotBe(visible);
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public MainPage checkCartEmpty (){
        emptyProductList.shouldBe(visible);
        return this;
    }

    @Step("Удалить всё из корзины")
    public MainPage deleteAllProductsFromCart (){
        $(byText("Удалить всё")).click();
        $(".cart-modal-buttons-row").$(byText("ДА")).click();
        return this;
    }

    @Step("Добавить продукт с помощью корзины")
    public MainPage adProductFromCart (String productName){
        $(".fast-add-product-desktop").$(byText("Добавить товар")).click();
        searchProductField.setValue(productName);
        productAddToCart.shouldBe(visible).click();
        closeModalButton.click();
        return this;
    }

}
