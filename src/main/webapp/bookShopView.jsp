<%-- 
    Document   : bookShopView
    Created on : 19.11.2019, 14:13:40
    Author     : Maxi
--%>

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
            <form id="optionForm" class="form-inline" action="" method="POST">
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
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>John</td>
                    <td>Doe</td>
                    <td>john@example.com</td>
                  </tr>
                  <tr>
                    <td>Mary</td>
                    <td>Moe</td>
                    <td>mary@example.com</td>
                  </tr>
                  <tr>
                    <td>July</td>
                    <td>Dooley</td>
                    <td>july@example.com</td>
                  </tr>
                </tbody>
          </table>
        </div>
    </body>
</html>
