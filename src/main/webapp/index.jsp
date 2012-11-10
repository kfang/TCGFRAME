<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <script data-dojo-config="async: true, tlmSiblingOfDojo: true, deps: ['application.js']"
            src="${pageContext.request.contextPath}/dojo/dojo.js.uncompressed.js"></script>
    <%--
    The reason to use a JSP is that it is very easy to obtain server-side configuration
    information (such as the contextPath) and pass it to the JavaScript environment on the client.
    --%>
    <link rel="stylesheet" href="bootstrap-combined.min.css" />
    <link rel="stylesheet" href="style.css" />
    <script type="text/javascript">
        var config = {
            contextPath: '${pageContext.request.contextPath}'
        };
    </script>
    <title>Join a Game</title>
</head>
<body>
	<div class="container centered hero-unit">
		<h1 class="page-header">Join a Game</h1>

    <h2>CometD Tutorial</h2>

    <div id="status"></div>
        <button id="greeter">
        Send Hello to Server
    </button>
    
    <h3>Game List:</h3>
    <div id="games_list">
    emptylist
    </div>

		<div class="input">
			<form action="waiting" onsubmit="get_a_group();" method="post">
				<div>
					<span class="label label-info mylabel">Username:</span>
					<input class="input" id="username" type="text" name="username"/>
				</div>
				<div>
					<input class="btn btn-primary" type="submit" value="Join game" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>
