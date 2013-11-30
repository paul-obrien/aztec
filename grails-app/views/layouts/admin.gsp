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
          Search for player:<input id="player_name"><br>
          <span id="player_search_message"></span>          
        </div>
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
            <div class="body">
                <g:layoutBody />
            </div>
        </div>
    </body>
</html>