<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/">My Overview</a></li></sec:authorize>
            <li><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')"><li class="active"><a href="/login.html">Login</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container">
     <div class="content">
        <div class="row">
            <div class="span14">
            </div>
            <div class="span6">
	            <h3>Login via google or yahoo:</h3>
	            <p>
		            <form action="j_spring_openid_security_check" class="form-stacked" method="post">
					  <input name="openid_identifier" size="50" maxlength="100" type="hidden" value="https://www.google.com/accounts/o8/id"/>
					  <input type="submit" class="btn primary" value="Sign in with Google"/>
					</form>
					<form action="j_spring_openid_security_check" class="form-stacked" method="post">
					  <input name="openid_identifier" size="50" maxlength="100" type="hidden" value="https://www.yahoo.com/"/>
					  <input type="submit" class="btn primary" value="Sign in with Yahoo"/>
					</form>
				</p>
		    </div>
		    <div class="span2">
		    </div>
			<div class="span6">
				<h3> Login using an <a href="http://openid.net/get-an-openid/">OpenID</a>:</h3>
				<form action="j_spring_openid_security_check" class="form-stacked" method="post">
				<div class="clearfix ">
				 <fieldset>
		            <label for="openid_identifier">Login:</label>
		            <div class="input">
		            <input id="openid_identifier" class="xlarge" name="openid_identifier" size="20" maxlength="100" type="text"/>
				  <!--img src="img/openid.png" alt="OpenID"/-->
		              <span class="help-inline">Enter your OpenID here.</span>
		            </div>
		          </div>
				  <div class="actions">
		            <input type="submit" class="btn primary" value="Login">&nbsp;<button type="reset" class="btn">Cancel</button>
		          </div>
				  
				   </fieldset>
				</form>
			</div>
        </div>
      </div>

      <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>