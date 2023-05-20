<%@ page contentType="text/html;encoding=UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:public_page>    
    <jsp:body>
        <div class="registro">
	        <h2>REGISTRO</h2>
	        <div class="mensaje">${ requestScope.mensaje != null ? mensaje : '' }</div>
	        <form action="${pageContext.request.contextPath}/registro" method="post">
	        
	           <div class="field">
		            <label for="username">Username: </label>
		            <input type="text" name="username" id="username">	        	
	        	</div>
	        	<div class="field">
		            <label for="password">Password: </label>
		            <input type="password" name="password" id="password">	        	
	        	</div>
	        	<div class="field">
	        		<button type="submit">CREAR CUENTA</button>
	        	</div>
	        </form>
	        <div class="no-account">
	        	<p>¿Ya tiene una cuenta? <a href="${pageContext.request.contextPath}/login">Login</a><p>
	        </div>
        </div>
    </jsp:body>
</t:public_page>