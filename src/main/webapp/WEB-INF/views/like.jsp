<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>OffLike - Likes in the real world </title>

<script type="text/javascript" src="http://apis.google.com/js/plusone.js">{lang: 'de', parsetags: 'explicit'}</script>

<script type="text/javascript">
var console = window['console'] || {'log':function(){}};

</script> 

<body>
  <h1>Campaign ${campaignName}</h1>
  <p>Jetzt liken!</p>
  <p>
	<g:plusone href="http://www.test.de" size="medium"></g:plusone>
	<script type="text/javascript">gapi.plusone.go();</script>
  
 </p>
</body>
</html>
