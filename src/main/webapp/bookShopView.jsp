<%-- 
    Document   : bookShopView
    Created on : 19.11.2019, 14:13:40
    Author     : Maxi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>St. Harrasser Buchgeschäft</h1>
            <h5>Bücher mit dem besonderen Pfiff</h5> 
        </div>
        <div class="container">
            <form id="optionForm" class="form-inline" action="WebShopController" method="POST">
                <label class="mr-sm-2">Sortieren nach:</label>
                <select id="sortSelection" class="form-control mb-2 mr-sm-2" name="sortSelection">
                    <option value="Titel">Titel</option>
                    <option value="Autor">Autor</option>
                    <option value="Preis">Preis</option>
                </select>
            </form> 
            <br/>
            <table class="table table-dark table-hover">
                <thead>
                  <tr>
                    <th>Buch</th>
                    <th>Autor</th>
                    <th>Verlag</th>
                    <th>Preis</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books2display}">
                        <tr>
                            <td>${book}</td>
                            <td>
                                <c:forEach var="bookAuthor" items="${book.authorList}">
                                    ${bookAuthor}<br/>
                                </c:forEach>
                            </td>
                            <td>${book.publisher}</td>
                            <td><b>${book.getFormattedPrice()}</b></td>
                        </tr>  
                    </c:forEach>
                  </tr>
                </tbody>
          </table>
        </div>
    </body>
</html>
