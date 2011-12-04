<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>OffLike - Likes in the real world </title>

<script type="text/javascript">
  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
</script> 

<body>
  <h1>Campaign ${campaignName}</h1>
  <p>Jetzt liken!</p>
  <p>
  
		<g:plusone size="medium" annotation="inline"></g:plusone>
 </p>
</body>
</html>
