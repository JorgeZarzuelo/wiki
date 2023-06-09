<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:public_page>    
    <jsp:body>
		<div class="management">
            
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
             
             <h1>Revisión de propuesta de modificación de artículo</h1>
             <c:if test="${requestScope.supervision != null}">
              <h2> Modificación propuesta por:  ${requestScope.supervision.username}  sobre el artículo: ${requestScope.supervision.titulo_articulo} </h2>
              
              
              <div class="htmlcomptitles">
                <div class="antes"><h3>Original</h3></div>
                <div class="despues"><h3>Propuesta</h3></div>
              </div>
              <div class="htmlcomp">
               <div class="antes">${requestScope.supervision.original}</div>
               <div class="despues">${requestScope.supervision.propuesta}</div>
              </div>
              <c:if test="${requestScope.supervision.operaciones != null}">
              <div class="form">
	              
		              <h2> Cambios propuestos </h2>
		              <form action="${pageContext.request.contextPath}/revision" method="post">
		              <c:forEach var="operacion" items="${requestScope.supervision.operaciones}" varStatus="loop">
		                 <c:if test="${operacion.operacion != 'MANTENER'}">
		              		<div class="htmlcomp">
				               <div class="antes"><span class="${operacion.operacion}">${operacion.htmlOriginal}</span></div>
				               <div class="despues"><span class="${operacion.operacion}">${operacion.htmlPropuesto}</span></div>
				               <div class="operacion">Marque la casilla para aceptar el cambio, si la deja desmarcada se considera no aceptado:
				               <p><c:if test="${operacion.operacion == 'ADD'}"><input type="checkbox" name="add_operation_index" value="${loop.index}"> </c:if>
				               CAMBIO:${operacion.operacion} - INDEX:${operacion.initialPosition} </p>
				               <c:if test="${operacion.operacion == 'ELIMINAR'}"> <p>La eliminación se procesa automáticamente</p> </c:if>				               
				               </div>
				             </div>		                     
		                 </c:if>
		                 <c:if test="${operacion.operacion == 'ELIMINAR'}">
		                	 <input type="hidden" name="add_operation_index" value="${loop.index}" />
		                 </c:if>
		                 <c:if test="${operacion.operacion == 'MANTENER'}">
		                	 <input type="hidden" name="add_operation_index" value="${loop.index}" />
		                 </c:if>
		              </c:forEach>
		              <input type="hidden" name="revision_id" value="${requestScope.supervision.id}" />
		              <button type="submit">ACEPTAR CAMBIOS MARCADOS</button>
	                </form>
	            </div>    
	           </c:if>
             </c:if>
             
       </div>             
         

    </jsp:body>
</t:public_page>