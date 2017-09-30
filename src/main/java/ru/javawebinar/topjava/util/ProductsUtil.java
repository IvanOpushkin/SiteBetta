package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.to.ProductWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProductsUtil {
    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    private ProductsUtil() {
    }

    public static List<ProductWithExceed> getWithExceeded(Collection<Product> products, int caloriesPerDay) {
        return getFilteredWithExceeded(products, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<ProductWithExceed> getFilteredWithExceeded(Collection<Product> products, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = products.stream()
                .collect(
                        Collectors.groupingBy(Product::getDate, Collectors.summingInt(Product::getCalories))
//                      Collectors.toMap(Product::getDate, Product::getCalories, Integer::sum)
                );

        return products.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<ProductWithExceed> getFilteredWithExceededByCycle(List<Product> products, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        products.forEach(meal -> caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum));

        final List<ProductWithExceed> mealsWithExceeded = new ArrayList<>();
        products.forEach(meal -> {
            if (DateTimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
                mealsWithExceeded.add(createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }

    /*
     *  Advanced solution in one return (listDayMeals can be inline).
     *  Streams are not multiplied, so complexity is still O(N)
     *  Execution time is increased as for every day we create 2 additional streams
     */
    public static List<ProductWithExceed> getFilteredWithExceededInOneReturn(List<Product> products, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Collection<List<Product>> listDayMeals = products.stream()
                .collect(Collectors.groupingBy(Product::getDate)).values();

        return listDayMeals
                .stream().flatMap(dayMeals -> {
                    boolean exceed = dayMeals.stream().mapToInt(Product::getCalories).sum() > caloriesPerDay;
                    return dayMeals.stream().filter(meal ->
                            DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                            .map(meal -> createWithExceed(meal, exceed));
                })
                .collect(Collectors.toList());
    }

    public static ProductWithExceed createWithExceed(Product product, boolean exceeded) {

        ProductWithExceed okey = new ProductWithExceed(product.getId(), product.getDateTime(), product.getDescription(), product.getCalories(), exceeded,
                product.getType1(), product.getType2(), product.getCod(), product.getNaimenovanie(),
                product.getProizvoditel(), product.getEdizmereniya(), product.getKolvo(), product.getCena(),
                product.getPrimechanie(), product.getPicture(), product.getArticul());

        return okey;
    }
}