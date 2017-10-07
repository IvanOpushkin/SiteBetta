package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.repository.ProductRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcProductRepositoryImpl implements ProductRepository {


    //public static int c = 100300;

    private static final int pNumber = 10;

    //all pages
    private static int pPagesN = 0;

    private static Integer pageCurr = 1;

    private static String searchString2="";

    private static final RowMapper<Product> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Product.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertProduct;

    @Autowired
    public JdbcProductRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertProduct = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }




    @Override
    public Product save(Product product, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                //Потом убрать ID дескрипцию и каллории завтра мб
                .addValue("id", product.getId())
                .addValue("description", "")
               .addValue("calories", 0)

                .addValue("date_time", LocalDateTime.now())
                .addValue("type1", product.getType1())
                .addValue("type2", product.getType2())
                .addValue("cod", product.getCod())
                .addValue("naimenovanie", product.getNaimenovanie())
                .addValue("proizvoditel", product.getProizvoditel())
                .addValue("edizmereniya", product.getEdizmereniya())
                .addValue("kolvo", product.getKolvo())
                .addValue("cena", product.getCena())
                .addValue("primechanie", product.getPrimechanie())
                .addValue("articul", product.getArticul())
                //Проблема с ценой не решена
                .addValue("fullprice", product.getFullprice())
                .addValue("picture", product.getPicture())
                .addValue("user_id", userId);

       //ПРОБЛЕМА С СИКВЕНС ID НЕ РЕШЕНА (РЕШЕНА ДОБАВЛЕНИЕ ID МЕТРА В ИНИЦИАЦИЮ DB)
        //System.out.println(product.getId());

        if (product.isNew()) {
            Number newId = this.insertProduct.executeAndReturnKey(map);
            //Number newId = c++;
            product.setId(newId.intValue());


        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE products " +
                           // "   SET calories=:calories, date_time=:date_time " +
                            " SET type1=:type1, type2=:type2, cod=:cod " +
                            ", naimenovanie=:naimenovanie, proizvoditel=:proizvoditel, edizmereniya=:edizmereniya " +
                            ", kolvo=:kolvo, cena=:cena, primechanie=:primechanie, articul=:articul" +
                        ", picture=:picture" +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return product;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM products WHERE id=? AND user_id=?", id, userId) != 0;
    }


    @Override
    public Product get(int id, int userId) {
        List<Product> products = jdbcTemplate.query(
                "SELECT * FROM products WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(products);
    }



    @Override
    public List<Product> getAll(int userId) {

        //List<Product> x = new ArrayList<Product>();
       // Product y = new Product();
       // y.setArticul("3");
       // y.setCena(30);
       // y.setKolvo(30);

       // x.add(y);

        List<Product> x = jdbcTemplate.query(
                "SELECT * FROM products WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);

        //отсортировано правильным компаратором

        for (int i = 0; i<x.size(); i++)
        {
            x.get(i).setPicture(x.get(i).getPicture().replace("основа","osnova"));
        }

        x = x.stream().sorted((o1, o2)->o1.getCod().
                compareTo(o2.getCod()))
                .collect(Collectors.toList());



        return x;
    }

    @Override
    public Map<Integer, List<Product>> sortedPagedList(String action, int userId) {

        List<Product> x = getAll(userId);
        //СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ
       if("sortTypeSetevoe".equals(action) )
        {
            String type1 = "Сетевое оборудование";
            //отсортировано правильным компаратором
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());

            return pagingFORM(x);
        }

        else if("sortTypeSetevoeCena".equals(action))
        {
            String type1 = "Сетевое оборудование";

            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o2.getCena().
                            compareTo(o1.getCena()))
                    .collect(Collectors.toList());

            return pagingFORM(x);

        }
        else if("sortTypeSetevoeProizvod".equals(action))
        {
            String type1 = "Сетевое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1) && !Meal.getProizvoditel().equals(""))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());

            return pagingFORM(x);

        }

        //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ
        else if("sortTypeLotki".equals(action))
        {
            String type1 = "Защитный лоток";
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }
        else if("sortTypeLotkiCena".equals(action))
        {
            String type1 = "Защитный лоток";
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o2.getCena().
                            compareTo(o1.getCena()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }
        else if("sortTypeLotkiProizvod".equals(action))
        {
            String type1 = "Защитный лоток";
            //отсортировано правильным компаратором
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1) && !Meal.getProizvoditel().equals(""))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }

        //ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ
        else if("sortTypeElektroshit".equals(action))
        {
            String type1 = "Электрощитовое";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }
        else if("sortTypeElektroshitCena".equals(action))
        {
            String type1 = "Электрощитовое";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                    .sorted((o1, o2)->o2.getCena().
                            compareTo(o1.getCena()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }
        else if("sortTypeElektroshitProizvod".equals(action))
        {
            String type1 = "Электрощитовое";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }


        //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ
        else if("sortTypeSvetovoe".equals(action))
        {
            String type1 = "Световое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }

        else if("sortTypeSvetovoeCena".equals(action))
        {
            String type1 = "Световое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o2.getCena().
                            compareTo(o1.getCena()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }
        else if("sortTypeSvetovoeProizvod".equals(action))
        {
            String type1 = "Световое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }

        //Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех
        else if("sortTypeSantex".equals(action))
        {
            String type1 = "Сантехническое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }

        else if("sortTypeSantexCena".equals(action))
        {
            String type1 = "Сантехническое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                    .sorted((o1, o2)->o2.getCena().
                            compareTo(o1.getCena()))
                    .collect(Collectors.toList());
            return pagingFORM(x);

        }
        else if("sortTypeSantexProizvod".equals(action))
        {
            String type1 = "Сантехническое оборудование";
            x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                    .sorted((o1, o2)->o1.getCod().
                            compareTo(o2.getCod()))
                    .collect(Collectors.toList());
            return pagingFORM(x);
        }

       //Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы
      //Добавил кусочек в метод и ничего добавлять не надо
       else if("sortTypeComm".equals(action))
       {
           String type1 = "Коммуникационные шкафы";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }

       else if("sortTypeCommCena".equals(action))
       {
           String type1 = "Коммуникационные шкафы";
           x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                   .sorted((o1, o2)->o2.getCena().
                           compareTo(o1.getCena()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }
       else if("sortTypeCommProizvod".equals(action))
       {
           String type1 = "Коммуникационные шкафы";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);
       }

        //Компьютерное оборудование//Компьютерное оборудование//Компьютерное оборудование//Компьютерное оборудование
       //Добавил кусочек в метод и ничего добавлять не надо
       else if("sortTypeKompOborud".equals(action))
       {
           String type1 = "Компьютерное оборудование";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }

       else if("sortTypeKompOborudCena".equals(action))
       {
           String type1 = "Компьютерное оборудование";
           x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                   .sorted((o1, o2)->o2.getCena().
                           compareTo(o1.getCena()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }
       else if("sortTypeKompOborudProizvod".equals(action))
       {
           String type1 = "Компьютерное оборудование";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);
       }

        //Телекомуникационные//Телекомуникационные//Телекомуникационные//Телекомуникационные//Телекомуникационные
       //Добавил кусочек в метод и ничего добавлять не надо
       else if("sortTypeTeleComm".equals(action))
       {
           String type1 = "Телекомуникационные";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }

       else if("sortTypeTeleCommCena".equals(action))
       {
           String type1 = "Телекомуникационные";
           x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                   .sorted((o1, o2)->o2.getCena().
                           compareTo(o1.getCena()))
                   .collect(Collectors.toList());
           return pagingFORM(x);

       }
       else if("sortTypeTeleCommProizvod".equals(action))
       {
           String type1 = "Телекомуникационные";
           x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                   .sorted((o1, o2)->o1.getCod().
                           compareTo(o2.getCod()))
                   .collect(Collectors.toList());
           return pagingFORM(x);
       }

        return pagingFORM(x);
    }

    @Override
    public List<Product> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM products WHERE user_id=?  AND date_time BETWEEN  ? AND ? ORDER BY date_time DESC",
                ROW_MAPPER, userId, startDate, endDate);
    }


    @Override
    public List<Product> sortTypePriceAll(int userId) {

        List<Product> x = jdbcTemplate.query(
                "SELECT * FROM products WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);


        x= x.stream().sorted((o1, o2)->o2.getCena().
                compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return x;
    }
    //Search Engine izi by contains String all check values new x i vse
    @Override
    public Map<Integer,List<Product>> search(int userId, String searchWord) {

        searchString2 = searchWord;

        List<Product> x = getAll(userId);

        if (x.isEmpty())
            return null;

        List<Product> searched = new ArrayList<Product>();

        for (Product t : x) {
            if (t.getType1().toLowerCase().contains(searchWord.toLowerCase()))
                searched.add(t);
            else if (t.getType2().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (String.valueOf(t.getCod()).toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (t.getNaimenovanie().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (t.getProizvoditel().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (t.getEdizmereniya().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (String.valueOf(t.getKolvo()).toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (String.valueOf(t.getCena()).toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (t.getPrimechanie().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (t.getArticul().toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
            else if (String.valueOf(t.getFullprice()).toLowerCase().contains(searchWord.toLowerCase())) searched.add(t);
        }
        return pagingFORM(searched);
    }

    //Paging base
    @Override
    public Map<Integer,List<Product>> paging(int userId) {


        List<Product> x = getAll(userId);

        if (x.isEmpty())
            return null;


        return pagingFORM(x);
    }

    //Разделение типов пример ещё и по номеру попорядку СДЕЛАНО
    @Override
    public Map<Integer,List<Product>> pagingType(int userId)
    { List<Product> x = getAll(userId);

        if (x.isEmpty())
            return null;

        List<Product> type1typeAll = x.stream().filter(meal -> meal.getType2().equals("Тип1")).collect(Collectors.toList());
        List<Product> type2 = x.stream().filter(meal -> meal.getType2().equals("Тип2")).collect(Collectors.toList());
        type1typeAll.addAll(type2);

        x = type1typeAll;

        return pagingFORM(x);
    };

   //Алфавитный порядок наименований
    @Override
    public Map<Integer,List<Product>> pagingAlpha(int userId)
    {List<Product> x = getAll(userId);

    if (x.isEmpty())
            return null;

        x = x.stream().sorted((o1, o2)->o1.getNaimenovanie().
                compareTo(o2.getNaimenovanie())).collect(Collectors.toList());

        return pagingFORM(x);};

    //Разделения по производителю обратное так как Пустые строки будут сверху
    @Override
    public Map<Integer,List<Product>> pagingProizvod(int userId)
    {List<Product> x = getAll(userId);
        if (x.isEmpty())
            return null;
        x = x.stream().sorted((o1, o2)->o2.getProizvoditel().
                compareTo(o1.getProizvoditel())).collect(Collectors.toList());
        return pagingFORM(x);};
    //Разделения по ед Измерениям обратное ок
    @Override
    public Map<Integer,List<Product>> pagingEdIzm(int userId)
    {List<Product> x = getAll(userId);
        if (x.isEmpty())
            return null;

        x = x.stream().sorted((o1, o2)->o2.getEdizmereniya().
                compareTo(o1.getEdizmereniya())).collect(Collectors.toList());

        return pagingFORM(x);};
    //Колво пока с большого количества
    @Override
    public Map<Integer,List<Product>> pagingKolvo(int userId)
    {List<Product> x = getAll(userId);
        if (x.isEmpty())
            return null;
        x = x.stream().sorted((o1, o2)->o2.getKolvo().
                compareTo(o1.getKolvo())).collect(Collectors.toList());
        return pagingFORM(x);};
    //Цена пока с большей цены т.к есть много пустых
    @Override
    public Map<Integer,List<Product>> pagingCena(int userId)
    {List<Product> x = getAll(userId);
        if (x.isEmpty())
            return null;
        x = x.stream().sorted((o1, o2)->o2.getCena().
                compareTo(o1.getCena())).collect(Collectors.toList());
        return pagingFORM(x);};
    //Общая цена тоже с пустыми
    @Override
    public Map<Integer,List<Product>> pagingFullprice(int userId)
    {List<Product> x = getAll(userId);
        if (x.isEmpty())
            return null;
        x = x.stream().sorted((o1, o2)->o2.getFullprice().
                compareTo(o1.getFullprice())).collect(Collectors.toList());
        return pagingFORM(x);};

    public Map<Integer,List<Product>> pagingFORM(List<Product> x)
    {
        Map<Integer,List<Product>> products = new HashMap<Integer,List<Product>>();

        int pNumberHelper = 0;
        //int pNumberHelper2 = pNumber;
        int xSize = x.size();
        int mxSizeHelper = -1;
        //pageNumber from 1
        int mapInt = 1;

       int fullPartPrice = 0;

       //Аккуратно до пэйджинга установили всю цену продуктов, определённого типа.

       //Формирование полной цены
       for (Product good:x)
        {
            fullPartPrice+=(good.getKolvo()*good.getCena());
        }
        //Установка полной цены каждому типовому уже кусочку
        for (Product good:x)
        {
            good.setFullPriceOfType(fullPartPrice);
        }


        while (pNumberHelper < xSize)
        {
            //initial capacity 5 по идее как массив 5 = 4
            List<Product> product1List = new ArrayList<Product>(pNumber);

            outerloop:
            for (int i = pNumberHelper; i<pNumberHelper + pNumber;i++)
            {
                mxSizeHelper++;
                if (mxSizeHelper >= xSize)
                    //вообще можно присвоить значение намбер хэлперу больше и просто брэйкнуть
                    break outerloop;

                product1List.add(x.get(mxSizeHelper));
            }

            products.put(mapInt,product1List);
            mapInt++;
            pNumberHelper+=pNumber;
            // pNumberHelper2+=pNumber;

        }


        if (xSize%pNumber > 0)
            pPagesN = xSize/pNumber + 1;
        else pPagesN = xSize/pNumber;

        return products;


    }


    @Override public Map<Integer, List<Product>> sortTypeSetevoe(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeCena(int userId) {
        return null;
    }

    @Override
    public Map<Integer, List<Product>> sortTypeSetevoeProizvod(int userId) {
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

    public static int getpPagesN() {
        return pPagesN;
    }

    public static void setpPagesN(int pPagesN) {
        JdbcProductRepositoryImpl.pPagesN = pPagesN;
    }

    public static Integer getPageCurr() {
        return pageCurr;
    }

    public void setPageCurr(Integer pageCurr) {
        this.pageCurr = pageCurr;
    }

    public static String getSearchString2() {
        return searchString2;
    }

    public static void setSearchString2(String searchString2) {
        JdbcProductRepositoryImpl.searchString2 = searchString2;
    }
}


  /*
    @Override
    public Map<Integer,List<Product>> sortTypeSetevoe(int userId) {

        String type1 = "Сетевое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSetevoeCena(int userId) {

        String type1 = "Сетевое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                .sorted((o1, o2)->o2.getCena().
                        compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSetevoeProizvod(int userId) {

        String type1 = "Сетевое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1) && !Meal.getProizvoditel().equals(""))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }

    @Override
    public Map<Integer,List<Product>> sortTypeLotki(int userId) {

        String type1 = "Защитный лоток";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeLotkiCena(int userId) {

        String type1 = "Защитный лоток";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1))
                .sorted((o1, o2)->o2.getCena().
                        compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeLotkiProizvod(int userId) {

        String type1 = "Защитный лоток";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().equals(type1) && !Meal.getProizvoditel().equals(""))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    //Электрощитовое//Электрощитовое//Электрощитовое//Электрощитовое//Электрощитовое//Электрощитовое
    @Override
    public Map<Integer,List<Product>> sortTypeElektroshit(int userId) {

        String type1 = "Электрощитовое";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());


        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeElektroshitCena(int userId) {

        String type1 = "Электрощитовое";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o2.getCena().
                        compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeElektroshitProizvod(int userId) {

        String type1 = "Электрощитовое";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }


    //По световому//По световому//По световому//По световому//По световому//По световому//По световому
    @Override
    public Map<Integer,List<Product>> sortTypeSvetovoe(int userId) {

        String type1 = "Световое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());


        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSvetovoeCena(int userId) {

        String type1 = "Световое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o2.getCena().
                        compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSvetovoeProizvod(int userId) {

        String type1 = "Световое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }

    //По сантехнич//По сантехнич//По сантехнич//По сантехнич//По сантехнич//По сантехнич//По сантехнич//По сантехнич
    @Override
    public Map<Integer,List<Product>> sortTypeSantex(int userId) {

        String type1 = "Сантехническое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());


        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSantexCena(int userId) {

        String type1 = "Сантехническое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1))
                .sorted((o1, o2)->o2.getCena().
                        compareTo(o1.getCena()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }
    @Override
    public Map<Integer,List<Product>> sortTypeSantexProizvod(int userId) {

        String type1 = "Сантехническое оборудование";

        List<Product> x = getAll(userId);
        //отсортировано правильным компаратором

        x = x.stream().filter(Meal -> Meal.getType1().contains(type1) && !Meal.getProizvoditel().equals(""))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());

        return pagingFORM(x);
    }

*/


//СОРИТРОВКА ТИПА СЕТЕВОЕ. ПОТОМ КОПИ НА ДВА ДРУГИХ ТИПА СМЕНА 1го СЛОВА//

   /* @Override
    public List<Product> sortTypeSetevoe(int userId) {

        List<Product> x = jdbcTemplate.query(
                "SELECT * FROM meals WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);

        //отсортировано правильным компаратором

        x = x.stream().filter(Product -> Product.getType2().equals("Тип2"))
                .sorted((o1, o2)->o1.getCod().
                        compareTo(o2.getCod()))
                .collect(Collectors.toList());


        return x;
    }
    */

  /*
   // @Override
    public Map<Integer,List<Product>> pagingTypesTypes(int userId) {

        //pNumber
        //Метод который будет вставлятся во все методы после сортировки. Нужна правильная очерёдность не обратная.
        //Базовая сортировка включена в метод getAll.

        // List<Product> x = sortType2(userId);

        List<Product> x = getAll(userId);

        x = x.stream().sorted((o1, o2)->o2.getType1().
                compareTo(o1.getType2())).collect(Collectors.toList());

        //МОМЕНТ НУЖНОЙ СОРТИРОВКИ.



        if (x.isEmpty())
            return null;

        Map<Integer,List<Product>> products = new HashMap<Integer,List<Product>>();

        int pNumberHelper = 0;
        //int pNumberHelper2 = pNumber;
        int xSize = x.size();
        int mxSizeHelper = -1;
        //pageNumber from 1
        int mapInt = 1;



        while (pNumberHelper < xSize)
        {
            //initial capacity 5 по идее как массив 5 = 4
            List<Product> product1List = new ArrayList<Product>(pNumber);

            outerloop:
            for (int i = pNumberHelper; i<pNumberHelper + pNumber;i++)
            {
                mxSizeHelper++;
                if (mxSizeHelper >= xSize)
                    //вообще можно присвоить значение намбер хэлперу больше и просто брэйкнуть
                    break outerloop;

                product1List.add(x.get(mxSizeHelper));
            }

            products.put(mapInt,product1List);
            mapInt++;
            pNumberHelper+=pNumber;
            // pNumberHelper2+=pNumber;

        }


        if (xSize%pNumber > 0)
            pPagesN = xSize/pNumber + 1;
        else pPagesN = xSize/pNumber;



        return products;
    }
    */


   /*
    public static void main(String[] args)
    {
        int xSize = 3;
        int pNumber=2;

        System.out.println(xSize%pNumber);
    }
    */


   /*
    @Override
    public List<Product> getAll(int userId) {



        List<Product> x = new ArrayList<Product>();
        new Product(100000,LocalDateTime.of(2017,8,28,16,56,3),"",0,"Сетевое оборудование","Тип1",2,"Бронированный кабель КВБбШнг 27x1","","Метры",183,0,"проверить метры и наименование");
        x.add(new Product(100000,LocalDateTime.of(2017,8,28,16,56,3),"",0,"Сетевое оборудование","Тип1",2,"Бронированный кабель КВБбШнг 27x1","","Метры",183,0,"проверить метры и наименование"));
        x.add(new Product(100000,LocalDateTime.of(2017,08,28,17,2, 2), "",0,"Сетевое оборудование","Тип2",3,"Бронированный кабель КВБбШв 4x1,0","","Метры",90,0,"проверить метры"));
);
        //отсортировано правильным компаратором

        x = x.stream().sorted((o1, o2)->o1.getCod().
                compareTo(o2.getCod()))
                .collect(Collectors.toList());


        return x;
    }
    */

//Разделение типов пример ещё и по номеру попорядку СДЕЛАНО
        /*
        List<Product> type1typeAll = x.stream().filter(product -> product.getType2().equals("Тип1")).collect(Collectors.toList());
        List<Product> type2 = x.stream().filter(product -> product.getType2().equals("Тип2")).collect(Collectors.toList());
        type1typeAll.addAll(type2);

        x = type1typeAll;*/



//Разделения по алфавиту
        /*
          x = x.stream().sorted((o1, o2)->o1.getNaimenovanie().
                    compareTo(o2.getNaimenovanie())).collect(Collectors.toList());
         */


//Разделения по производителю обратное так как Пустые строки будут сверху
        /*

        x = x.stream().sorted((o1, o2)->o2.getProizvoditel().
                compareTo(o1.getProizvoditel())).collect(Collectors.toList());
         */


//Разделения по ед Измерениям обратное ок
        /*

        x = x.stream().sorted((o1, o2)->o2.getEdizmereniya().
                compareTo(o1.getEdizmereniya())).collect(Collectors.toList());
         */


//Колво пока с большого количества
        /*
         x = x.stream().sorted((o1, o2)->o2.getKolvo().
                compareTo(o1.getKolvo())).collect(Collectors.toList());
                 */



//Цена пока с большей цены т.к есть много пустых
        /*
        x = x.stream().sorted((o1, o2)->o2.getCena().
                compareTo(o1.getCena())).collect(Collectors.toList());

           */


//Общая цена тоже с пустыми
        /*

        x = x.stream().sorted((o1, o2)->o2.getFullprice().
                compareTo(o1.getFullprice())).collect(Collectors.toList());


           */



