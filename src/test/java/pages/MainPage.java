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
            ecosystemSection = $(".ecosystem-section");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");
        ecosystemSection.shouldBe(visible);
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

    @Step("Проверить подкатегории каталога")
    public MainPage checkSubcategories (Categories categories, List<String> subcategories) {
        $$(".sections-list-link-inner ").findBy(partialText(categories.description)).shouldBe(visible).hover();
        $$(".subsection").shouldHave(texts(subcategories));

        return this;
    }

    public MainPage inputInSearchBar (String productName){
        searchNotActive.click();
        searchInput.setValue(productName).pressEnter();
        return this;
    }

    public MainPage checkSearchUsingSearchBar (String productName){
        lowestSections.shouldHave(text(productName)).shouldBe(visible);
        productTitle.shouldHave(text(productName));
        return this;
    }

    public MainPage addToCart (){
        productAddToCart.shouldBe(visible).scrollIntoView(false).click();
        return this;
    }

    public MainPage goToCart (){
        cartLink.click();
        return this;
    }

    public MainPage checkCartNotEmpty (){
        emptyProductList.shouldNotBe(visible);
        return this;
    }

    public MainPage checkCartEmpty (){
        emptyProductList.shouldBe(visible);
        return this;
    }

    public MainPage deleteAllProductsFromCart (){
        $(byText("Удалить всё")).click();
        $(".cart-modal-buttons-row").$(byText("ДА")).click();
        return this;
    }

    public MainPage adProductFromCart (String productName){
        $(".fast-add-product-desktop").$(byText("Добавить товар")).click();
        searchProductField.setValue(productName);
        productAddToCart.shouldBe(visible).click();
        closeModalButton.click();
        return this;
    }

}
