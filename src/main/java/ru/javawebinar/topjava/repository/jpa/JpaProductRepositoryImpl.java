package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class JpaProductRepositoryImpl implements ProductRepository {

   /*
    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager em;

   /*
    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

*/
    @Override
    public Product save(Product product, int userId) {
        if (product.isNew()) {
            em.persist(product);
            return product;
        } else {

            //типо Юзеру настроили ID. Которому обновим еду.
            // Связь уже настроена через fk автозапуск связи.
            User user = product.getUser();
            user.setId(userId);
            product.setUser(user);

            return em.merge(product);
        }
    }



    @Override
    public boolean delete(int id, int userId)
    {
       // List<Product> users = em.createNamedQuery(query string);

        return false;
    }

    @Override
    public Product get(int id, int userId) {
        return null;
    }

    @Override
    public List<Product> getAll(int userId) {
        return null;
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }


    @Override
    public Map<Integer, List<Product>> sortedPagedList(String action, int userId) {
        return null;
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
}