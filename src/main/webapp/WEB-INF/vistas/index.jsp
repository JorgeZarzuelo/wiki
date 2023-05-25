<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:public_page>    
    <jsp:body>
        <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
         </c:if>
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