package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Product;
import ru.javawebinar.topjava.web.product.ProductRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.jdbc.JdbcProductRepositoryImpl.getPageCurr;
import static ru.javawebinar.topjava.repository.jdbc.JdbcProductRepositoryImpl.getSearchString2;
import static ru.javawebinar.topjava.repository.jdbc.JdbcProductRepositoryImpl.getpPagesN;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class ProductServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private ProductRestController productController;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        productController = springContext.getBean(ProductRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            //Сделать зеркальный конструктор с null аналогично
            Product product = new Product(null,
                    // LocalDateTime.parse(request.getParameter("dateTime")),
                    LocalDateTime.now(),
                    // request.getParameter("description"),
                    "",
                    //  Integer.parseInt(request.getParameter("calories")),
                    0,
                    request.getParameter("type1"),
                    request.getParameter("type2"),

                    request.getParameter("cod").equals("")? 0 : Integer.parseInt(request.getParameter("cod")),
                    request.getParameter("naimenovanie"),
                    request.getParameter("proizvoditel"),
                    request.getParameter("edizmereniya"),
                    request.getParameter("kolvo").equals("")? 0 : Integer.parseInt(request.getParameter("kolvo")),
                    request.getParameter("cena").equals("")? 0 : Integer.parseInt(request.getParameter("cena")),
                    request.getParameter("primechanie"),
                    request.getParameter("picture"),
                    request.getParameter("articul")

                    //По идее фулпрайс просто геттером возьмёца
            );

            System.out.println(request.getParameter("id"));

            if (request.getParameter("id").isEmpty()) {
                productController.create(product);
            } else {
                productController.update(product, getId(request));
            }
            response.sendRedirect("products?action=paging");

        } else if ("filter".equals(action)) {
            LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
            LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
            LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
            LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
            request.setAttribute("products", productController.getBetween(startDate, startTime, endDate, endTime));
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        } else if ("paging".equals(action))
        {

            // int pageN=1;

            // if (request.getParameter("pageN") != null)
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            //JdbcProductRepositoryImpl.setPageCurr(pageN);
            request.setAttribute("productsPaging", productController.paging().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            //request.setAttribute("pageCurr", JdbcProductRepositoryImpl.getPageCurr());

            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }


        //СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ
        //ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ//ВСЁ
        else if("sortTypeSetevoe".equals(action) || "sortTypeSetevoeCena".equals(action) || "sortTypeSetevoeProizvod".equals(action)
                || "sortTypeLotki".equals(action) || "sortTypeLotkiCena".equals(action) || "sortTypeLotkiProizvod".equals(action)
        || "sortTypeElektroshit".equals(action) || "sortTypeElektroshitCena".equals(action) || "sortTypeElektroshitProizvod".equals(action)
                || "sortTypeSvetovoe".equals(action) || "sortTypeSvetovoeCena".equals(action) || "sortTypeSvetovoeProizvod".equals(action)
                || "sortTypeSantex".equals(action) || "sortTypeSantexCena".equals(action) || "sortTypeSantexProizvod".equals(action)
                || "sortTypeComm".equals(action) || "sortTypeCommCena".equals(action) || "sortTypeCommProizvod".equals(action)
                || "sortTypeKompOborud".equals(action) || "sortTypeKompOborudCena".equals(action) || "sortTypeKompOborudProizvod".equals(action)
                || "sortTypeTeleComm".equals(action) || "sortTypeTeleCommCena".equals(action) || "sortTypeTeleCommProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));

            //JdbcProductRepositoryImpl.setPageCurr(pageN);

            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            //Странно заработало с цветом для нажатий
            //--Инпут Тайп связан с нажатием каждой отдельной кнопкой шлёт инфу --
            request.setAttribute("pageCurr", pageN);
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);

        }


        //Вторая часть по базе данных

        else if ("pagingType".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingType().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingAlpha".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingAlpha().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingProizvod().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingEdIzm".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingEdIzm().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingKolvo".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingKolvo().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingCena().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("pagingFullprice".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.pagingFullprice().get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("search".equals(action))
        {
            int pageN=1;

            if (request.getParameter("pageN") != null && !request.getParameter("pageN").equals(""))
                pageN = Integer.parseInt(request.getParameter("pageN"));


            String searchString = request.getParameter("searchString");

            if (searchString != null) request.setAttribute("productsPaging", productController.search(searchString).get(pageN));
            else request.setAttribute("productsPaging", productController.search(getSearchString2()).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("searchCena".equals(action))
        {
            int pageN=1;

            if (request.getParameter("pageN") != null && !request.getParameter("pageN").equals(""))
                pageN = Integer.parseInt(request.getParameter("pageN"));


            String searchString = request.getParameter("searchString");

            if (searchString != null) request.setAttribute("productsPaging", productController.search(searchString).get(pageN));
            else request.setAttribute("productsPaging", productController.search(getSearchString2()).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if ("searchProizvod".equals(action))
        {
            int pageN=1;

            if (request.getParameter("pageN") != null && !request.getParameter("pageN").equals(""))
                pageN = Integer.parseInt(request.getParameter("pageN"));


            String searchString = request.getParameter("searchString");

            if (searchString != null) request.setAttribute("productsPaging", productController.search(searchString).get(pageN));
            else request.setAttribute("productsPaging", productController.search(getSearchString2()).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }




    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //action = action +
        //int page = Integer.parseInt(request.getParameter("page"));

        List<Product> productList = new ArrayList<>();


        switch (action == null ? "all" : action) {

            //СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ
            case "sortTypeSetevoe":
            case "sortTypeSetevoeCena":
            case "sortTypeSetevoeProizvod":
            case "sortTypeLotki":
            case "sortTypeLotkiCena":
            case "sortTypeLotkiProizvod":
            case "sortTypeElektroshit":
            case "sortTypeElektroshitCena":
            case "sortTypeElektroshitProizvod":
            case "sortTypeSvetovoe":
            case "sortTypeSvetovoeCena":
            case "sortTypeSvetovoeProizvod":
            case "sortTypeSantex":
            case "sortTypeSantexCena":
            case "sortTypeSantexProizvod":
            case "sortTypeComm":
            case "sortTypeCommCena":
            case "sortTypeCommProizvod":
            case "sortTypeKompOborud":
            case "sortTypeKompOborudCena":
            case "sortTypeKompOborudProizvod":
            case "sortTypeTeleComm":
            case "sortTypeTeleCommCena":
            case "sortTypeTeleCommProizvod":
//                int pageN = Integer.parseInt(request.getParameter("pageN"));
                request.setAttribute("productsPaging", productController.sortedPagedList(action).get(1));
                //по идее после первой строчки хэшируется всё по производ типам

               // JdbcProductRepositoryImpl.setPageCurr(pageN);

                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("currPage", getPageCurr());
                //System.out.println(getpPagesN());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            //По сортировкам конец части 1. Для сайта
            //Кнопки только для таблицы.
            //Сайто страница тестовое


            case "mirrorPaging":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.paging().get(2));
                request.setAttribute("maxPages", getpPagesN());
               request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;



            //paging основная
            case "paging":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.paging().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("currPage", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;


            case "pagingType":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingType().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingAlpha":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingAlpha().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingProizvod":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingProizvod().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingEdIzm":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingEdIzm().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingKolvo":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingKolvo().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingCena":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingCena().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
            case "pagingFullprice":
                // Object numberPage = request.getAttribute("number");
                // int numberP = (int) numberPage;
                request.setAttribute("productsPaging", productController.pagingFullprice().get(1));
                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("pageCurr", getPageCurr());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;


            case "delete":
                int id = getId(request);
                productController.delete(id);
                request.getRequestDispatcher("products?action=paging").forward(request, response);
                break;
            case "create":
            case "update":
                final Product product = "create".equals(action) ?
                        new Product(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        productController.get(getId(request));
                request.setAttribute("product", product);
                request.getRequestDispatcher("/productForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("productsPaging", productController.sortedPagedList(action).get(1));
                //по идее после первой строчки хэшируется всё по производ типам

                // JdbcProductRepositoryImpl.setPageCurr(pageN);

                request.setAttribute("maxPages", getpPagesN());
                request.setAttribute("currPage", getPageCurr());
                //System.out.println(getpPagesN());
                request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}



                /*
            //Разделение типов пример ещё и по номеру попорядку СДЕЛАНО
            Map<Integer,List<Product>> pagingType(int userId);
            //Разделения по алфавиту
            Map<Integer,List<Product>> pagingAlpha(int userId);
            //Разделения по производителю обратное так как Пустые строки будут сверху
            Map<Integer,List<Product>> pagingProizvod(int userId);
            //Разделения по ед Измерениям обратное ок
            Map<Integer,List<Product>> pagingEdIzm(int userId);
            //Колво пока с большого количества
            Map<Integer,List<Product>> pagingKolvo(int userId);
            //Цена пока с большей цены т.к есть много пустых
            Map<Integer,List<Product>> pagingCena(int userId);
            //Общая цена тоже с пустыми
            Map<Integer,List<Product>> pagingFullprice(int userId);
            */



/*
 //СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ//СЕТЕВОЕ
        else if("sortTypeSetevoe".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeSetevoeCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeSetevoeProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ //ЛОТКИ
        else if("sortTypeLotki".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeLotkiCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeLotkiProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ//ЭЛЕКТРОЩИТЫ
        else if("sortTypeElektroshit".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeElektroshitCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeElektroshitProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }


        //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ //СВЕТОВОЕ
        else if("sortTypeSvetovoe".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeSvetovoeCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeSvetovoeProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех//Сантех
        else if("sortTypeSantex".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeSantexCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeSantexProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы//Коммуникационные шкафы
        else if("sortTypeComm".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeCommCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeCommProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //Компьютерное оборудование//Компьютерное оборудование//Компьютерное оборудование//Компьютерное оборудование
        //Или ВСЁ В ОДИН ОР. ОДИН ОР СПОСОБ УБРАТЬ КУЧУ ТЕКСТА
        else if("sortTypeKompOborud".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeKompOborudCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeKompOborudProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        //Телекомуникационные//Телекомуникационные//Телекомуникационные//Телекомуникационные//Телекомуникационные
        //Или ВСЁ В ОДИН ОР. ОДИН ОР СПОСОБ УБРАТЬ КУЧУ ТЕКСТА
        else if("sortTypeTeleComm".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }

        else if("sortTypeTeleCommCena".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }
        else if("sortTypeTeleCommProizvod".equals(action))
        {
            int pageN = Integer.parseInt(request.getParameter("pageN"));
            request.setAttribute("productsPaging", productController.sortedPagedList(action).get(pageN));
            request.setAttribute("maxPages", getpPagesN());
            request.getRequestDispatcher("/productsPaging.jsp").forward(request, response);
        }


 */