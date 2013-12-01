<html>
    <head>
	    <title>Coach Homepage</title>
        <meta name="layout" content="standard"></meta>
    </head>
    <body>
      <h1>${team.name} Training Session Report</h1>
      <g:form name="sessionReport" url="[action:'submitSessionReport',controller:'team']">
        <input type="hidden" name="team_id" value="${team.id}"/>
        <g:each in="${team.playersByLastName()}" var="player">
          <div class="player_rating">
            <div class="player_name">
              ${player.firstName} ${player.lastName}:
            </div>
            <div class="player_select">
              <select name="player_${player.id}">
                <g:each in="${aztec.Report.SESSION_RATINGS}" var="item">
                  <option value="${item.key}">${item.value}</option>
                </g:each>
              </select>
            </div>
          </div>
        </g:each>
        <div id="injury_report">
          <div class="injury_field">
            Injury:
            <select id="injury_severity" name="injury_severity">
              <option value="">
              <option value="-1">None
              <option value="0">Minor
              <option value="1">Severe
            </select>
          </div>
          <div class="injury_field hidden_injury_field">
            Player:
            <select name="injured_player_id">
              <g:each var="player" in="${team.playersByLastName()}">
                <option value="${player.id}">${player.lastName}, ${player.firstName}</option>
              </g:each>
            </select>
          </div>
          <div class="injury_field hidden_injury_field">
            Description:
            <textarea name="injury_comment" rows=4 cols=80></textarea>
          </div>
        </div>
        <div id="submit_button">
          <input id="submit" type="submit" value="Submit Session Report"/>
        </div>
      </g:form>
      <script language="javascript">
         $("#injury_severity").change(function() {
            if (!$("#injury_severity").val()) {
                $(".hidden_injury_field").hide();
                $("#submit_button").hide();
            }
            else if ($("#injury_severity").val() == "-1") {
                $(".hidden_injury_field").hide();
                $("#submit_button").show();
            }
            else {
                $(".hidden_injury_field").show();
                $("#submit_button").show();
            }
         })
      </script>
   </body>
</html>
      
      