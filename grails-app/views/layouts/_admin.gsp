Search for player:<input id="player_name"><br>
<span id="player_search_message"></span>          
<script type="text/javascript">
  $("#player_name").keypress(function(event) {
    if (event.which == 13) {
       $.ajax({
         url: '/aztec/player/search',
         data: {'player_name' : $("#player_name").val()},                 	  
       }).done(function(data) {
       if (data.id)
         window.location = "/aztec/player/" + data.id + "/reports";
       else
         $("#player_search_message").text("No player with the last name " + $("#player_name").val());                       
       });
     }
   });
</script>
