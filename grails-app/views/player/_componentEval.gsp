<div>
  <span class="component_header">${component.capitalize()}:</span>
  <a href="" class="component_help_link" onClick="$('#${component}_help').show(); return false;">What is this?</a>
</div>
<div class="component_help" id="${component}_help">
  <div class="hide_help" >
    <a href=""onClick="$('#${component}_help').hide(); return false;">Hide</a>
  </div>
  <g:render template="${component}Help"/>
  <div class="hide_help">
    <a href="" onClick="$('#${component}_help').hide(); return false;">Hide</a>
  </div>
</div>
<div>
  <div class="component_rating">
    <div class="component_label">Rating:</div>
    <div class="component_field">
      <select name="${component}_rating">
        <g:each in="${aztec.Report.RATINGS}" var="item">
          <option value="${item.key}">${item.value}</option>
        </g:each>
      </select>
    </div>
  </div>
  <div class="component_comment">
    <div class="component_label">Comment:</div>
    <div class="component_field">
      <textarea name="${component}_comment" rows=4 cols=80></textarea>
    </div>
  </div>
</div>