<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-3">
    <div class="alert alert-info" role="alert">
        <h4>Схемы планетарного производства</h4>
    </div>

    <div class="card">
        <form id="edit_planet_form" method="post" action="./planet">
            <h4 class="card-header">
                <p><img src="${blue_print.material.image}" alt="..." class="img-thumbnail"
                        style="background-color: #000000">
                    ${blue_print.material.name}</p>
                <p> Количество производимого материала -
                    <input type="number" id="quantity_main_material" value="${blue_print.quantityResult}">
                </p>
            </h4>
            <div class="card-body">
                <h5 class="card-title">Требуемые материалы</h5>
                <ul class="list-group list-group-flush">
                    <c:set var="counter" value="0"/>
                    <c:forEach var="elem" items="${blue_print.bluePrintMaterials}">
                        <li class="list-group-item">
                            <div class="input-group mb-3">
                                <img src="${elem.material.image}" alt="..." class="img-thumbnail"
                                     style="background-color: #000000">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">${elem.material.name}</span>
                                </div>
                                <select class="custom-select" name="element_${counter=counter+1}">
                                    <option selected value="0">Choose...</option>
                                    <c:forEach var="rez" items="${list_material}">
                                        <option value="${rez.id}">
                                                ${rez.name}
                                        </option>
                                    </c:forEach>
                                </select>
                                <input type="text" class="form-control" name="elem-quantity_${counter}"
                                       VALUE="${elem.quantity}">
                            </div>
                        </li>
                    </c:forEach>
                    <li class="list-group-item" id="form_new">

                    </li>
                </ul>

                <div>
                    <input class="btn btn-primary" type="submit" value="Изменить">
                    <input class="btn btn-primary" type="reset" value="Cancel">
                    <button class="btn btn-outline-primary" type="button" id="add_element">Добавить элемент</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    var count = ${counter};
    add_element.onclick = function () {
        var l = $('#form input').length / 2;
        $('#form_new').prepend('' +
            '<div class="input-group mb-3">' +
            '<select class="custom-select" name="element_' + (count = count + 1) + '">' +
            '<option selected value="0">Choose...</option>' +
            '<c:forEach var="rez" items="${list_material}">' +
            '<option value="${rez.id}">' +
            '${rez.name}' +
            '</option>' +
            '</c:forEach>' +
            '</select>' +
            '<input type="text" class="form-control" name="elem-quantity_' + count + '" VALUE="0">' +
            '</div>'
        )
    };
    $('#add_element').trigger('click');
</script>
