package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {

    public SelenideElement cartLink = $("[data-test=cart-link]"),
            emptyProductList = $(".empty-product-list"),
            searchProductField = $("[data-test=search-product-field]"),
            productAddToCart = $("[data-test=product-add-to-cart]"),
            closeModalButton = $("[data-test=close-modal-btn]");

    @Step("Перейти в корзину")
    public CartPage goToCart (){
        cartLink.click();
        return this;
    }

    @Step("Проверить, что корзина не пуста")
    public CartPage checkCartNotEmpty (){
        emptyProductList.shouldNotBe(visible);
        return this;
    }

    @Step("Проверить, что корзина пуста")
    public CartPage checkCartEmpty (){
        emptyProductList.shouldBe(visible);
        return this;
    }

    @Step("Удалить всё из корзины")
    public CartPage deleteAllProductsFromCart (){
        $(byText("Удалить всё")).click();
        $(".cart-modal-buttons-row").$(byText("ДА")).click();
        return this;
    }

    @Step("Добавить продукт с помощью корзины")
    public CartPage adProductFromCart (String productName){
        $(".fast-add-product-desktop").$(byText("Добавить товар")).click();
        searchProductField.setValue(productName);
        productAddToCart.shouldBe(visible).click();
        closeModalButton.click();
        return this;
    }
}
