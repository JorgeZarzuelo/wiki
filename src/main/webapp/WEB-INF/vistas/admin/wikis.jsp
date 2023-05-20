<%@ page contentType="text/html;encoding=UTF-8" %>
<%@page import="wiki.entities.Wiki" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:public_page>    
    <jsp:body>
        <div class="management">
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Administración de wikis</h1>
	        
	        <div class="creation">
	             <h2>Crear wiki:</h2>
	        	
		        <form action="${pageContext.request.contextPath}/wikis" method="post">
		        
		           <table>
		              <tr>
			            <td>
				            <label for="topic">Topic: </label>
				            <input type="text" name="topic" id="topic">	
				        </td>      	
		        	    <td>
		        	         <textarea name="descripcion" id="descripcion" rows="3" cols="100">Describa el tema del que trata esta wiki...</textarea>
		        	    </td>
		        	         
		        		<td>
		        		     <input type="hidden" name="operacion" value="crear" />
		        		     <button type="submit">CREAR WIKI</button>
		        		</td>
		        	</tr>
		        </table>
		        </form>
	        
	        </div>
           
	        <div class="wikis-list">
	        	<h2>Lista de wikis:</h2>
	        	<c:if test="${requestScope.wikis != null}">
		        	<table>		
		        	    <tr><th>ID</th><th>TOPIC</th><th>CONTENIDO</th><th></th><th></th></tr>	        
		        		<c:forEach var="wiki" items="${requestScope.wikis}" >
		        			 <tr>
		        				<td>${wiki.id}</td>
		        				<form action="${pageContext.request.contextPath}/wikis" method="post">
		        				<td> 		        				   
			        				     <input type="text" name="topic" value="${wiki.topic }" />
			        			</td>
			        			<td>	     
			        				     <textarea cols="100" name="descripcion" rows="3">${wiki.descripcion }</textarea>
		        				          <input type="hidden" name="operacion" value="editar" />
		        				          <input type="hidden" name="wiki_id" value="${wiki.id}" />
		        				</td>
		        				<td><button type="submit">EDITAR</button></td>
		        				 </form>
		        				<td>
		        				     <form action="${pageContext.request.contextPath}/wikis" method="post">
				        				<input type="hidden" name="wiki_id" value="${wiki.id}" />
				        				<input type="hidden" name="operacion" value="eliminar" />
				        				<button class="delete" type="submit">ELIMINAR</button>
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