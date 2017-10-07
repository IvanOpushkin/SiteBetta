<%@ page import="ru.javawebinar.topjava.repository.jdbc.JdbcProductRepositoryImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %> --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="templatemo_style.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" href="nivo-slider.css" type="text/css" media="screen" />
    <link rel="stylesheet" type="text/css" href="css/ddsmoothmenu.css" />

    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/ddsmoothmenu.js"></script>

    <title>Оборудование Хабаровск</title>
    <link rel="stylesheet" href="css/style.css">

    <script type="text/javascript">

        ddsmoothmenu.init({
            mainmenuid: "top_nav", //menu DIV id
           orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
           classname: 'ddsmoothmenu', //class added to menu's outer DIV
           // customtheme: ["cadetblue", "#18374a"],
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        })

    </script>


    <style>

        /*
        input {
            line-height: 2em;  2em is (2 * default line height)
        } */

        /**
   * Grey
   */
        a.button {
            -moz-box-shadow: inset 0 0 0 1px #63ad0d;
            -webkit-box-shadow: inset 0 0 0 1px #63ad0d;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            background: #eee;
            background: -webkit-gradient(linear, 0 0, 0 bottom, from(#eee), to(#e2e2e2));
            background: -moz-linear-gradient(#eee, #e2e2e2);
            background: linear-gradient(#eee, #e2e2e2);

            box-shadow: inset 0 0 0 1px #f5f5f5;
            color: #555;
            display: inline-block;
            font: bold 12px Arial, Helvetica, Clean, sans-serif;
            margin: 0 10px 10px 0;
            padding: 6px 6px;
            position: relative;
            text-align: center;
            text-decoration: none;
            text-shadow: 0 1px 0 #fafafa; }

        a.button:hover {
            background: #e4e4e4;
            background: -webkit-gradient(linear, 0 0, 0 bottom, from(#e4e4e4), to(#ededed));
            background: -moz-linear-gradient(#e4e4e4, #ededed);
            background: linear-gradient(#e4e4e4, #ededed);
        }






        html, body{
            background-color: white; /* Цвет фона веб-страницы */
            /*Удаление картинки с фона*/
            background-image: url(images/Безымянный.png);
            background-repeat:no-repeat;
        }


        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
        }

        a:visited{
            color:#4da6ff;
        }

        .button1 {
            background-color: #82DF86; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 20pt;
        }

        .button1:visited {
            color: white;
        }

        .button2 {
            background-color: white;
            border: 1px solid #F0FFF0;
            color: cadetblue;
            /* 15 32*/
            padding: 10px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 9pt;
        }
        .button3 {
            background-color: white;
            border: 1px solid #F0FFF0;
            color: red;
            /* 15 32*/
            padding: 10px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 9pt;
        }

        .button2:visited {
            color: white;
        }


        #outer
        {
            width:100%;
            text-align: center;
        }

        #outer2
        {
            width:100%;
            text-align: center;
        }

        #outer3
        {
            width:100%;
            text-align: center;
        }

        .inner
        {
            display: inline-block;
        }

        table {
            font-family: 'Open Sans', sans-serif;
        border: none;

        }
        th {
            padding: 8px 10px;
            background: white;


        <%--color: #F9C941; --%>
            color: #4da6ff;
        <%--    border-right: 1px solid; --%>
            font-size: 0.9em;
        }
        th:first-child {
            text-align: left;
        }
        th:last-child {
            border-right: none;
        }
        td {
            vertical-align: middle;
            padding: 10px;
            font-size: 14px;
            text-align: center;
            border: none;
            border-top:1px solid darkgray;
        <%--
        border-top: 1px solid black;
        border-bottom: 1px solid black;
        border-right: 1px solid black; --%>
    }
    td:first-child {

        border: none;

    }
    td:nth-child(2){
        text-align: left;
    }
</style>
</head>
<body style="background-color: white;">
<div id="background">
<br>


</br>
<!--тут ли section hz-->

<section>

   <%-- ${param.action} --%>
<div id="outer3" style="width:100%;" align="center">

    <div id="templatemo_body_wrapper">
        <div id="templatemo_wrapper">



            <table align="center">
                <tr>

                    <img src="images/lanit-crop.png" width="440" height="85">
                    <!--
                    <img src="images/picture0.jpg" width="200" height="100" align="left">
                <img src="images/picture.jpg" width="200" height="100" align="center">
                    <img src="images/picture.jpg" width="200" height="100" align="right">
                    -->
                </tr>

           </table>

            <div></div>


                <div id="templatemo_menubar">

                <div id="top_nav" class="ddsmoothmenu">
                    <ul>
                        <!--ПРОБЕЛ ДВИГА-->
                        <li><a href="">О нас</a></li>
                        <li><a href="products?action=paging">Каталог</a>

                            <!--Меню барка1-->
                            <!--
                            <ul>
                                <li><a href="https://www.yandex.ru/">Товары первого типа</a></li>
                                <li><a href="#submenu2">Товары для дома</a></li>
                                <li><a href="#submenu3">Товары третьего типа</a></li>
                                <li><a href="#submenu4">Товары четвёртого типа</a></li>
                                <li><a href="#submenu5">Товары пятого типа</a></li>
                            </ul>
                            -->

                        </li>
                        <li><a href="">Контакты</a>
                            <!--Меню барка2-->
                            <!--
                            <ul>
                                <li><a href="#submenu1">НОВИНКИ</a></li>
                                <li><a href="#submenu2">СКИДКИ</a></li>
                                <li><a href="#submenu3">О качестве</a></li>
                            </ul>
                            -->
                        </li>
                    </ul>
                    <br style="clear: left" />
                </div> <!-- end of ddsmoothmenu -->
                <div id="templatemo_search">

                        <form method="post" action="products?action=search">

                           <!--Поисковик корневого сайта Скр->
                            <!-- инпут тайп хайден убирает ввод
                            <input type="text" value=" " name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                        <input type="submit" name="Search" value=" " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />-->
                            <input type='hidden' name="pageN" value="${i.index}">
                            <input type='text' style="height:35px; width:175px" name="searchString" class="txt_field"/>
                            <button type="submit" class ="sub_btn"></button>

                        </form>

                </div>
            </div>

          <!--Момент работы с сайтом упорядочиватели-->
            <div id="templatemo_main">
                <div id="sidebar" class="float_l">
                    <div class="sidebar_box"><span class="bottom"></span>
                        <h3>Категории Товаров</h3>
                        <div class="content">
                            <ul class="sidebar_list">
                                <li class="first"><a href="products?action=sortTypeSetevoe">Сетевое оборудование</a></li>
                                <li><a href="products?action=sortTypeLotki">Защитные лотки</a></li>
                                <li><a href="products?action=sortTypeSvetovoe">Световое оборудование</a></li>
                                <li><a href="products?action=sortTypeSantex">Сантехническое оборудование</a></li>
                                <li><a href="products?action=sortTypeComm">Коммуникационное</a></li>
                                <li><a href="products?action=sortTypeKompOborud">Компьютерное оборудование</a></li>
                                <li><a href="products?action=sortTypeTeleComm">Телекомуникационные</a></li>
                                <li class="last"><a href="products?action=sortTypeElektroshit">Электрощитовое оборудование</a></li>

                            </ul>
                        </div>
                    </div>

                    <!-- список по продажам будущее (каждой картинке спец изображение обрез)
                    Сделать рандомные значения
                    -->
                    <div class="sidebar_box"><span class="bottom"></span>
                        <h3>Продажи</h3>
                        <div class="content">
                            <div class="bs_box">
                                <a href="products?action=sortTypeLotki"><img src="PhotoCablesMendeleev/200osnova.jpg" width="50" height="70" alt="image" /></a>
                                <h4><a href="products?action=sortTypeLotki">Защитные лотки</a></h4>
                                <p class="price">162руб/шт</p>
                                <div class="cleaner"></div>
                            </div>
                            <div class="bs_box">
                                <a href="products?action=sortTypeSetevoe"><img src="PhotoCablesMendeleev/4osnova.jpg" width="50" height="70" alt="image" /></a>
                                <h4><a href="products?action=sortTypeSetevoe">Сетевое оборудовани</a></h4>
                                <p class="price">80руб/м</p>
                                <div class="cleaner"></div>
                            </div>
                            <div class="bs_box">
                                <a href="products?action=sortTypeSantex"><img src="PhotoCablesMendeleev/47590osnova.jpg" width="50" height="70" alt="image" /></a>
                                <h4><a href="products?action=sortTypeSantex">Сантехническое оборудование</a></h4>
                                <p class="price">15руб/шт</p>
                                <div class="cleaner"></div>
                            </div>
                            <div class="bs_box">
                                <a href="products?action=sortTypeSvetovoe"><img src="PhotoCablesMendeleev/16870osnova.jpg" width="50" height="70" alt="image" /></a>
                                <h4><a href="products?action=sortTypeSvetovoe">Световое оборудование</a></h4>
                                <p class="price">500руб/м</p>
                                <div class="cleaner"></div>
                            </div>
                        </div>
                    </div>
                </div>




                <!--Копирка парама, не трогать наш уникальный экшен нужный в коде. Обрезание каждый раз перед нажатием кнопки-->
                <c:set var = "itog" value = "${param.action}" />

                <c:set var = "proverkaCenaLi" value = "${fn:substring(itog,  itog.length()-4, itog.length())}" />
                <c:set var = "proverkaProizvodLi" value = "${fn:substring(itog,  itog.length()-8,itog.length())}" />

                <!--Решение Отезание нужного элемента-->
                <c:if test="${proverkaCenaLi eq 'Cena'}">
                    <c:set var = "itog" value = "${fn:substring(itog,  0, itog.length()-4)}" />

                </c:if>
                <c:if test="${proverkaProizvodLi eq 'Proizvod'}">
                    <c:set var = "itog" value = "${fn:substring(itog,  0, itog.length()-8)}"/>

                </c:if>

                <c:if test="${param.action !='search'}">
                <!--Есть ли норм связь между if и otherwise JSTL НЕТ СВЯЗИ. Это от кэйсового вроде другого тега-->
                <c:if test="${param.action eq 'paging'}">
                    <div id="outer2" style="width:100%; text-align:right" align="right">
                   Сортировать по: <div class="inner"><a style="color:black" href="products?action=pagingProizvod" class = "button">производителю</a></div>
                    <div class="inner"><a style="color:black" href="products?action=pagingCena" class = "button">цене</a></div>
                </div>
                </c:if>

                <c:if test="${param.action != 'paging'}">
                    <div id="outer2" style="width:100%; text-align:right" align="right">
                        Сортировать по: <div class="inner"><a style="color:black" href="products?action=${itog}Proizvod" class = "button">производителю</a></div>
                        <div class="inner"><a style="color:black" href="products?action=${itog}Cena" class = "button">цене</a></div>
                    </div>
                </c:if>
                </c:if>

<!--
                <div class="ddsmoothmenu">
                    <ul>
                        <li><a href="meals?action=pagingProizvod">
                            <!--class="selected" подсветка
                           По производителю</a>

                        </li>
                    </ul>

                    <ul>
                        <li><a href="meals?action=pagingCena">
                            <!--class="selected" подсветка
                            По цене</a>

                        </li>
                    </ul>
                </div>
            -->


                <div id="content" class="float_r">
                  <!--Слайдер скрыт-->
                   <!-- ПОЛЕ ФОТОК MAYBE
                    <div id="slider-wrapper">
                        <div id="slider" class="nivoSlider">
                            <img src="images/slider/02.jpg" alt="" />
                            <a href="#"><img src="images/slider/01.jpg" alt="" title="This is an example of a caption" /></a>
                            <img src="PhotoCablesMendeleev/24.1основа.jpg" width="120" height="120" alt="" />
                            <img src="images/slider/04.jpg" alt="" title="#htmlcaption" />
                        </div>
                        <div id="htmlcaption" class="nivo-html-caption">
                            <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
                        </div>
                    </div>
                    <script type="text/javascript" src="js/jquery-1.4.3.min.js"></script>
                    <script type="text/javascript" src="js/jquery.nivo.slider.pack.js"></script>
                    <script type="text/javascript">
                        $(window).load(function() {
                            $('#slider').nivoSlider();
                        });
                    </script>
                    -->

                    <table border="1" cellpadding="8" cellspacing="0" width>
                        <%
                            int x = 1;
                        %>
                        <c:forEach items="${productsPaging}" var="Product">
                            <jsp:useBean id="Product" scope="page" type="ru.javawebinar.topjava.model.Product"/>
                            <tr>

                                <c:if test ="${empty Product.picture}">
                                    <td><img src="Sklad-Photos/NO-IMAGE.png" width = "120" height="120"><a href="Sklad-Photos/NO-IMAGE.png"></a></td>
                                </c:if>

                                <c:if test ="${not empty Product.picture}">
                                    <td><a href="PhotoCablesMendeleev/${Product.picture}.jpg"><img src="PhotoCablesMendeleev/${Product.picture}.jpg" width = "120" height="160"></a></td>
                                </c:if>


                                    <%--<td><%=x%></td> --%>
                                <% x++; %>

                                    <%-- <td>${meal.articul}</td>

                                <td>${meal.type1}</td>
                                <td>${meal.type2}</td>
                                    <%--     <td>${meal.cod}</td> --%>
                                <td>${Product.naimenovanie}</td>
                                <td>${Product.proizvoditel}</td>
                                    <%--     <td>${meal.edizmereniya}</td> --%>
                                    <%--     <td>${meal.kolvo}</td> --%>
                                <c:if test="${Product.cena != 0}">
                                <c:if test="${Product.edizmereniya eq 'Метры'}"><td>${Product.cena} руб/метр</td></c:if>
                                <c:if test="${Product.edizmereniya eq 'метры'}"><td>${Product.cena} руб/метр</td></c:if>
                                <c:if test="${Product.edizmereniya eq 'шт'}"><td>${Product.cena} руб/шт</td></c:if>
                                <c:if test="${Product.edizmereniya eq ''}"><td>${Product.cena} руб.</td></c:if>
                                </c:if>

                            </tr>

                        </c:forEach>

                    </table>


                </div>
                <div class="cleaner"></div>
            </div> <!-- END of templatemo_main -->

            <div id="outer" style="width:135%;" align="center">
                <div class="inner">


                </div>

                <div class="inner">


                </div>
                <div class="inner" align="left">


               <c:if test="${maxPages < 12}">
                   <!-- Правильный цикл как -->
                    <table>
                        <tr>
                            <!--Нужен пэйджовый динамический идентификатор в Репозитории +1, от каждого клика в стороны След. Предыд и Игра от него-->
                          <!--Три цикла, обычный, на начало и на конец -->
                            <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
                                <form method="post" action="products?action=${param.action}">
                                    <!-- инпут тайп хайден убирает ввод -->
                                    <!--Инпут Тайп связан с нажатием каждой отдельной кнопкой шлёт инфу -->
                                    <input type='hidden' name="pageN" value="${i.index}">
                                    <c:if test="${i.index==pageCurr}">
                                        <button type="submit" class ="button2" style="color:blue">${i.index}</button>
                                    </c:if>
                                    <c:if test="${i.index!=pageCurr}">
                                        <button type="submit" class ="button2">${i.index}</button>
                                    </c:if>
                                </form>
                            </c:forEach>
                        </tr>
                    </table>
               </c:if>

                    <c:if test="${maxPages > 12}">
                        <!-- Правильный цикл как -->
                        <table>
                            <tr>
                                <!--Нужен пэйджовый динамический идентификатор в Репозитории +1, от каждого клика в стороны След. Предыд и Игра от него-->
                                <!--Три цикла, обычный, на начало и на конец -->
                                <c:forEach begin="1" end="${12}" step="1" varStatus="i">
                                    <form method="post" action="products?action=${param.action}">
                                        <!-- инпут тайп хайден убирает ввод -->
                                        <input type='hidden' name="pageN" value="${i.index}">
                                        <c:if test="${i.index==pageCurr}">
                                            <button type="submit" class ="button2" style="color:blue">${i.index}</button>
                                        </c:if>
                                        <c:if test="${i.index!=pageCurr}">
                                            <button type="submit" class ="button2">${i.index}</button>
                                        </c:if>
                                    </form>
                                </c:forEach>
                            </tr>
                        </table>
                    </c:if>


                </div>

            </div>

            <div id="templatemo_footer">
                <p align="center" style="color:blue"><a style="color:blue" href="">О нас</a> | <a style="color:blue" href="">Каталог</a> | <a style="color:blue"href="">Контакты</a></p>

                <!-- Credit: www.templatemo.com --></div> <!-- END of templatemo_footer -->
        </div> <!-- END of templatemo_wrapper -->
        <p align="center" style="color:blue">Copyright © 2017 <a href="">Ланит</a></p>

    </div>




  <%--
    <div class="inner" >
        <form method="post" action="meals?action=search">
            <!-- инпут тайп хайден убирает ввод
            <input type='hidden' name="pageN" value="${i.index}">
            <input type='text' name="searchString">
            <button type="submit" class ="button2">Найти</button>
        </form>
        </div>

    </div>
    --%>






<br>





</section>
</div>
</body>
</html>

<%--


<thead>
<tr style = "color:blue">

    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>


    <th>Pic</th>
    <th>№</th>
    <th>Артикул</th>
    <th>Тип</th>
    <th><a href="meals?action=pagingType">Подтип</a></th>
    <th>Код</th>
    <th><a href="meals?action=pagingAlpha">Наименование</a></th>
    <th><a href="meals?action=pagingProizvod">Производитель</a></th>
    <th><a href="meals?action=pagingEdIzm">Ед.изм</a></th>
    <th><a href="meals?action=pagingKolvo">Кол-во</a></th>
    <th><a href="meals?action=pagingCena">Цена</a></th>
    <th><a href="meals?action=pagingFullprice">Общ.Цена</a></th>
    <th>Примечание</th>
    <th colspan="2">Action</th>
</tr>
</thead>
--%>

<%--
                          <td>${meal.fullprice}</td>
                          <td>${meal.primechanie}</td>
                          <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                          <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>


                          --%>
