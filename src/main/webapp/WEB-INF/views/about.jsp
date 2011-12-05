<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">
     <div class="content">
        <div class="row">
          <div class="span10">
            <h2>About</h2>
            <p>This website was created for RHoK in Berlin on 3rd - 4th december 2011 </p>
          </div>
          <div class="span4">
          </div>
        </div>
      </div>

      <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>