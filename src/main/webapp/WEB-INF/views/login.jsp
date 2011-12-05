<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <li><a href="/">Home</a></li>
            <li class="active"><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">
     <div class="content">
        <div class="row">
          <div class="span10">
            <h2>Login</h2>
            <p></p>
            <form action="j_spring_openid_security_check" method="post">
			  <input name="openid_identifier" size="50" maxlength="100" type="hidden" value="https://www.google.com/accounts/o8/id"/>
			  <input type="submit" value="Sign in with Google"/>
			</form>
          </div>
          <div class="span4">
          </div>
        </div>
      </div>

      <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>