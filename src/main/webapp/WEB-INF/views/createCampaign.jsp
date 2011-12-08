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
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')"><li><a href="/login.html">Login</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">

      <div class="content">
        <div class="page-header">
          <h1>Create a new Campaign</h1>
        </div>
        <div class="row">
          <div class="span10">
             <p class="ol-large">Please complete all the fields (website not necessary).</p>
             
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
		          <div class="clearfix <c:if test="${errorMap.externalLink}" >error</c:if>">
		            <label for="title">Website</label>
		            <div class="input">
		              <input class="xlarge <c:if test="${errorMap.externalLink}" >error</c:if>" id="externalLink" value="${externalLink}" name="externalLink" size="30" type="text">
		              <span class="help-inline">${errorMap.externalLink}</span>
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