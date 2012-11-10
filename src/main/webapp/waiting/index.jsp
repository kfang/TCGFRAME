<!DOCTYPE html>
<html>
<head>
	<script data-dojo-config="async: true, tlmSiblingOfDojo: true, deps: ['application.js']"
            src="${pageContext.request.contextPath}/dojo/dojo.js.uncompressed.js"></script>
	<title>Waiting Page</title>    
	<link rel="stylesheet" href="../bootstrap-combined.min.css" />
    <link rel="stylesheet" href="../style.css" />
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
   </script>
</head>

<body><div class="hero-unit container centered">
	<h1>Waiting Page</h1>
	<div style="text-align:center">
	<br />
	<label class="myLabel">Waiting Users:</label>
	<ul id="user_list"></ul>
	<div id="start_game"></div>
	
	<form id="game_form" action="../game" method="post"></form>

	</div>
</div></body>

</html>