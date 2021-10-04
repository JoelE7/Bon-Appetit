<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="#"><img src="images/logoB.png" class="img-logo" alt="Bon_appetit"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
    </div>
</nav>
<div class="fondo">
    <div class="form">
        <h1 class="titulo text-center p-2">Cambiar clave</h1>
        <form:form action="cambiar-clave" method="POST" modelAttribute="datos">
            <div class="form-group mx-2">
                <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario:"/><br/>
                <form:input path="claveActual" type="text" id="password" class="form-control"
                            placeholder="Clave actual:"/><br/>
                <form:input path="claveNueva" type="text" id="password" class="form-control"
                            placeholder="Clave nueva:"/><br/>
                <form:input path="repiteClaveNueva" type="text" id="repite-password" class="form-control"
                            placeholder="Confirme clave actual:"/>
            </div>
            <div class="row  mx-auto">
                <div class="col-4">
                    <button class="btn btn-block btn-info " Type="Submit"/>
                    Modificar</button>
                </div>
                <div class="col-4">
                    <a href="login" class=" btn btn-outline-danger btn-block">Cancelar</a>
                </div>
            </div>
        </form:form>
        <c:if test="${not empty error}">
            <h4><span class="text-light">${error}</span></h4>
            <br>
        </c:if>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>