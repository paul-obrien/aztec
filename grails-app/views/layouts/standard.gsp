<html>
    <head>
      	<r:require modules="core"/>
      	<r:layoutResources/>
        <title><g:layoutTitle default="Aztec Soccer Club" /></title>
        <g:layoutHead />
    </head>
    <body onload="${pageProperty(name:'body.onload')}">
      	<r:layoutResources/>
        <div class="menu">
          <sec:ifAllGranted roles="ROLE_ADMIN">
             <g:render template="/layouts/admin"/> 
          </sec:ifAllGranted>
          <sec:ifAllGranted roles="ROLE_COACH">
             <g:render template="/layouts/coach"/> 
          </sec:ifAllGranted>
          <sec:ifAllGranted roles="ROLE_PLAYER">
             <g:render template="/layouts/player"/> 
          </sec:ifAllGranted>
        </div>
        <div class="body">
          <g:layoutBody />
        </div>
    </body>
</html>         