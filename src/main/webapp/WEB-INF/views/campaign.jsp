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
            <li><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')"><li><a href="/login.html">Login</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">

      <div class="content">
        <ul class="breadcrumb">
			  <li><a href="/">My Campaigns</a> <span class="divider">/</span></li>
			  <li class="active">${campaign.title}</li>
			</ul>
        <div class="row">
          <div class="span8">
          <h2>${campaign.title}</h2>
            <h3>Campaign Description</h3>
            <p>${campaign.description}</p>
            <p>
            <c:if test="${not empty campaign.externalLink}"><a href="${campaign.externalLink}">${campaign.externalLink}</a></c:if></p>
            
            <c:if test="${not empty mapUrl}">
              <div><img src="${mapUrl}" border=0></div>
            </c:if>
            <h3>Create new QR Codes</h3>
            <p>Generate and print one QR Code for every location where you want people to be able to like your campaign.<p>
            <ol>
              <li>Print your QR Codes</li>
              <li>Stick them on your Posters</li>
              <li>Bring your Posters to their final location and put them up</li>
              <li>Use our Android App to scan the QR Code, making sure that your GPS is working so that the poster can be associated by the android application with the location where it was put up</li>
            </ol>
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
          <div class="span6">
          <h3>Existing Locations</h3>
            <c:if test="${empty qrcodeList}">
				<p>Once you have generated QR Codes, they will be shown here.</p>
            </c:if>
            <c:if test="${not empty qrcodeList}">
	            <c:forEach items="${qrcodeList}" var="qrcode">
	              <div class="qrcode">
		              <p><a href="${qrcode.qrCodeImageLink}" class=" btn">Show QRCode</a></p>
		              <g:plusone href="${qrcode.likeUrl}"></g:plusone>
		 			 <div class="fb-like" data-href="${qrcode.likeUrl}" data-send="true" data-width="200" data-show-faces="true"></div>
		  
		              <hr>
	              </div>
	            </c:forEach>
            </c:if>
            
          </div>
        </div>

 	  <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>