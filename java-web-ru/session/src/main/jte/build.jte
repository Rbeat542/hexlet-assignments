@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Authorization page</title>
</head>
@if(page.getError() != null && page.getError() != "")
    <h2>${page.getError()}</h2>
@endif
<br>
<h3>Страница авторизации</h3>
<form action="${NamedRoutes.loginPath()}" method="post">
    <input type="text" placeholder="Name" name="name"/>
    <input type="password" placeholder="Password" name="password" />
    <input type="submit" />
</form>