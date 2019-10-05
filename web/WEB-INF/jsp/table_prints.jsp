<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-3">
    <div class="alert alert-info" role="alert">
        <h4>Схемы планетарного производства</h4>
    </div>
    <table class="table table-hover">
        <thead>
        <tr class="table-warning">
            <th scope="col" colspan="2">Технологичные планетарные материалы (Т2)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="material" items="${materials}">
        <tr>
            <td><a href="./planet?item=${material.id}">
                 <img src="${material.image}" alt="..." class="img-thumbnail" style="background-color: #000000">
                </a>
            </td>
            <td><c:out value="${material.name}" /></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
