<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>
		<div class="management">
            
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Administración de solicitudes de modificación de artículos</h1>
	        
	        <div class="user-list">
	        <h2>Lista de Revisiones:</h2>
	          <c:if test="${requestScope.revisiones != null}">
	        	<table>
		           <tr><th>ID REVISION</th><th>USUARIO:</th><th>ID - ARTICULO:</th><th>TITULO:</th></tr>
		           
			        <c:forEach var="revision" items="${requestScope.revisiones}" >
			        
			           
				         <tr>
				            <td>${revision.id}</td>	
				            <td>${revision.username}</td>	
				            <td>${revision.articulo_id}</td>
				            <td>${revision.titulo_articulo}</td>
				            <td>
				                <form action="${pageContext.request.contextPath}/revision" method="get">
				                <input type="hidden" name="operacion" value="revisar" />
				                <input type="hidden" name="revision_id" value="${revision.id}" />
				                <input type="hidden" name="user_id" value="${revision.user_id}" />
				            	<button type="submit">REVISAR</button>
				            	</form>
				            </td>	
				            <td>
				                <form action="${pageContext.request.contextPath}/revisiones" method="post">
				                <input type="hidden" name="operacion" value="rechazar" />
				                <input type="hidden" name="revision_id" value="${revision.id}" />
				                <input type="hidden" name="user_id" value="${revision.user_id}" />
				            	<button class="delete" type="submit">RECHAZAR</button>
				            	</form>
				            </td>		              
				         </tr>  
				      
				              
			        </c:forEach>
			        			      
		          </table>
		          
		      </c:if> 
	        </div>
	       
	    </div>   
    </jsp:body>
</t:public_page>