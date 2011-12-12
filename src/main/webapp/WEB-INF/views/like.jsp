<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${campaign.title}</title>
	<meta charset="utf-8">

    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le styles -->
    <link href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">

    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta property="og:title" content="${campaign.title}" />
	<meta property="og:type" content="cause" />
	<meta property="og:url" content="${url}" />
	<meta property="og:image" content="" />
	<meta property="og:site_name" content="Offlike - Likes in the offline world" />
	<meta property="fb:admins" content="${fbAdmins}" />

	<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
	<script type="text/javascript">
	
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-27674780-1']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	
	</script>
</head>
<body class="likepage">
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) {return;}
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/de_DE/all.js#xfbml=1";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>

    <div class="topbar">
      <div class="fill">
        <div class="container">
        <h3>${campaign.title}</h3>
          <!--p>Like this campaign? "like" this Page!</p-->
        </div>
      </div>
    </div>

    <div class="container">
    
      <div class="content">
        <div class="row">
          <div class="span10">
			  
			  <p>${campaign.description}</p>
			
			  <p><c:if test="${not empty campaign.externalLink}"><a href="${campaign.externalLink}">${campaign.externalLink}</a></c:if></p>
			  <h3>Support us, right here!</h3>
			  <p>Please show your support for our campaign right here where you scanned our Code! When
			  you like this page, you will show that you consider this campaign to be important at this location.
			  <g:plusone href="${url}"></g:plusone>
			  <div class="fb-like" data-href="${url}" data-send="true" data-width="450" data-show-faces="true"></div>
          </div>
       </div>
       
      <footer>
          <p>Powered by <a href="http://offlike.herokuapp.com">Off Like</a>: Bringing "Like" to offline places</p>
      </footer>
     </div>
   </div>  
 </p>
</body>
</html>
