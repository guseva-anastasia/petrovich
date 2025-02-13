package tests;

import helpers.TestData;
import models.Categories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.List;
import java.util.stream.Stream;

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
    @DisplayName("Проверить добавление продукта в корзине")
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

    @Tag("petrovich_tests")
    @DisplayName("Проверить элементы каталога продуктов")
    @ParameterizedTest()
    @MethodSource("checkCategoriesItem")
    void checkListOfSubcategoriesElementsTest(Categories categories, List<String> subcategories) {
        mainPage.openPage()
                .openCatalogue()
                .checkSubcategories(categories,subcategories);
    }

    static Stream<Arguments> checkCategoriesItem() {
        return Stream.of(
                Arguments.of(
                        Categories.BUILDING_MATERIALS,
                        List.of("Стеновые и фасадные материалы", "Сухие строительные смеси", "Древесно-плитные материалы", "Заборы и ограждения", "Тротуарная плитка, бордюры и решетки", "Кровля, водосточные системы", "Цемент и сыпучие материалы", "Пиломатериалы и отделка деревом", "Лестницы и комплектующие", "Строительные расходные материалы", "Теплоизоляция и шумоизоляция", "Сваи", "Влагозащита и пароизоляция", "Железобетонные кольца и люки","Материалы для сухого строительства", "Металлопрокат", "Гидроизоляция" , "Парковочное и дорожное оборудование")
                ),
                Arguments.of(
                        Categories.TOOL,
                        List.of("Ручной инструмент", "Измерительные инструменты", "Специализированный инструмент" , "Генераторы", "Моющая техника", "Электроинструмент", "Высотные конструкции", "Абразивные материалы", "Компрессорное оборудование и аксессуары", "Спецодежда и средства защиты", "Расходные материалы к электроинструменту", "Малярный инструмент", "Хозтовары и расходные материалы", "Газовое и сварочное оборудование", "Силовая, строительная техника и комплектующие", "Штукатурный инструмент", "Хранение инструмента"," Техника и оборудование для уборки")
                ),
                Arguments.of(
                        Categories.ELECTRICAL,
                        List.of("Розетки, выключатели", "Щитовое оборудование", "Товары для разводки кабеля", "Системы безопасности", "Источники питания и зарядные устройства", "Освещение", "Комплектующие для щитов и низковольтного оборудования", "Кабель, провод", "Умный дом", "Стабилизаторы напряжения и ИБП", "Товары для сборки щитка", "Удлинители, разветвители", "Системы прокладки кабеля", "Теплый пол электрический", "Низковольтное оборудование", "Аксессуары для электромонтажа","Монтажные коробки","Молниезащита и заземление")
                ),
                Arguments.of(
                        Categories.ENGINEERING_SYSTEMS,
                        List.of("Водоснабжение", "Предохранительная и регулирующая арматура", "Изоляция для труб","Сантехнический инструмент", "Газоснабжение", "Вентиляция", "Водонагревательное оборудование", "Сантехнический крепеж", "Отопление", "Водоотведение и канализация", "Трубы водоснабжения и отопления", "Герметизация соединений и уплотнители", "Климатические системы", "Насосное оборудование и аксессуары", "Фитинги для водоснабжения и отопления", "Ревизионные люки")
                ),
                Arguments.of(
                        Categories.FINISH,
                        List.of("Двери", "Монтажная пена", "Керамическая плитка и затирки", "Потолочные системы", "Окна и комплектующие", "Клей, жидкие гвозди", "Обои", "Декор стен и потолка", "Фурнитура для дверей", "Лакокрасочные материалы", "Панели для отделки стен и комплектующие", "Пленка самоклеящаяся", "Герметики", "Напольные покрытия", "Декоративный камень", "Уголки и профили отделочные")
                ),
                Arguments.of(
                        Categories.HOME_PRODUCTS,
                        List.of("Кухня", "Декор для дома", "Хранение в доме", "Бытовая техника", "Карнизы, шторы и жалюзи", "Хозяйственно-бытовые товары", "Посуда и кухонная утварь", "Текстиль для дома", "Климатическая техника", "Мебель", "Ковры и коврики для интерьера", "Системы умного дома")
                ),
                Arguments.of(
                        Categories.PLUMBING,
                        List.of("Аксессуары для ванной комнаты", "Ванны, экраны и комплектующие", "Полотенцесушители", "Смесители и душевое оборудование", "Душевые кабины и ограждения", "Сушилки для рук", "Раковины, пьедесталы и комплектующие", "Мебель для ванной комнаты","Аксессуары для общественных мест", "Унитазы и инсталляции", "Сифоны")
                ),
                Arguments.of(
                        Categories.FASTENERS,
                        List.of("Саморезы и шурупы", "Заклепки", "Крепеж сантехнический", "Метрический крепеж", "Такелаж", "Дюбели и дюбель-гвозди", "Крепеж для окон", "Крепление кабеля", "Наборы для крепления", "Хомуты, скобы и ленты", "Гвозди", "Крепеж для фасадных систем", "Кровельный крепеж", "Перфорированный крепеж", "Анкеры", "Крепеж мебельный", "Кронштейны, крюки и профили", "Скрытый крепеж")
                ),
                Arguments.of(
                        Categories.GARDEN_AND_LEISURE,
                        List.of("Гриль и барбекю", "Одежда для отдыха и работы в саду", "Емкости и товары для полива", "Ограждения и заборы", "Уборочная техника и инвентарь", "Товары для бани", "Теплицы, парники и грядки", "Садовые и строительные тачки","Садовые дорожки", "Автотовары", "Товары для отдыха", "Семена и растения", "Садовая техника и комплектующие", "Благоустройство садового участка", "Системы хранения", "Средства защиты от насекомых, грызунов","Уход за растениями", "Садовый инструмент", "Маркизы для террас и веранд", "Хозблоки и бытовки")
                )
        );
    }



}
