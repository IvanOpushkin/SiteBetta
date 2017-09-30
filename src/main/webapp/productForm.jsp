<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>Оборудование артикул №</title>
    <style>
        .button1 {
              background-color: cadetblue; /* Green */
              border: none;
              color: white;
              padding: 10px 32px;
              text-align: center;
              text-decoration: none;
              display: inline-block;
              font-size: 20pt;
          }

        .button2 {
            background-color: cadetblue; /* Green */
            border: none;
            color: white;
            padding: 7px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 20pt;
        }

        .button1:visited {
            color: white;
        }
    </style>

    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <a href="index2.html" class = "button1"> Меню  </a>
 <%-- <h4 style="color:black">${param.action == 'create' ? 'Create Product' : 'Edit Product'}</h4> --%>

   <!-- вкладка теста магазина -->
    <!--
    <div id="templatemo_header">
        <div id="site_title"><h1><a href="#">Магазин Оборудования</a></h1></div>
    </div> <!-- END of templatemo_header

    <div id="templatemo_menubar">
        <div id="top_nav" class="ddsmoothmenu">
            <ul>
                <li style = "height:30px; width:150px"><a href="index.html" class="selected">Home</a></li>
                <li><a href="products.html">Товары</a>
                    <ul>
                        <li><a href="https://www.yandex.ru/">Товары первого типа</a></li>
                        <li><a href="#submenu2">Товары для дома</a></li>
                        <li><a href="#submenu3">Товары третьего типа</a></li>
                        <li><a href="#submenu4">Товары четвёртого типа</a></li>
                        <li><a href="#submenu5">Товары пятого типа</a></li>
                    </ul>
                </li>
                <li><a href="about.html">О товарах</a>
                    <ul>
                        <li><a href="#submenu1">НОВИНКИ</a></li>
                        <li><a href="#submenu2">СКИДКИ</a></li>
                        <li><a href="#submenu3">О качестве</a></li>
                    </ul>
                </li>
                <li><a href="faqs.html">Доп.Инфа</a></li>
                <li><a href="checkout.html">Оплата</a></li>
                <li><a href="contact.html">Напишите нам!</a></li>
            </ul>
            <br style="clear: left" />
        </div> <!-- end of ddsmoothmenu

            <div id="templatemo_search">
                <form action="#" method="get">
                    <input type="text" value=" " name="keyword" id="keyword" title="keyword" onfocus="clearText(this)" onblur="clearText(this)" class="txt_field" />
                    <input type="submit" name="Search" value=" " alt="Search" id="searchbutton" title="Search" class="sub_btn"  />
                </form>
            </div>
        </div>  END of templatemo_menubar -->

    <hr width = 30% align = left>
    <jsp:useBean id="product" type="ru.javawebinar.topjava.model.Product" scope="request"/>
    <form method="post" action="products">
        <input type="hidden" name="id" value="${product.id}">

       <table style = " border-spacing:30px">
        <tr>

        <td>
        <dl>
            <dt>Артикул:</dt>
            <dd><input type="text" value="${product.articul}" name="articul"></dd>
        </dl>


        <dl>
        <dt>Тип:</dt>
            <dd><input type="text" value="${product.type1}" name="type1"></dd>
        </dl>
        <dl>
            <dt>Подтип:</dt>
            <dd><input type="text" value="${product.type2}" name="type2"></dd>
        </dl>
        <dl>
            <dt>Код:</dt>
            <dd><input type="number" value="${product.cod}" name="cod"></dd>
        </dl>

        <dl>
            <dt>Наименование:</dt>
            <dd><input type="text" value="${product.naimenovanie}" name="naimenovanie"></dd>
        </dl>
        <dl>
            <dt>Производитель:</dt>
            <dd><input type="text" value="${product.proizvoditel}" name="proizvoditel"></dd>
        </dl>
        <dl>
            <dt>Ед.измерения:</dt>
            <dd><input type="text" value="${product.edizmereniya}" name="edizmereniya"></dd>
        </dl>

        <dl>
            <dt>Количество:</dt>
            <dd><input type="number" value="${product.kolvo}" name="kolvo"></dd>
        </dl>
        <dl>
            <dt>Цена:</dt>
            <dd><input type="number" value="${product.cena}" name="cena"></dd>
        </dl>
        <dl>
        <dt>Примечание:</dt>
        <dd><input type="text" value="${product.primechanie}" name="primechanie"></dd>
    </dl>

        <dt>Название изображения (в корне):</dt>
        <dd><input type="text" value="${product.picture}" name="picture"></dd>
        </dl>

        </td>

            <c:if test ="${empty product.picture}"><td> <a href="Sklad-Photos/NO-IMAGE.png"><img src="Sklad-Photos/NO-IMAGE.png" width="189" height="189"></a></td></c:if>
            <c:if test="${not empty product.picture}"><td> <a href="PhotoCablesMendeleev/${product.picture}.jpg"><img src="PhotoCablesMendeleev/${product.picture}.jpg" width="189" height="255"></a></td></c:if>


        </tr>

       </table>


        <hr width = 30% align = left>

        <button type="submit" class ="button2">Save</button>
        <button onclick="window.history.back()" type="button" class ="button2">Cancel</button>
    </form>



</section>
</body>
</html>

<%--
    <dl>
        <dt>DateTime:</dt>
        <dd><input type="datetime-local" value="${product.dateTime}" name="dateTime"></dd>
    </dl>
    <dl>
        <dt>Description:</dt>
        <dd><input type="text" value="${product.description}" size=40 name="description"></dd>
    </dl>
    <dl>
        <dt>Calories:</dt>
        <dd><input type="number" value="${product.calories}" name="calories"></dd>
    </dl>

    --%>

