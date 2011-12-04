<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>OffLike - Likes in the real world </title>


   <c:forEach items="${qrcodeList}" var="qrcode">
              <p><img src="${qrcode}" class="qrcode"/></p>
    </c:forEach>
    
    </body>
</html>
    