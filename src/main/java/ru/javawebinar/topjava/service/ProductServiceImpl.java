package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public List<Product> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public List<Product> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Product update(Product product, int userId) {
        return checkNotFoundWithId(repository.save(product, userId), product.getId());
    }

    @Override
    public Product create(Product product, int userId) {
        Assert.notNull(product, "product must not be null");
        return repository.save(product, userId);
    }

    @Override
    public List<Product> sortTypePriceAll(int userId) {
        return repository.sortTypePriceAll(userId);
    }

    @Override
    public Map<Integer, List<Product>> paging(int userId) {
        return repository.paging(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingType(int userId) {
        return repository.pagingType(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingAlpha(int userId) {
        return repository.pagingAlpha(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingProizvod(int userId) {
        return repository.pagingProizvod(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingEdIzm(int userId) {
        return repository.pagingEdIzm(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingKolvo(int userId) {
        return repository.pagingKolvo(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingCena(int userId) {
        return repository.pagingCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> pagingFullprice(int userId) {
        return repository.pagingFullprice(userId);
    }

    @Override
    public Map<Integer, List<Product>> search(int userId, String searchWord) {
        return repository.search(userId,searchWord);
    }


    @Override
    public Map<Integer, List<Product>> sortTypeSetevoe(int userId) {
        return repository.sortTypeSetevoe(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeCena(int userId) {
        return repository.sortTypeSetevoeCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeProizvod(int userId) {
        return repository.sortTypeSetevoeProizvod(userId);
    }



    @Override
    public Map<Integer, List<Product>> sortTypeLotki(int userId) {
        return repository.sortTypeLotki(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeLotkiCena(int userId) {
        return repository.sortTypeLotkiCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeLotkiProizvod(int userId) {
        return repository.sortTypeLotkiProizvod(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshit(int userId) {
        return repository.sortTypeElektroshit(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshitCena(int userId) {
        return repository.sortTypeElektroshitCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeElektroshitProizvod(int userId) {
        return repository.sortTypeElektroshitProizvod(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoe(int userId) {
        return repository.sortTypeSvetovoe(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoeCena(int userId) {
        return repository.sortTypeSvetovoeCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSvetovoeProizvod(int userId) {
        return repository.sortTypeSvetovoeProizvod(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantex(int userId) {
        return repository.sortTypeSantex(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantexCena(int userId) {
        return repository.sortTypeSantexCena(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSantexProizvod(int userId) {
        return repository.sortTypeSantexProizvod(userId);
    }

    @Override
    public Map<Integer, List<Product>> sortedPagedList(String action, int userId) {
        return repository.sortedPagedList(action,userId);
    }
}