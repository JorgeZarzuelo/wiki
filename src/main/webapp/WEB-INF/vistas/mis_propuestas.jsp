<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>		
		<c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
         </c:if>
       <h1>Mis propuestas de modificación de artículos</h1>
       <c:if test="${requestScope.propuestas != null}">
       <div class="management">
           <table>
              <tr><th>ID propuesta</th><th>STATUS</th><th>ID artículo</th><th>contenido</th></tr>
		   <c:forEach var="propuesta" items="${requestScope.propuestas}" >
			  <tr>
			  	<td>${propuesta.id}</td><td>${propuesta.pendiente ? "PENDIENTE" : "APROBADA"}</td><td>${propuesta.articulo_id}</td><td><textarea cols="80" rows="3" disabled="disabled">${propuesta.propuesta}</textarea></td>
			  </tr> 
		   </c:forEach>
		   </table>
		  </div> 
	   </c:if>
    </jsp:body>
</t:public_page>