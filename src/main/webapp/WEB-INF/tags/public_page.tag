<%@tag description="Plantilla de public page" pageEncoding="UTF-8"%>


<jsp:include page="/WEB-INF/fragments/header.jsp" />
<main>
   <div class=main-wrapper>
		<div class="main">
		   <jsp:doBody/>
		</div>
	</div>
</main>
<jsp:include page="/WEB-INF/fragments/footer.jsp" />
