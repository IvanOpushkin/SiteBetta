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

            <%--color: #F9C941; --%>
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
<br>

    <div id="outer" style="width:100%;" align="center">
        <div class="inner"><a href="products?action=create" class = "button1"  >Add Product</a></div>
        <div class="inner"> <a href="index.html" class = "button1"> Меню  </a> </div>
        <div class="inner"> <a href="products?action=paging" class = "button1"> Paging </a> </div>
        <div class="inner"> <a href="products?action=paging" class = "button1"> PageOfTheSite </a> </div>
    </div>

</br>

<!--тут ли section hz-->

<section>
    <table border="1" cellpadding="8" cellspacing="0" width>
        <thead>
        <tr style = "color:blue">
            <%--
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            --%>
            <th>Pic</th>
            <th>№</th>
                <th>Артикул</th>
            <th>Тип</th>
            <th>Подтип</th>
            <th>Код</th>
            <th>Наименование</th>
            <th>Производитель</th>
            <th>Ед.изм</th>
            <th>Кол-во</th>
            <th>Цена</th>
                <th>Общ.Цена</th>
            <th>Примечание</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>

        <%
            int x = 1;
        %>
        <c:forEach items="${products}" var="product">
            <jsp:useBean id="product" scope="page" type="ru.javawebinar.topjava.to.ProductWithExceed"/>

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
    </table>
</section>
</body>
</html>


<%--


               <c:if test="${product.cod == 5}">
                   <c:if test="${product.kolvo == 283}">
                       <td><a href="Sklad-Photos/83zsLqkYPS.jpg">Photo</a></td>
                   </c:if>
                   <c:if test="${product.kolvo == 500}">
                       <td><a href="Sklad-Photos/UEZNkLy105.jpg">Photo</a></td>
                   </c:if>


               </c:if> --%>

<%-- строка цвет </tr> --%>

<%-- СТРАННАЯ СТРОКА ${fn:formatDateTime(product.dateTime)} --%>

<!--
.button, .button:before {
display: inline-block;
font-size: 20px;
color: #fff;
text-decoration: none;
text-align:center;
padding: 8px 15px;
border-radius: 100px;
border: solid rgb(4,88,192);
border-width: 3px 10px;
outline: none;
opacity: 1;
transition: .6s, opacity 0s 9999999s, visibility 0s 9999999s;
}
.button {
position: relative;
padding: calc(8px + 3px - 1px) calc(15px + 10px - 1px);
border: 1px solid rgba(62,153,239,.5);
background: linear-gradient(to left, rgb(62,153,239) 1%, #fff 3%, rgb(44,135,232) 8%, rgba(255,255,255,.3) 50%, rgb(44,135,232) 92%, #fff 97%, rgb(62,153,239) 99%) no-repeat;
}
.button:before {
content:  "ТИП1";
position:  absolute;
bottom: -7px;
left: -1px;
width: calc(100% - (15px + 10px - 1px)*2);
background: #fff linear-gradient(rgb(58,160,253), rgb(4,88,192) 60%, rgb(49,112,201));
box-shadow: 0 10px 18px rgba(0,0,0,.5);
}
.button:hover {
-webkit-transform: scale(1.1, 1.1);
transform: scale(1.1, 1.1);
}
.button:hover:before {
border-color: #0766d8;
background: #fff linear-gradient(#3fadff, #0766d8 60%, #3279dd);
}
.button:focus,
.button:active {
-webkit-transform: scale(2, 2);
transform: scale(2, 2);
opacity: 0;
visibility: hidden;
transition: .4s;
}
-->

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
   </form>
   --%>

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
