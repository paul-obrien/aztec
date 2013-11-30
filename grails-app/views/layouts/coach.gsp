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
          <div>Evaluate a player:</div>
          <div>
            <g:each var="team" in="${coach.teams}">
              <g:form name="selectPlayerForEvaluation" url="[action:'evaluate',controller:'player']" method="get">
                <input type="hidden" name="team_id" value="${team.id}"/>
                ${team.name}:
                <select name="player_id">
                  <g:each var="player" in="${team.playersByLastName()}">
                    <option value="${player.id}">${player.lastName}, ${player.firstName}</option>
                  </g:each>
                </select>
                <input type="submit" value="Evaluate"/><br>
              </g:form>
            </g:each>
          </div>
        </div>
        <div class="body">
           <g:layoutBody />
        </div>
    </body>
</html>