package tests;

import helpers.TestData;
import models.Categories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import pages.MainPage;


public class PetrovichTests extends TestBase{

    MainPage mainPage = new MainPage();
    TestData testData = new TestData();

    @Tag("petrovich_tests")
    @Test
    @DisplayName("Проверить работу поисковой строки")
    void checkSearchBarTest(){
        mainPage.openPage()
                .inputInSearchBar(testData.productName)
                .checkSearchUsingSearchBar(testData.productName);
    }

    @Tag("petrovich_tests")
    @Test
    @DisplayName("Проверить добавление продукта с главной страницы в корзину")
    void checkAddProductToCartFromMainPageTest(){
        mainPage.openPage()
                .addToCart()
                .goToCart()
                .checkCartNotEmpty();
    }

    @Tag("petrovich_tests")
    @Test
    @DisplayName("Проверить добавление продукта с помощью корзины")
    void checkAddProductFromCartTest(){
        mainPage.openPage()
                .goToCart()
                .adProductFromCart(testData.productName)
                .checkCartNotEmpty();
    }

    @Tag("petrovich_tests")
    @Test
    @DisplayName("Проверить удаление всех продуктов из корзины")
    void checkDeleteAllProductsFromCartTest(){
        mainPage.openPage()
                .addToCart()
                .goToCart()
                .checkCartNotEmpty()
                .deleteAllProductsFromCart()
                .checkCartEmpty();
    }

    @Tag("petrovich_tests")
    @DisplayName("Проверить элементы каталога продуктов")
    @ParameterizedTest()
    @EnumSource
    void checkListOfCatalogueElementsTest(Categories categories) {
        mainPage.openPage()
                .openCatalogue()
                .checkCategories(categories);
    }

}
