<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/">My Overview</a></li></sec:authorize><li><a href="/"><sec:authorize access="hasRole('ROLE_ANONYMOUS')">Home</sec:authorize><sec:authorize access="hasRole('USER_ROLE')">My Campaigns</sec:authorize></a></li>
            <li><a href="/about.html">About</a></li>
            <li class="active"><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')"><li><a href="/login.html">Login</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>
    
   <div class="container">
 	<div class="content">
        <div class="row">
          <div class="span10">
            <h2>Contact </h2>
            <p>Please contact RHoK Berlin for more information.</p>
          </div>
          <div class="span4">
          </div>
        </div>
      </div>

      <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>