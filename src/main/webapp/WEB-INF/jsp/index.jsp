<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<body>

	<!-- Header -->
    <jsp:include page="/WEB-INF/jsp/header/headerDeconnecte.jsp">
        <jsp:param value="headerDeconnecte" name="headerDeconnecte"></jsp:param>
    </jsp:include>
    
	<!-- Main-->
    <jsp:include page="/WEB-INF/jsp/main/mainDeconnecte.jsp">
        <jsp:param value="mainDeconnecte" name="mainDeconnecte"></jsp:param>
    </jsp:include>
	
	<!-- Footer -->

    <jsp:include page="/WEB-INF/jsp/footer/footer.jsp">
        <jsp:param value="footer" name="footer"></jsp:param>
    </jsp:include>


</body>
</html>