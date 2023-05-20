<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:public_page>    
    <jsp:body>
        <div class="login">
	        <h2>LOGIN</h2>
	        <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
	        <form action="${pageContext.request.contextPath}/login" method="post">
	        
	           <div class="field">
		            <label for="username">Username: </label>
		            <input type="text" name="username" id="username">	        	
	        	</div>
	        	<div class="field">
		            <label for="password">Password: </label>
		            <input type="password" name="password" id="password">	        	
	        	</div>
	        	<div class="field">
	        		<button type="submit">ENVIAR</button>
	        	</div>
	        </form>
	        <div class="no-account">
	        	<p>¿Aún no tiene una cuenta? <a href="${pageContext.request.contextPath}/registro">Crear cuenta</a><p>
	        </div>
        </div>
    </jsp:body>
</t:public_page>