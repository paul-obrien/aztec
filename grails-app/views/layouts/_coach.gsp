<div class="menu_option team_menu_section">
  <g:each var="team" in="${currentUser.teams}">
    <g:form id="player_action" name="select_player_for_evaluation" url="[action:'evaluate',controller:'player']" method="get">
      <input type="hidden" name="team_id" value="${team.id}"/>
      <div class="menu_team_name">${team.name}:</div>
      <div class="team_menu_option">
          <g:select name="player_id" from="${team.playersByLastName()}" value="${player?.id}"
                    optionKey="id" optionValue="lastNameFirst"/>        
          <!-- <select id="player_select" name="player_id">
          <g:each var="player" in="${team.playersByLastName()}">
            <option value="${player.id}">${player.lastName}, ${player.firstName}</option>
          </g:each>
        </select> -->
      </div>
      <div class="team_menu_option">
        <a id="evaluate_link" href="" onClick="$('#player_action').submit(); return false;">Evaluate Player</a>
      </div>
      <div class="team_menu_option">
        <a id="view_reports_link" href="">View Player Reports</a>
      </div>
    </g:form>
    <script language="javascript">
      $("#view_reports_link").click(function() {
         window.location = "/aztec/player/reports/" + $("#player_id").val();
         return false;
      });
    </script>
    <div class="team_menu_option">
      <a href="/aztec/team/sessionReport/${team.id}">Report a training session</a> 
    </div>
  </g:each>
</div>

