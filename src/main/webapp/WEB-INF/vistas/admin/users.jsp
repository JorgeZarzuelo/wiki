<%@ page contentType="text/html;encoding=UTF-8" %>
<%@page import="wiki.entities.User" %>
<%@page import="wiki.entities.Rol" %>
<%@page import="wiki.entities.Wiki" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:public_page>    
    <jsp:body>
        
        <div class="management">
            
             <c:if test="${requestScope.mensaje != null}">
                 <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
             </c:if>
           
	        <h1>Administración de usuarios</h1>
	        
	        <div class="creation">
	             <h2>Crear usuario:</h2>
	        	
		        <form action="${pageContext.request.contextPath}/users" method="post">
		        
		           
			            <label for="username">Username: </label>
			            <input type="text" name="username" id="username">	        	
		        	
		        	
			            <label for="password">Password: </label>
			            <input type="password" name="password" id="password">	        	
		        	
		        	     <input type="hidden" name="operacion" value="crearUsuario" />
		        		<button type="submit">CREAR CUENTA</button>
		        	
		        </form>
	        
	        </div>
	        
	        <div class="creation">
	             <h2>Asignar rol:</h2>
	        	
		        <form action="${pageContext.request.contextPath}/users" method="post">
		        
		           
			            <label for="id_usuario">USUARIO: </label>
			            <select id="id_usuario" name="id_usuario">
			            	<c:if test="${requestScope.users != null}">
			            	   <c:forEach var="user" items="${requestScope.users}" >
			            	        <option value="${user.id}">ID:${user.id} - ${user.username}</option>
			            	   </c:forEach>
			            	</c:if>
			            </select>       	
		        	
		        	
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
		        	
		        	    <input type="hidden" name="operacion" value="addRole" />
		        		<button type="submit">ASIGNAR ROL</button>
		        	
		        </form>
	        
	        </div>
	        
	        <div class="user-list">
	        <h2>Lista de usuarios:</h2>
	          <c:if test="${requestScope.users != null}">
	        	<table>
		           <tr><th>ID</th><th>USERNAME</th><th>PASSWORD</th><th>ROLES</th><th></th><th></th></tr>
		           
			        <c:forEach var="user" items="${requestScope.users}" >
				         <tr>
				            <td>${user.id}</td>
				            <td>${user.username}</td>	
				            
					         <td>
					            <form action="${pageContext.request.contextPath}/users" method="post">
						            <input type="text" name="password"  value="${user.password}">
						            <input type="hidden" name="user_id" value="${user.id}" />				            	  
					            	<input type="hidden" name="operacion" value="editUser" />
				                <button type="submit">EDITAR</button>
				                </form>	
				            </td>	
				                       
					        <td>
						        <table class="no-border">
						            <c:if test="${user.roles != null}">
							        	<c:forEach var="rol" items="${user.roles}">
							        		<tr>
							        			<td class="no-border">							        			    
							        				ROL: ${rol.tipo} / WIKI: ${rol.wiki_id}
							        			</td>
							        			<td class="no-border">
							        			    <form action="${pageContext.request.contextPath}/users" method="post">
								        			    <input type="hidden" name="rol_id" value="${rol.id}" />
								        			    <input type="hidden" name="operacion" value="deleteRol" />
								        				<button class="delete" type="submit">REVOCAR</button>
							        				</form>
							        			</td>
							        		</tr> 
							        	</c:forEach>
						        	</c:if>
						        </table>
					        </td>					        	
				            <td>
				                  <form action="${pageContext.request.contextPath}/users" method="post">
				                     <input type="hidden" name="operacion" value="deleteUser" />
				                     <input type="hidden" name="user_id" value="${user.id}" />
				           			 <button class="delete" type="submit">ELIMINAR USUARIO</button>
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