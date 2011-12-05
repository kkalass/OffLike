<%@include file="header.jsp"%>
  <div class="topbar">
      <div class="fill">
        <div class="container">
          <a class="brand" href="/"></a>
          <ul class="nav">
            <li class="active" ><a href="/">Home</a></li>
            <li><a href="/about.html">About</a></li>
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
            <h2>A Real-World "Like" for Campaigning and Advocacy</h2>
            <p>
       
 Supporting  things globally online is very easy and popular at the moment. There  are Systems like Google+1;FaceBook Like and Flatter - to just name 3. 
 Offlike  aims to extend this pattern to local "offline" things. By placing  QR-Codes ( or later NFC ) on real places with real problems - and giving  the user the option to support this thing we hope to increase  involvement with problems that matter for you - localy - offline .</p>
 
 <p>    How it works: First you create a new campaign at Off Like. Entering a title, description and the amount of QR codes you need. Then Off Like creates the needed codes. You print them and put them on your campaign poster. These posters are distributed in the local area where problems appear. Everytime somebody sees a poster, he or she scans the code with the Off Like App and likes the poster immediatly. If the person hasn't got the app, he or she is directed to the site where the person can 'like' the idea.  Statistics about the amount on views, likes and gps coordinates are viewable on offlike.com. This is how you can add a like button in the real-world.
             
            </p>
 <p>We created this service as part of the RHOK #4 in Berlin. For more background information visit our <a href="http://www.rhok.org/solutions/off-like" target="_blank">solution page</a>, the original <a href="http://www.rhok.org/node/18611" target="_blank">problem definition</a> or have a look at our <a href="/extra/presentation3.pdf">Presentation</a>.</p>
            
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