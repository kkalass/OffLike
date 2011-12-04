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
          <h1>Campaign <small></small></h1>
        </div>
        <div class="row">
          <div class="span10">
            <h2>Create a new Campaign</h2>
             <p>Please complete all the fields (website not necessary).</p>
             
            <form action="/createCampaign" method="POST">
              <fieldset>
		          <div class="clearfix <c:if test="${errorMap.title}" >error</c:if>">
		            <label for="title">Titel</label>
		            <div class="input">
		              <input class="xlarge <c:if test="${errorMap.title}" >error</c:if>" id="title" name="title" value="${title}" size="30" type="text">
		              <span class="help-inline">${errorMap.title}</span>
		            </div>
		          </div>
		          <div class="clearfix <c:if test="${errorMap.description}" >error</c:if>">
		            <label for="description">Description</label>
		            <div class="input">
		              <textarea class="xlarge <c:if test="${errorMap.description}" >error</c:if>" id="description" name="description" rows="5">${description}</textarea>
		              <span class="help-inline">${errorMap.description}</span>
		            </div>
		          </div>
		          <div class="clearfix <c:if test="${errorMap.refererUrl}" >error</c:if>">
		            <label for="title">Website</label>
		            <div class="input">
		              <input class="xlarge <c:if test="${errorMap.refererUrl}" >error</c:if>" id="externalLink" value="${externalLink}" name="externalLink" size="30" type="text">
		              <span class="help-inline">${errorMap.refererUrl}</span>
		            </div>
		          </div>
		          
		          <div class="actions">
		            <input type="submit" class="btn primary" value="Create Campaign">&nbsp;<button type="reset" class="btn">Cancel</button>
		          </div>
	          </fieldset>
            </form>
            
          </div>
          <div class="span4">
            
          </div>
        </div>

 	  <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>