<!DOCTYPE html>
<html>
<head>
    <title>Authorization Page</title>
</head>
<body>
<h2>Authorization Page</h2>
<form action="/login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username">
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
    </div>
    <input type="submit" value="Login">
</form>
</body>
</html>