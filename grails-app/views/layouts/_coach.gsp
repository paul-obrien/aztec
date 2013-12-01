<div>
  <g:each var="team" in="${currentUser.teams}">
    <g:form id="player_action" name="select_player_for_evaluation" url="[action:'evaluate',controller:'player']" method="get">
      <input type="hidden" name="team_id" value="${team.id}"/>
      <div>${team.name}:</div>
      <select id="player_select" name="player_id">
        <g:each var="player" in="${team.playersByLastName()}">
          <option value="${player.id}">${player.lastName}, ${player.firstName}</option>
        </g:each>
      </select>
      <a id="evaluate_link" href="" onClick="$('#player_action').submit(); return false;">Evaluate</a>
      <a id="view_reports_link" href="">View Reports</a>
    </g:form>
    <script language="javascript">
      $("#view_reports_link").click(function() {
         window.location = "/aztec/player/reports/" + $("#player_select").val();
         return false;
      });
    </script>
    <div>
      <a href="/aztec/team/sessionReport/${team.id}">Report a training session</a> 
    </div>
  </g:each>
</div>
