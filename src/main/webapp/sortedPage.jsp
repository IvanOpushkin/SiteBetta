<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Склад</title>
    <link rel="stylesheet" href="css/style.css">

    <style>

        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
        }

        a:visited{
            color:blue;
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

        #outer
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

        }
        th {
            padding: 8px 10px;
            background: white;


            color: #4da6ff;
            border-right: 1px solid;
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
            border-top: 1px solid black;
            border-bottom: 1px solid black;
            border-right: 1px solid black;
        }
        td:first-child {
            border-left: 1px solid black;
            border-right: none;
        }
        td:nth-child(2){
            text-align: left;
        }
    </style>
</head>
<body>
<section>

        <br>

        <div id="outer" style="width:100%;" align="center">
            <div class="inner"><a href="products?action=create" class = "button1">Add Product</a></div>
            <div class="inner"> <a href="index.html" class = "button1"> Меню  </a> </div>
        </div>

        </br>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr style = "color:blue">


                <!--Возможно сначала сделать пэйджинацию банальное мэпой с номером-->

                <!--Сортировка всего выпадающие оконца 5 окон-->

                <th>Pic</th><!--Тут нет смысла-->
                    <th>№</th> <!--Просто поле, пока не надо-->
                    <th>Артикул</th> <!--пока нету не надо-->
                    <th>Тип</th> <!--пока один тип не надо-->
                <th><a href="products?action=${"sortTypesTypes"}">Подтип</a></th> <!--Сортировка всего по одинаковости типов как с производ-->
                    <th>Код</th> <!--обратная сортировка потом какнить-->
                    <th>Наименование</th> <!--лучше просто поиск для этой строки-->
                <th><a href="products?action=${param.action}Proizvod">Производитель</a></th> <!-- и обратка -->
                <th><a href="products?action=${"sortType"}Edizm">Ед.изм</a></th> <!-- и обратка просто зеркало метод-->
                <th><a href="products?action=${"sortKolvo"}">Кол-во</a></th> <!-- и обратка просто зеркало метод-->

                <%--Обычные сортировки сначала--%>
                <%--Как всё сделать одним методом, передачей туда второго аргумента  <th><a href="products?action=${"sortTyp"}">Кол-во</a></th> например к ID встроеннго в кнопку--%>
               <%-- <c:if test="${param.action == "sortType2"}"></c:if>
               Можно будет вставить native action в тип сортировки они имеют одинаковое начало строки
               Возможно сделать тернарную цепочку, СДЕЛАТЬ IF СПИСОК КАЖДОГО БАЗОВОГО ПУНКТА ПО КОДУ ВНИЗ.
               --%>


                <th><a href =${param.action != "sortType2PriceAll" ? "products?action=sortType2PriceAll" : "products?action=sortType2PriceAllBack"}>Цена</a></th>

                <th>Общ.Цена</th>
                    <th>Примечание</th>
                    <th colspan="2">Action</th>
            </tr>
            </thead>




            <%
                int x = 1;
            %>
            <c:forEach items="${products}" var="product">
                <jsp:useBean id="product" scope="page" type="ru.javawebinar.topjava.model.Product"/>


            <tr>

                <c:if test ="${empty product.picture}">
                    <td><a href="Sklad-Photos/NO-IMAGE.png">Photo</a></td>
                </c:if>

                <c:if test ="${not empty product.picture}">
                    <td><a href="PhotoCablesMendeleev/${product.picture}.jpg">Photo</a></td>
                </c:if>


                    <td><%=x%></td>
                    <% x++; %>
                    <td>${product.articul}</td>

                    <td>${product.type1}</td>
                    <td>${product.type2}</td>
                    <td>${product.cod}</td>
                    <td>${product.naimenovanie}</td>
                    <td>${product.proizvoditel}</td>
                    <td>${product.edizmereniya}</td>
                    <td>${product.kolvo}</td>
                    <td>${product.cena}</td>

                    <td>${product.fullprice}</td>

                    <td>${product.primechanie}</td>

                    <td><a href="products?action=update&id=${product.id}">Update</a></td>
                    <td><a href="products?action=delete&id=${product.id}">Delete</a></td>

                </tr>
            </c:forEach>

            <%-- СТРОКА СУММЫ ОБЩЕЙ СТОИМОСТИ ТЕКСТОМ ВПРАВО. ФУЛ ПРАВО. ПОТИПОВАЯ СТОИМОСТЬ --%>
        </table>
</section>
</body>
</html>

<%-- <tr class="${product.exceed ? 'exceeded' : 'normal'}"> --%>

<!--
<td>
<%--${product.dateTime.toLocalDate()} ${product.dateTime.toLocalTime()}--%>
<%--<%=TimeUtil.toString(product.getDateTime())%>--%>
<%--${fn:replace(product.dateTime, 'T', ' ')}--%>
<-- МЕСТО СТРАННОЙ СТРОКИ  -->
<%--
</td>
<td>${product.description}</td>
<td>${product.calories}</td>

--%>



<%-- СТРАННАЯ СТРОКА ${fn:formatDateTime(product.dateTime)} --%>

<%--
   <h3><a href="index.html">Home</a></h3>
   <h2>Meals</h2>
   <form method="post" action="products?action=filter">
       <dl>
           <dt>From Date:</dt>
           <dd><input type="date" name="startDate" value="${param.startDate}"></dd>
       </dl>
       <dl>
           <dt>To Date:</dt>
           <dd><input type="date" name="endDate" value="${param.endDate}"></dd>
       </dl>
       <dl>
           <dt>From Time:</dt>
           <dd><input type="time" name="startTime" value="${param.startTime}"></dd>
       </dl>
       <dl>
           <dt>To Time:</dt>
           <dd><input type="time" name="endTime" value="${param.endTime}"></dd>
       </dl>
       <button type="submit">Filter</button>
   </form> --%>


<%-- ${param.action == 'create' ? 'Create Product' : 'Edit Product'} --%>

