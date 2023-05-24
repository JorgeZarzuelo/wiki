<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>
		<div class="management">
            
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Gestión de roles del usuario actual</h1>
	        
	        
	        <div class="creation">
	             <h2>Solicitar rol sobre wikis:</h2>
	        	
		        <form action="${pageContext.request.contextPath}/mis_roles" method="post">
		        
		                          	
		        	
		        	
			            <label for="rol">ROL: </label>
			            <select id="rol" name="rol">
			            	<option value="COORDINADOR">COORDINADOR</option>
			            	<option value="SUPERVISOR">SUPERVISOR</option>
			            </select>   
			            
			            <label for="wiki_id">WIKI: </label>
			            <select id="wiki_id" name="wiki_id">
			            	<c:if test="${requestScope.wikis != null}">
			            	   <c:forEach var="wiki" items="${requestScope.wikis}" >
			            	        <option value="${wiki.id}">ID:${wiki.id} - ${wiki.topic}</option>
			            	   </c:forEach>
			            	</c:if>
			            </select>        	
		        	
		        	    <input type="hidden" name="operacion" value="addRoleWiki" />
		        		<button type="submit">SOLICITAR ROL</button>
		        	
		        </form>
	        
	        </div>
	        
	        <div class="creation">
	             <h2>Solicitar rol sobre artículos:</h2>
	        	
		        <form action="${pageContext.request.contextPath}/mis_roles" method="post">
		        
		                          	
		        	
		        	
			            <label for="rol">ROL: </label>
			            <select id="rol" name="rol">			            	
			            	<option value="SUPERVISOR">SUPERVISOR</option>
			            </select>   
			            
			            <label for="articulo_id">Artículo: </label>
			            <select id="articulo_id" name="articulo_id">
			            	<c:if test="${requestScope.articulos != null}">
			            	   <c:forEach var="articulo" items="${requestScope.articulos}" >
			            	        <option value="${articulo.id}">ID:${articulo.id} - ${articulo.titulo}</option>
			            	   </c:forEach>
			            	</c:if>
			            </select>        	
		        	
		        	    <input type="hidden" name="operacion" value="addRoleArticulo" />
		        		<button type="submit">SOLICITAR ROL</button>
		        	
		        </form>
	        
	        </div>
	        
	        
	        
	        <div class="user-list">
	        <h2>Lista de roles y solicitudes (las solicitudes son roles con estado pendiente):</h2>
	          <c:if test="${requestScope.roles != null}">
	        	<table>
		           <tr><th>USUARIO</th><th>ROL:</th><th>ESTADO</th><th>ELEMENTO:</th><th>TITULO/TOPIC:</th></tr>
		           
			        <c:forEach var="rol" items="${requestScope.roles}" >
			        
			           
				         <tr>
				            <td>${rol.username}</td>	
				            <td>${rol.tipoSolicitud}</td>	
				            <td>
					             <c:if test="${rol.pendiente == false}"> 
					            	ACTIVO					            	
					            </c:if>
					            <c:if test="${rol.pendiente == true}"> 
					            	PENDIENTE				            	
					            </c:if>
				            </td>
				            <c:if test="${rol.articulo_id == 0}"> 
				            	<td>WIKI</td>
				            	<td>${rol.topic_wiki}</td>
				            </c:if>	
				            <c:if test="${rol.wiki_id == 0}"> 
				            	<td>ARTICULO</td>
				            	<td>${rol.titulo_articulo}</td>
				            </c:if>
				            
				            	              
				         </tr>  
				      
				              
			        </c:forEach>
			        			      
		          </table>
		          
		      </c:if> 
	        </div>
	       
	    </div>   
    </jsp:body>
</t:public_page>