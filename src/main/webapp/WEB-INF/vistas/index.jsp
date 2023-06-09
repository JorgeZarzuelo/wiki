<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:public_page>    
    <jsp:body>
        <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
         </c:if>
         <div class="portada">
         	<p>Para el testeo de la aplicación se cargan durante el arranque algunas wikis y artículos y tambien algunos usuarios:</p>
         	<ul>
         		<li>GESTOR: admin/admin</li>
         		<li>COORDINADOR: coordinador/1234</li>
         		<li>SUPERVISOR: supervisor/1234</li>
         		<li>Usuario normal registrado: user1/1234</li>
         	</ul>
         </div>
        <c:if test="${requestScope.wikis != null}">
        	<c:forEach var="wiki" items="${requestScope.wikis}">
        	   <div class="wiki">
	        		<h2>${wiki.topic}</h2>
	        		<p>${wiki.descripcion}</p>
	        		<c:if test="${wiki.articulos != null}">
	        		    <div class="articulos">
			        		<c:forEach var="articulo" items="${wiki.articulos}">
			        		   - <a href="${pageContext.request.contextPath}/articulo?id=${articulo.id}">${articulo.titulo}</a>
			        		</c:forEach>
		        		</div>
		        	</c:if>	
        		</div>
        	</c:forEach>
        </c:if>
    </jsp:body>
</t:public_page>