<%@ page contentType="text/html;encoding=UTF-8" %>
<%@page import="wiki.entities.Wiki" %>
<%@page import="wiki.entities.Articulo" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:public_page>    
    <jsp:body>
        <div class="management">
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Administración de Artículos</h1>
	        
	        <div class="creation">
	             <h2>Crear artículo:</h2>
	        	<p>Solamente aquellas wikis de las que es coordinador</p>
		        <form action="${pageContext.request.contextPath}/articulos" method="post">
		        
		                <div class="field">
			              <label for="wiki_id">WIKI: </label>
				            <select id="wiki_id" name="wiki_id">
				            	<c:if test="${requestScope.wikis != null}">
				            	   <c:forEach var="wiki" items="${requestScope.wikis}" >
				            	        <option value="${wiki.id}">ID:${wiki.id} - ${wiki.topic}</option>
				            	   </c:forEach>
				            	</c:if>
				            </select>  
				        </div>
				        <div class="field">
				            <label for="titulo">titulo: </label>
				            <input class="long600px" type="text" name="titulo" id="titulo">	
				        </div>
				        <div class="field">
		        	         <textarea name="contenido" id="contenido" rows="3" cols="100">Introducir HTML aquí</textarea>
		        	     </div>
		        	    <div class="field">
		        		     <input type="hidden" name="operacion" value="crear" />
		        		     <button type="submit">CREAR ARTICULO</button>
		        		</div>
		        		
		        </form>
	        
	        </div>
           
	        <div class="articulos-list">
	        	<h2>Lista de artículos:</h2>
	        	<p>Solamente aquellas wikis de las que es coordinador</p>
	        	<c:if test="${requestScope.wikis != null}">
	        	<c:forEach var="wiki" items="${requestScope.wikis}" >
	        	<h3>WIKI: ID ${wiki.id} - Topic ${wiki.topic}</h3>
		        	<table>		
		        	    <tr><th>ID</th><th>TITULO</th><th></th><th></th></tr>	        
		        		     <c:forEach var="articulo" items="${wiki.articulos}" > 
		        			 <tr>
		        				<td>${articulo.id}</td>		        				
			        			<td>${articulo.titulo}</td>				        			
			        			<td>
			        			    <form action="${pageContext.request.contextPath}/editar_articulo" method="get">
				        				<input type="hidden" name="articulo_id" value="${articulo.id}" />
				        				<input type="hidden" name="operacion" value="editar" />
				        				<button  type="submit">EDITAR</button>
				        			 </form>
			        			</td>		        				
		        				<td>
		        				     <form action="${pageContext.request.contextPath}/articulos" method="post">
				        				<input type="hidden" name="articulo_id" value="${articulo.id}" />
				        				<input type="hidden" name="operacion" value="eliminar" />
				        				<button class="delete" type="submit">ELIMINAR</button>
				        			 </form>
		        				</td>
		        			</tr>
		        		 </c:forEach>
	        		</table>
	        		</c:forEach>
	        	</c:if>
	        </div>
        </div>
    </jsp:body>
</t:public_page>