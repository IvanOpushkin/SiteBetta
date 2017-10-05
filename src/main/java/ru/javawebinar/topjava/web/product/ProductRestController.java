package ru.javawebinar.topjava.web.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.service.ProductService;
import ru.javawebinar.topjava.to.ProductWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.ProductsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class ProductRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

    private final ProductService service;

    @Autowired
    public ProductRestController(ProductService service) {
        this.service = service;
    }

    public Product get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get product {} for userId={}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete product {} for userId={}", id, userId);
        service.delete(id, userId);
    }

    public List<ProductWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for userId={}", userId);
        return ProductsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public Product create(Product product) {
        int userId = AuthorizedUser.id();
        //int userId = 100000;
        log.info("create {} for userId={}", product, userId);
        checkNew(product);
        return service.create(product, userId);
    }

    public void update(Product product, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={} for userId={}", product, id, userId);
        assureIdConsistent(product, id);
        service.update(product, userId);
    }

    /**
     * <ol>Filter separately
     *   <li>by date</li>
     *   <li>by time for every date</li>
     * </ol>
     */
    public List<ProductWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for userId={}", startDate, endDate, startTime, endTime, userId);

        return ProductsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );
    }

    public List<Product> sortTypePriceAll() {
        int userId = AuthorizedUser.id();
        log.info("getSortedPrice for userId={}", userId);
        return service.sortTypePriceAll(userId);
    }

    public Map<Integer,List<Product>> paging()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedPaging for userId={}", userId);
        return service.paging(userId);
    }


    public Map<Integer,List<Product>> pagingType()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedType for userId={}", userId);
        return service.pagingType(userId);
    }

    public Map<Integer,List<Product>> pagingAlpha()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedAlpha for userId={}", userId);
        return service.pagingAlpha(userId);
    }
    public Map<Integer,List<Product>> pagingProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedProizvod for userId={}", userId);
        return service.pagingProizvod(userId);
    }
    public Map<Integer,List<Product>> pagingEdIzm()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedEdIzm for userId={}", userId);
        return service.pagingEdIzm(userId);
    }
    public Map<Integer,List<Product>> pagingKolvo()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedKolvo for userId={}", userId);
        return service.pagingKolvo(userId);
    }
    public Map<Integer,List<Product>> pagingCena()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedCena for userId={}", userId);
        return service.pagingCena(userId);
    }
    public Map<Integer,List<Product>> pagingFullprice()
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedFullPrice for userId={}", userId);
        return service.pagingFullprice(userId);
    }

    public Map<Integer,List<Product>> search(String searchWord)
    {
        int userId = AuthorizedUser.id();
        log.info("getSortedFullPrice for userId={}", userId);
        return service.search(userId,searchWord);
    }

    //Сетевое оборудование //Сетевое оборудование //Сетевое оборудование //Сетевое оборудование

    public Map<Integer,List<Product>> sortTypeSetevoe()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSetevoe for userId={}", userId);
        return service.sortTypeSetevoe(userId);
    }

    public Map<Integer,List<Product>> sortTypeSetevoeCena()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSetevoeProizvod for userId={}", userId);
        return service.sortTypeSetevoeCena(userId);
    }
    public Map<Integer,List<Product>> sortTypeSetevoeProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSetevoeProizvod for userId={}", userId);
        return service.sortTypeSetevoeProizvod(userId);
    }

    //Лотки//Лотки//Лотки//Лотки//Лотки//Лотки//Лотки//Лотки//Лотки//Лотки


    public Map<Integer,List<Product>> sortTypeLotki()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeLotki for userId={}", userId);
        return service.sortTypeLotki(userId);
    }
    public Map<Integer,List<Product>> sortTypeLotkiCena()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeLotkiCena for userId={}", userId);
        return service.sortTypeLotkiCena(userId);
    }
    public Map<Integer,List<Product>> sortTypeLotkiProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeLotkiProizvod for userId={}", userId);
        return service.sortTypeLotkiProizvod(userId);
    }

    //Электрощитки //Электрощитки //Электрощитки //Электрощитки //Электрощитки //Электрощитки //Электрощитки

    public Map<Integer,List<Product>> sortTypeElektroshit()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeElektroshit for userId={}", userId);
        return service.sortTypeElektroshit(userId);
    }
    public Map<Integer,List<Product>> sortTypeElektroshitCena()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeElektroshitCena for userId={}", userId);
        return service.sortTypeElektroshitCena(userId);
    }
    public Map<Integer,List<Product>> sortTypeElektroshitProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeElektroshitProizvod for userId={}", userId);
        return service.sortTypeElektroshitProizvod(userId);
    }

    //Световое //Световое //Световое //Световое //Световое //Световое //Световое //Световое //Световое //Световое

    public Map<Integer,List<Product>> sortTypeSvetovoe()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSvetovoe for userId={}", userId);
        return service.sortTypeSvetovoe(userId);
    }
    public Map<Integer,List<Product>> sortTypeSvetovoeCena()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSvetovoeCena for userId={}", userId);
        return service.sortTypeSvetovoeCena(userId);
    }
    public Map<Integer,List<Product>> sortTypeSvetovoeProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSvetovoeProizvod for userId={}", userId);
        return service.sortTypeSvetovoeProizvod(userId);
    }

    //Сантехни//Сантехни//Сантехни//Сантехни//Сантехни//Сантехни//Сантехни//Сантехни//Сантехни

    public Map<Integer,List<Product>> sortTypeSantex()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSantex for userId={}", userId);
        return service.sortTypeSantex(userId);
    }
    public Map<Integer,List<Product>> sortTypeSantexCena()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSantexCena for userId={}", userId);
        return service.sortTypeSantexCena(userId);
    }
    public Map<Integer,List<Product>> sortTypeSantexProizvod()
    {
        int userId = AuthorizedUser.id();
        log.info("sortTypeSantexProizvod for userId={}", userId);
        return service.sortTypeSantexProizvod(userId);
    }

    public Map<Integer, List<Product>> sortedPagedList(String action) {

        int userId = AuthorizedUser.id();
        log.info(action + "for userId={}", userId);
        return service.sortedPagedList(action,userId);
    }





}