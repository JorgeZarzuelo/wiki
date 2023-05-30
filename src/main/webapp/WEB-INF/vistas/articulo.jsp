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
        	
        	<p>${articulo.contenido}</p>
        	<a class="atras" href="${pageContext.request.contextPath}/"> << Atrás </a>
        </div>	
        <div class="propuesta">   
             <h3>Area de edición de artículos</h3>
             
             <c:if test="${sessionScope.isLoggedIn == null}"> 
        	      
        	     <p>Para poder proponer la edición de este artículo debe tener una cuenta y tener la sesion iniciada. <a href="${pageContext.request.contextPath}/login">Login</a> </p>
		         
		    </c:if>
        	      
        	<c:if test="${sessionScope.isLoggedIn == true && sessionScope.isGestor == false}"> 
        	    <p>Descargue el fichero con el html del artículo, modifiquelo en su PC y subalo para generar una propuesta de modificación.</p>
        	    <p>La propuesta de modificación será revisada por los supervisores de la wiki/articulo y puede ver su estado en "Mis propuestas".</p> 
        	    <div class="menu-edicion">
	        	    	<ul>
			        	      <li>1 - Descargar HTML <a target="_blanck" href="${pageContext.request.contextPath}/html?articulo_id=${articulo.id}">DESCARGAR</a></li>
					          <li>2 - Subir propuesta de modificación 
					                  <form action="${pageContext.request.contextPath}/html" method="post" enctype="multipart/form-data">
					                  <input type="hidden" name="articulo_id" value="${articulo.id}" />
					                  <input type="file" name="edited" />
					                  <button type="submit">SUBIR</button>
					                  </form>
					           </li>
					          <li><a href="${pageContext.request.contextPath}/mis_propuestas">Mis propuestas</a></li>
	        	     	</ul>
        	    </div> 
        	      
		         
		    </c:if>
        	
        </div>
         </c:if>
    </jsp:body>
</t:public_page>