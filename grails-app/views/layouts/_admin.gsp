<div class="menu_option">
  Search for player:<input class="search_box" id="player_name"><br>
  <div><span class="menu_error" id="player_search_message"></span></div>         
</div>
<div class="menu_option">
  Search for coach:<input class="search_box" id="coach_name"><br>
  <div><span class="menu_error" id="coach_search_message"></span></div>         
</div>
<div class="menu_option">
  <a href="/aztec/coach/create">Create a coach</a>
</div>
<div class="menu_option">
  <a href="/aztec/player/create">Create a player</a>
</div>
<script type="text/javascript">
  $(".search_box").keypress(function(event) {
    if (event.which == 13) {
       type = ($(this).attr('id') == 'coach_name') ? 'coach' : 'player'
       params = {}
       params[type + "_name"] = $(this).val();
       
       $.ajax({
         url: '/aztec/' + type + '/search',
         data: params,                 	  
       }).done(function(data) {
       if (data.id && type == 'player')
         window.location = "/aztec/player/reports/" + data.id;
       else if (data.id)
         window.location = "/aztec/coach/edit/" + data.id;
       else
         $("#" + type + "_search_message").text("No " + type + " with the last name " + $("#" + type + "_name").val());                       
       });
     }
   });
</script>
