<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>
		<div class="management">
            
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Administración de solicitudes de Supervisión</h1>
	        <p>Solamente de las wikis en las que es coordinador (Puede haber peticiones de supervisión de artículos)</p>
	        <div class="user-list">
	        <h2>Lista de solicitudes:</h2>
	          <c:if test="${requestScope.solicitudes != null}">
	        	<table>
		           <tr><th>USUARIO</th><th>SOLICITA SER:</th><th>ELEMENTO:</th><th>TITULO/TOPIC:</th></tr>
		           
			        <c:forEach var="solicitud" items="${requestScope.solicitudes}" >
			        
			           
				         <tr>
				            <td>${solicitud.username}</td>	
				            <td>${solicitud.tipoSolicitud}</td>	
				            <c:if test="${solicitud.articulo_id == 0}"> 
				            	<td>WIKI</td>
				            	<td>${solicitud.topic_wiki}</td>
				            </c:if>	
				            <c:if test="${solicitud.wiki_id == 0}"> 
				            	<td>ARTICULO</td>
				            	<td>${solicitud.titulo_articulo}</td>
				            </c:if>
				            <td>
				                <form action="${pageContext.request.contextPath}/solicitudes_coordinador" method="post">
				                <input type="hidden" name="operacion" value="aprobar" />
				                <input type="hidden" name="rol_id" value="${solicitud.rol_id}" />
				            	<button type="submit">APROBAR</button>
				            	</form>
				            </td>	
				            <td>
				                <form action="${pageContext.request.contextPath}/solicitudes_coordinador" method="post">
				                <input type="hidden" name="operacion" value="rechazar" />
				                <input type="hidden" name="rol_id" value="${solicitud.rol_id}" />
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