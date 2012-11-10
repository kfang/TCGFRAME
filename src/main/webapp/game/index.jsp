<!DOCTYPE html>
<html>
<head>
	<script data-dojo-config="async: true, tlmSiblingOfDojo: true, deps: ['application.js']"
            src="${pageContext.request.contextPath}/dojo/dojo.js.uncompressed.js"></script>
	<title>Waiting Page</title>
    <link rel="stylesheet" href="style.css" />
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
   </script>
</head>

<body>
	<h1>Game Page</h1>
	<div id="gs">
		<div id="supply"></div>
		<div id="phases"></div>
		<div id="stats"></div>
		<div id="play"></div>
		<div id="hand"></div>
	</div>
</body>

</html>