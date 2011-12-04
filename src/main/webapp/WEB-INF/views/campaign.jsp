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
          <h1>${title}<small></small></h1>
        </div>
        <div class="row">
          <div class="span10">
            <h2>Description</h2>
            <p>${description}</p>
          </div>
          <div class="span4">
            <h3>Secondary content</h3>
          </div>
        </div>

 	  <%@include file="footer.jsp"%>

   </div> <!-- /container -->


<%@include file="end.jsp"%>