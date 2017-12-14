<%--
  Created by IntelliJ IDEA.
  User: jeremyvalenzuela
  Date: 12/13/17
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Hello World</title>
  <script src="https://unpkg.com/react@^16/umd/react.development.js"></script>
  <script src="https://unpkg.com/react-dom@^16/umd/react-dom.development.js"></script>
  <script src="https://unpkg.com/babel-standalone@^6/babel.min.js"></script>
</head>
<body>
<div id="root"></div>
<script type="text/babel">

    ReactDOM.render(
        <h1>Hello, world!</h1>,
        document.getElementById('root')
    );

</script>
</body>
</html>