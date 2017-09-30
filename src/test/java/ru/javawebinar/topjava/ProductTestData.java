package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Product;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class ProductTestData {

    public static final BeanMatcher<Product> MATCHER = new BeanMatcher<>();

    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final Product PRODUCT_1 = new Product(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Product PRODUCT_2 = new Product(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Product PRODUCT_3 = new Product(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Product PRODUCT_4 = new Product(MEAL1_ID + 3, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Product PRODUCT_5 = new Product(MEAL1_ID + 4, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Product PRODUCT_6 = new Product(MEAL1_ID + 5, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Product ADMIN_PRODUCT_1 = new Product(ADMIN_MEAL_ID, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final Product ADMIN_PRODUCT_2 = new Product(ADMIN_MEAL_ID + 1, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<Product> PRODUCTS = Arrays.asList(PRODUCT_6, PRODUCT_5, PRODUCT_4, PRODUCT_3, PRODUCT_2, PRODUCT_1);

    public static Product getCreated() {
        return new Product(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Product getUpdated() {
        return new Product(MEAL1_ID, PRODUCT_1.getDateTime(), "Обновленный завтрак", 200);
    }
}
