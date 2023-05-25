<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
         </c:if>
        <c:if test="${requestScope.articulo != null}">
        <div class="articulo">            
        	<h1 class="header">${articulo.titulo}</h1>
        	<a href="${pageContext.request.contextPath}/"> << Atrás </a>
        	<p>${articulo.contenido}</p>
        	<a href="${pageContext.request.contextPath}/"> << Atrás </a>
        </div>	
        <div class="propuesta">   
                 
        	<c:if test="${sessionScope.isLoggedIn == true && sessionScope.isGestor == false}"> 
		         <li><a href="${pageContext.request.contextPath}/mis_roles">Propuesta de modificación</a></li>
		    </c:if>
        	
        </div>
         </c:if>
    </jsp:body>
</t:public_page>