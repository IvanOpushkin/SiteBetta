package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.repository.ProductRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryProductRepositoryImpl implements ProductRepository

{
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    // Map  userId -> (mealId-> product)
    private Map<Integer, Map<Integer, Product>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Product save(Product product, int userId) {
        Objects.requireNonNull(product);

        /*
        if (product.isNew()) {
            product.setId(counter.incrementAndGet());
        } else if (get(product.getId(), userId) == null) {
            return null;
        }
        Map<Integer, Product> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        return meals.put(product.getId(), product);
*/

        Map<Integer, Product> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (product.isNew()) {
            product.setId(counter.incrementAndGet());
            return meals.put(product.getId(), product);
        }
        return meals.computeIfPresent(product.getId(), (id, oldMeal) -> product);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Product> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Product get(int id, int userId) {
        Map<Integer, Product> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public List<Product> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(userId)
                .filter(um -> DateTimeUtil.isBetween(um.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Stream<Product> getAllAsStream(int userId) {
        Map<Integer, Product> meals = repository.get(userId);
        return meals == null ?
                Stream.empty() :
                meals.values().stream()
                        .sorted(Comparator.comparing(Product::getDateTime).reversed());
    }



    @Override
    public List<Product> sortTypePriceAll(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> paging(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingType(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingAlpha(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingEdIzm(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingKolvo(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> pagingFullprice(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> search(int userId, String searchWord) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoe(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeLotki(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeLotkiCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeLotkiProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshit(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshitCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshitProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoe(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoeCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoeProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantex(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantexCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantexProizvod(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortedPagedList(String action, int userId) {
        return null;
    }
}