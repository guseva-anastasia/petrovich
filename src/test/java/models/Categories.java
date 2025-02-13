package models;

public enum Categories {
    BUILDING_MATERIALS ("Стройматериалы"),
    TOOL("Инструмент"),
    ELECTRICAL ("Электрика"),
    ENGINEERING_SYSTEMS("Инженерные системы"),
    FINISH("Финишная отделка"),
    HOME_PRODUCTS("Товары для дома"),
    PLUMBING("Сантехника"),
    FASTENERS("Крепеж"),
    GARDEN_AND_LEISURE("Сад и досуг"),
    NEW("Новинки");

    public final String description;

    Categories(String description) {
        this.description = description;
    }
}
