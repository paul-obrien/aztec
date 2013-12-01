<div>
  ${component.capitalize()}: ${aztec.Report.RATINGS[rating]}
  <g:if test="${comment}">
    <a href="" class="details_link" id="${component}_details_${report.id}">Details &raquo;</a>
  </g:if>
</div>
<div class="report_component_comment" id="${component}_comment_${report.id}">
  ${comment}
</div>

