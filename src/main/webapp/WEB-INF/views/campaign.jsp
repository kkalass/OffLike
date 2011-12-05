<%@include file="header.jsp"%>
<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
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
          <a class="brand" href="/"></a>
          <ul class="nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="#">Campaign</a></li>
            <li><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">

      <div class="content">
        <div class="page-header">
          <span class="label important">VERY IMPORTANT</span> Please copy  and save the URL,
             so you can change your individual campaign later on.
        </div>
        <div class="row">
          <div class="span10">
            <h2>${campaign.title}</h2>
            <p>${campaign.description}</p>
            <p>
            <c:if test="${campaign.externalLink}"><a href="${campaign.externalLink}">${campaign.externalLink}</a></c:if></p>
            
            <div><img src="${mapUrl}" border=0></div>
            <h3>Create new QR Codes</h3>
            <form action="/createQrCodes" method="POST">
              <fieldset>
                  <input type="hidden" name="campaignid" value="${campaign.id}">
                  
		          <div class="clearfix <c:if test="${errorMap.numberOfCodes}" >error</c:if>">
		            <label for="title">Number of Codes</label>
		            <div class="input">
		              <input class="xlarge <c:if test="${errorMap.numberOfCodes}" >error</c:if>" id="numberOfCodes" name="numberOfCodes" value="${numberOfCodes}" size="30" type="text">
		              <span class="help-inline">${errorMap.numberOfCodes}</span>
		            </div>
		          </div>
		          
		          <div class="actions">
		            <input type="submit" class="btn primary" value="Create New QR Codes">&nbsp;<button type="reset" class="btn">Cancel</button>
		          </div>
	          </fieldset>
            </form>
          </div>
          <div class="span4">
          <h3>Existing Locations</h3>
            <c:forEach items="${qrcodeList}" var="qrcode">
              <div class="qrcode">
	              <p><a href="${qrcode.qrCodeImageLink}" class=" btn">Show QRCode</a></p>
	              <g:plusone href="${qrcode.likeUrl}"></g:plusone>
	 			 <div class="fb-like" data-href="${qrcode.likeUrl}" data-send="true" data-width="220" data-show-faces="true"></div>
	  
	              <hr>
              </div>
            </c:forEach>
            
          </div>
        </div>

 	  <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>