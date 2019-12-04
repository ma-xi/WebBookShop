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
        <script src="scripts.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Woggmaier Büchergroßhandel</h1>
            <h5>Bücher mit dem besonderen Pfiff</h5> 
        </div>
        <div class="container">
            <div class="jumbotron">
                <form id="optionForm" class="form-inline" action="WebShopController" method="POST">
                    <label class="mr-sm-2">Sortieren nach:</label>
                    <select id="sortSelection" class="form-control mb-2 mr-sm-2" 
                            name="sortSelection" onchange="sortBooks()">
                        <c:choose>
                            <c:when test="${setSort.equals('Titel')}">
                                <option value="Titel" selected>Titel</option>
                                <option value="Autor">Autor</option>
                                <option value="Preis">Preis</option>
                            </c:when>
                            <c:otherwise>
                                <option value="Titel">Titel</option>
                                <c:choose>
                                    <c:when test="${setSort.equals('Autor')}">
                                        <option value="Autor" selected>Autor</option>
                                        <option value="Preis">Preis</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="Autor">Autor</option>
                                        <option value="Preis" selected>Preis</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <label class="mr-sm-2">Filtern:</label>
                    <input type="text" class="form-control mr-sm-2" name="filter" 
                           value="${setFilter}"/>
                    <input type="submit"  class="btn btn-secondary mr-sm-2" value="Filtern"
                           name="filterBtn" />
                    <div class="form-check-inline mr-sm-2">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="filterSel" 
                                accept="" value="title" checked>filtern nach Titel
                        </label>
                    </div>
                    <div class="form-check-inline mr-sm-2">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="filterSel" 
                                accept=""value="author">filtern nach Autor
                        </label>
                    </div>
                    <div class="form-check-inline mr-sm-2">
                        <button type="button" class="btn btn-warning" 
                                data-toggle="modal" data-target="#myModal">
                            Warenkorb anzeigen
                        </button>
                    </div>
                    <input type="hidden" name="sortOrFilter" id="sortOrFilter" value="filter" />
                </form>
            </div>
            <br/>
            </div>
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
                            <form id="addForm" class="form-inline" action="WebShopController" method="POST">
                                <td>${book}</td>
                                   <input type="hidden" name="bTitle" id="bTitle" value="${book.title}" />
                                <td>
                                    <c:forEach var="bookAuthor" items="${book.authorList}">
                                        ${bookAuthor}<br/>
                                    </c:forEach>
                                </td>
                                <td>${book.publisher}</td>
                                <td><b>${book.getFormattedPrice()}</b></td>
                                <td><button type="submit" class="btn btn-primary">Zum Warenkorb hinzufügen</button></td>
                            </form>
                       </tr>  
                    </c:forEach>
                </tbody>
          </table>
        <div class="modal" id="myModal">
            <div class="modal-dialog modal-xl">
                <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Warenkorb</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                   <table class="table table-hover">
                        <thead>
                          <tr>
                            <th>Buch</th>
                            <th>Menge</th>
                            <th>Einzelpreis</th>
                            <th>Gesamtpreis</th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="article" items="${shoppingCart}">
                                <tr>
                                    <td>${article.book.title}</td>
                                    <td>${article.amount}</td>
                                    <td>${article.getFormattedPrice()}</td>
                                    <td>${article.getFormattedTotalPrice()}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                   </table>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <form id="addForm" class="form-inline" action="WebShopController" method="POST">                    
                        <button type="submit" class="btn btn-warning">Warenkorb leeren</button>
                        <input type="hidden" name="empty" value="empty" />
                    </form>   
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Schließen</button>                    
                </div>

                </div>
            </div>
        </div>
    </body>
</html>
