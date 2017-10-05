package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ProductRepository {
    // null if updated product do not belong to userId
    Product save(Product product, int userId);

    // false if product do not belong to userId
    boolean delete(int id, int userId);

    // null if product do not belong to userId
    Product get(int id, int userId);

    // ORDERED dateTime
    List<Product> getAll(int userId);

    // ORDERED dateTime
    List<Product> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    //Методы понимания (под кэш в страничке)

    List<Product> sortTypePriceAll(int userId);

    Map<Integer, List<Product>> paging(int userId);

    //Разделение типов пример ещё и по номеру попорядку СДЕЛАНО
    Map<Integer, List<Product>> pagingType(int userId);

    //Разделения по алфавиту
    Map<Integer, List<Product>> pagingAlpha(int userId);

    //Разделения по производителю обратное так как Пустые строки будут сверху
    Map<Integer, List<Product>> pagingProizvod(int userId);

    //Разделения по ед Измерениям обратное ок
    Map<Integer, List<Product>> pagingEdIzm(int userId);

    //Колво пока с большого количества
    Map<Integer, List<Product>> pagingKolvo(int userId);

    //Цена пока с большей цены т.к есть много пустых
    Map<Integer, List<Product>> pagingCena(int userId);

    //Общая цена тоже с пустыми
    Map<Integer, List<Product>> pagingFullprice(int userId);

    Map<Integer, List<Product>> search(int userId, String searchWord);

    //По Сетевому оборудованию
    Map<Integer,List<Product>> sortTypeSetevoe(int userId);
    Map<Integer,List<Product>> sortTypeSetevoeCena(int userId);
    Map<Integer,List<Product>> sortTypeSetevoeProizvod(int userId);

    //По лоткам
    Map<Integer,List<Product>> sortTypeLotki(int userId);
    Map<Integer,List<Product>> sortTypeLotkiCena(int userId);
    Map<Integer,List<Product>> sortTypeLotkiProizvod(int userId);

    //По электрощитам
    Map<Integer,List<Product>> sortTypeElektroshit(int userId);
    Map<Integer,List<Product>> sortTypeElektroshitCena(int userId);
    Map<Integer,List<Product>> sortTypeElektroshitProizvod(int userId);

    //По световому
    Map<Integer,List<Product>> sortTypeSvetovoe(int userId);
    Map<Integer,List<Product>> sortTypeSvetovoeCena(int userId);
    Map<Integer,List<Product>>  sortTypeSvetovoeProizvod(int userId);

    //По сантех
    Map<Integer,List<Product>> sortTypeSantex(int userId);
    Map<Integer,List<Product>> sortTypeSantexCena(int userId);
    Map<Integer,List<Product>>  sortTypeSantexProizvod(int userId);

    //Метод улучшение
    Map<Integer,List<Product>> sortedPagedList(String action, int userId);

    //Если банально сменить колво страниц до такойто цифры + номер. Умный цикл. ПО ПЭЙДЖИНГУ.
    //Мир живёт по себе, настроить его кусочек под себя, не мешая никак миру. (Мысли об Ароняне)






}
