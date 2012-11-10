<!DOCTYPE html>
<html>
<head>
	<script data-dojo-config="async: true, tlmSiblingOfDojo: true, deps: ['application.js']"
            src="${pageContext.request.contextPath}/dojo/dojo.js.uncompressed.js"></script>
	<title>Waiting Page</title>
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
   </script>
</head>

<body>
	<h1>Waiting Page</h1>
	<label>Waiting Users:</label>
	<ul id="user_list"></ul>
	<div id="start_game"></div>
</body>

</html>