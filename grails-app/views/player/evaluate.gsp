<html>
    <head>
	    <title>Evaluation for ${player.firstName} ${player.lastName}</title>
        <meta name="layout" content="standard"></meta>
    </head>
    <body>
      <div id="player_evaluation">
        <div class="page_header">Evaluation for ${player.firstName} ${player.lastName}</div>
        <g:form name="playerEvaluation" url="[action:'submitEvaluation',controller:'player']">
          <input type="hidden" name="player_id" value="${player.id}"/>
          <input type="hidden" name="team_id" value="${team.id}"/>
          <g:render template="componentEval" model="['component' : 'psychological']" />
          <g:render template="componentEval" model="['component' : 'physical']" />
          <g:render template="componentEval" model="['component' : 'technical']" />
          <g:render template="componentEval" model="['component' : 'tactical']" />
          <div id="overall_evaluation">
            <div class="component_label">Overall comment:</div>
            <div class="component_field">
              <textarea name="overall_comment" rows=4 cols=80></textarea>
            </div>
          </div>
          <input type="submit" value="Submit Evaluation"/>
        </g:form>
      </div>
    </body>
</html>