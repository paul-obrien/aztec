<html>
    <head>
	    <title>Coach Homepage</title>
        <meta name="layout" content="standard"></meta>
    </head>
    <body>
		<div id="player_report">
			<h1>${player.firstName} ${player.lastName}</h1>

			<div class="training_attendance">
  				Training Attendance: ${player.trainingAttendance()[0]} / ${player.trainingAttendance()[1]}
			</div>

			<div class="report_header_row">
  				<div class="report_field report_date">Date</div>
  				<div class="report_field report_type">Report Type</div>
  				<div class="report_field reported_by">Reported By</div>
  				<div class="report_field report_details">Details</div>
  				<div class="report_field report_comment">Comment</div>
			</div>

			<g:each in="${player.reportsByDate()}" var="report">
   				<div class="report_row ${report.reportType}">
     				<div class="report_field report_date">
       					<g:formatDate format="MM-dd-yy" date="${report.date}"/>
     				</div>
     				<div class="report_field report_type">
       					${report.typeDisplayName()}
     				</div>
     				<div class="report_field reported_by">
       					${report.reportedBy()}
     				</div>
     
      	 		    <div class="report_field report_details">
     					<g:if test="${report.reportType == 'sessionCoach'}">
         					Rating: ${aztec.Report.SESSION_RATINGS[report.overallRating]}
     					</g:if>
     					<g:elseif test="${(report.reportType == 'periodicCoach' || report.reportType == 'self')}">
         					<g:render template="componentReport" 
         					  model="['component' : 'psychological', 'report' : report, 
         					          'rating' : report.psychologicalRating, 'comment' : report.psychologicalComment]" />
         					<g:render template="componentReport" 
         					  model="['component' : 'physical', 'report' : report, 
         					          'rating' : report.physicalRating, 'comment' : report.physicalComment]" />
         					<g:render template="componentReport" 
         					  model="['component' : 'technical', 'report' : report, 
         					          'rating' : report.technicalRating, 'comment' : report.technicalComment]" />
         					<g:render template="componentReport" 
         					  model="['component' : 'tactical', 'report' : report, 
         					          'rating' : report.tacticalRating, 'comment' : report.tacticalComment]" />
 	     				</g:elseif>
  	   					<g:elseif test="${report.reportType == 'injury'}">
 	      					Severity: ${report.injurySeverity ? "Severe" : "Minor" }
     					</g:elseif>
     				</div>
     				<div class="report_field report_comment">
       					${report.comment}
     				</div>
   				</div>
			</g:each>
		</div>
		<div id="report_options">
		  Show:<br>
		  <input id="periodicCoach" class="type_selector" type="checkbox" checked/>Periodic Coach Evaluations<br>
		  <input id="sessionCoach" class="type_selector" type="checkbox" checked/>Session Evaluations<br>
		  <input id="self" class="type_selector" type="checkbox" checked/>Self Evaluations<br>
		  <input id="injury" class="type_selector" type="checkbox" checked/>Injury Reports<br>
		</div>
		
		<script language="javascript">
        	$(".details_link").click(function() {
  		      	tokens = $(this).attr('id').split("_")
        		comment = $("#" + tokens[0] + "_comment_" + tokens[2])
         		if (comment.css('display') == 'none') {
           			comment.show();
           			$(this).html("Close &laquo;");
	  			}
				else {
					comment.hide();
					$(this).html("Details &raquo;");
				}

				return false;
			});

        	$(".type_selector").click(function() {
       	      	if ($(this).is(":checked"))
       	        	$("." + $(this).attr("id")).show();
       	     	else
       	       		$("." + $(this).attr("id")).hide();
       	   	});   
        </script>
	</body>
</html>