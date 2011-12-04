<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="#">Campaign</a></li>
            <li><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">

      <div class="content">
        <div class="page-header">
          <h1>${campaign.title}<small></small></h1>
        </div>
        <div class="row">
          <div class="span10">
            <h2>Description</h2>
            <p>${campaign.description}</p>
            <p>
            <c:if test="${campaign.externalLink}"><a href="${campaign.externalLink}">${campaign.externalLink}</a></c:if></p>
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
              <p><a href="${qrcode.qrCodeImageLink}" class="qrcode btn">Show QRCode</a></p>
            </c:forEach>
            
          </div>
        </div>

 	  <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>