<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-3">
    <div class="alert alert-info" role="alert">
        <h4>Схемы планетарного производства</h4>
    </div>
    <table class="table table-hover">
        <thead>
        <tr class="table-warning">
            <th>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="checkBoxPlanetAll">
                    <label class="form-check-label" for="checkBoxPlanetAll">
                        Все
                    </label>
                </div>
            </th>
            <th scope="col" colspan="3">Технологичные планетарные материалы (Т2)</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="material" items="${materials}">
            <tr>
                <td>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="planetII_${material.id}">
                    </div>
                </td>
                <td><a href="./planet?item=${material.id}">
                    <img src="${material.image}" alt="..." class="img-thumbnail" style="background-color: #000000">
                </a>
                </td>
                <td><c:out value="${material.name}"/></td>
                <td align="right">
                    <c:if test="${material.flagIsBluePrint}">
                        <button type="button" class="btn btn-outline-secondary" id="btn_edit_planetII_${material.id}">Изменить</button>
                    </c:if>
                    <c:if test="${!material.flagIsBluePrint}">
                        <button type="button" class="btn btn-outline-warning" id="btn_add_planetII_${material.id}">Создать</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
