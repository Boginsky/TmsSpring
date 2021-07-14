<%--
  Created by IntelliJ IDEA.
  User: Kirill Boginsky
  Date: 14.07.2021
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TodoList</title>
</head>
<body>
<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>${name}</td>
    </tr>
    <tr>
        <th scope="row">2</th>
        <td>${priority}</td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>${important}</td>
    </tr>
    <tr>
        <th scope="row">4</th>
        <td>${startdate}</td>
    </tr>
    <tr>
        <th scope="row">5</th>
        <td>${enddate}</td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>${comment}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
