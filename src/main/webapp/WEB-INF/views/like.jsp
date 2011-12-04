<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>OffLike - Likes in the real world </title>
<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>

</head>
<body>
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) {return;}
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/de_DE/all.js#xfbml=1";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>

  <h1>Campaign ${campaignName}</h1>
  <p>Jetzt liken!</p>
  <p>
  <g:plusone href="http://stackoverflow.com"></g:plusone>
  <div class="fb-like" data-href="${url}" data-send="true" data-width="450" data-show-faces="true"></div>
  
 </p>
</body>
</html>
