<html>
    <head>
	    <title>Player: ${player.firstName} ${player.lastName}</title>
        <meta name="layout" content="standard"></meta>
    </head>
    <body>
      <div id="coach_form">
        <div class="page_header">${player.firstName} ${player.lastName}</div>
        <g:if test="${doneSubmit}">
          <div class="submit_confirm">Player has been updated</div>
        </g:if>
        <g:form name="editPlayer" url="[action:'editSubmit',controller:'player']">
          <input type="hidden" name="player_id" value="${player.id}"/>
          <div class="fieldcontain">
            <div class="form_label">First Name:</div>
            <div class="form_input"> <g:field name="firstName" value="${player?.firstName}"/></div>
          </div>
          <div class="fieldcontain">
            <div class="form_label">Last Name:</div>
            <div class="form_input"> <g:field name="lastName" value="${player?.lastName}"/></div>
          </div>
          <div class="fieldcontain">
            <div class="form_label">Email:</div>
            <div class="form_input"><g:field name="email" value="${player?.email}"/></div>
          </div>
          <div class="fieldcontain">
            <div class="form_label">Date of Birth:</div>
            <div class="form_input">
              <g:datePicker name="dateOfBirth" value="${player?.dateOfBirth}" precision="day" years="${1995..2007}"/>
            </div>
          </div>
          <div class="fieldcontain"><input type="submit" value="Submit"/></div>
        </g:form>
      </div>
    </body>
</html>