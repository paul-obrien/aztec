<html>
    <head>
	    <title>
	      <g:if test="${coach}">
	        Coach: ${coach.firstName} ${coach.lastName}
	      </g:if>
	      <g:else>
	        New Coach
	      </g:else>
	    </title>
        <meta name="layout" content="standard"></meta>
    </head>
    <body>
      <div id="coach_form">
        <div class="page_header">
          <g:if test="${coach}">
            ${coach.firstName} ${coach.lastName}
          </g:if>
          <g:else>
            New Coach
          </g:else>
        </div>
        <g:if test="${doneSubmit}">
          <div class="submit_confirm">Coach has been updated</div>
        </g:if>
        <g:form name="editCoach" url="[action:'editSubmit',controller:'coach']">
          <input type="hidden" name="coach_id" value="${coach?.id}"/>
          <div class="fieldcontain">
            <div class="form_label">First Name:</div>
            <div class="form_input"> <g:field name="firstName" value="${coach?.firstName}"/></div>
          </div>
          <div class="fieldcontain">
            <div class="form_label">Last Name:</div>
            <div class="form_input"> <g:field name="lastName" value="${coach?.lastName}"/></div>
          </div>
          <div class="fieldcontain">
            <div class="form_label">Email:</div>
            <div class="form_input"><g:field name="email" value="${coach?.email}"/></div>
          </div>
          <div class="fieldcontain"><input type="submit" value="Submit"/></div>
        </g:form>
      </div>
    </body>
</html>