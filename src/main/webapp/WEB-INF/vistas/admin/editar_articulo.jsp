<%@ page contentType="text/html;encoding=UTF-8" %>
<%@page import="wiki.entities.Articulo" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:public_page>    
    <jsp:body>
        <div class="management">
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Editar Artículo</h1>
	        <a href="${pageContext.request.contextPath}/articulos"> << Volver a lista de artículos</a>
	        <div class="editar">
	             <h2>Editar artículo:</h2>
	        	<p>Solamente aquellas wikis de las que es coordinador</p>
		        <form action="${pageContext.request.contextPath}/editar_articulo" method="post">
		        
		           
				        <div class="field">
				            <label for="titulo">titulo: </label>
				            <input class="long600px" type="text" name="titulo" id="titulo" value="${articulo.titulo}">	
				        </div>
				        <div class="field">
		        	         <textarea name="contenido" id="contenido" rows="25" cols="100">${articulo.contenido}</textarea>
		        	     </div>
		        	    <div class="field">
		        	         <input type="hidden" name="articulo_id" value="${articulo.id}" />
		        		     <input type="hidden" name="operacion" value="editar" />
		        		     <button type="submit">EDITAR ARTICULO</button>
		        		</div>
		        		
		        		<a href="${pageContext.request.contextPath}/articulos"> << Volver a lista de artículos</a>
		        		
		        </form>
	        
	        </div>
           
	        
        </div>
    </jsp:body>
</t:public_page>