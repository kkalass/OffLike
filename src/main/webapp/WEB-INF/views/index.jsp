<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <sec:authorize access="hasRole('USER_ROLE')"><li  class="active"><a href="/">My Overview</a></li></sec:authorize>
            <li><a href="/about.html">About</a></li>
            <li><a href="/contact.html">Contact</a></li>
            <sec:authorize access="hasRole('USER_ROLE')"><li><a href="/j_spring_security_logout">Logout</a></sec:authorize>
            <sec:authorize access="hasRole('ROLE_ANONYMOUS')"><li><a href="/login.html">Login</a></sec:authorize>
          </ul>
         
        </div>
      </div>
    </div>

    <div class="container homepage">
    
      <div class="content">
        <div class="row">
          <div class="span10">
          <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
            <h2>A Real-World "Like" for Campaigning and Advocacy</h2>
            <p class="ol-large">
       
 Supporting  things globally online is very easy and popular at the moment. There  are Systems like Google+1;FaceBook Like and Flatter - to just name 3. 
 Offlike  aims to extend this pattern to local "offline" things. By placing  QR-Codes ( or later NFC ) on real places with real problems - and giving  the user the option to support this thing we hope to increase  involvement with problems that matter for you - localy - offline .</p>
 
 <p class="ol-large">    How it works: First you create a new campaign at Off Like. Entering a title, description and the amount of QR codes you need. Then Off Like creates the needed codes. You print them and put them on your campaign poster. These posters are distributed in the local area where problems appear. Everytime somebody sees a poster, he or she scans the code with the Off Like App and likes the poster immediatly. If the person hasn't got the app, he or she is directed to the site where the person can 'like' the idea.  Statistics about the amount on views, likes and gps coordinates are viewable on offlike.com. This is how you can add a like button in the real-world.
             
            </p>
 <p class="ol-large">We created this service as part of the RHOK #4 in Berlin. For more background information visit our <a href="http://www.rhok.org/solutions/off-like" target="_blank">solution page</a>, the original <a href="http://www.rhok.org/node/18611" target="_blank">problem definition</a> or have a look at our <a href="/extra/presentation3.pdf">Presentation</a>.</p>
            
          </sec:authorize>
            
          <sec:authorize access="hasRole('USER_ROLE')">
            <div>
            
            <h2>My Campaigns</h2>
            <c:if test="${not empty campaigns}">
	            <p>
	            <table border=0 class="zebra-striped bordered-table">
	                <tr><th>Title</th><th>External Link</th></tr>
	                <c:forEach items="${campaigns}" var="campaign">
		              <tr><td><a href="/campaign/${campaign.id}">${campaign.title}</a></td><td><a href="${campaign.externalLink}">${campaign.externalLink}</a></td></td>
				    </c:forEach>
	            </table>
	            </p>
	         </c:if>
            <c:if test="${empty campaigns}">
	           <p>Here you will get an overview of your campaigns, and this is how it works: </p>
	           <ul>
	             <li>First you create a new campaign</li>
	             <li>Entering a title, description and the amount of QR codes you need</li>
	             <li> Then Off Like creates the needed codes</li>
	             <li>You print them and put them on your campaign poster</li>
	             <li>These posters are distributed in the local area where problems appear</li>
	             <li>Everytime somebody sees a poster, he or she scans the code with the Off Like App and likes the poster immediatly. 
	             If the person hasn't got the app, he or she is directed to the site where the person can 'like' the idea.</li>
	             <li>Statistics about the amount on views, likes and gps coordinates are viewable on offlike.com. </li>
	           </ul>
	           <p>This is how you can add a like button in the real-world!</p>
	         </c:if>
            </div>
          </sec:authorize>
            <div><a class="btn primary" href="/createCampaign">Create new Campaign</a></div>
          </div>
          <div class="span4">
            
            <a  href="https://market.android.com/details?id=org.rhok.offlike&feature=search_result#?t=W251bGwsMSwxLDEsIm9yZy5yaG9rLm9mZmxpa2UiXQ"><img src="/img/androidphone.png"/></a></p>
          </div>
        </div>
      </div>

      <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>