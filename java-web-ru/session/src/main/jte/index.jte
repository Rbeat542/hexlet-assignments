@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello !</title>
</head>
<body>
<main>
    <h1>Привет !</h1>
    @if(page.getName() != null)
        Добро пожаловать, ${page.getName()}.
        <br>
        Чтобы разлогиниться, удалите куку из браузера.

        <form action="${NamedRoutes.logoutPath()}" method="post">

        <button type="submit">Выйти</button>
        </form>
    @endif
</main>
<br>
<div>
    @if(page.getName() == null)
        Добро пожаловать, неизвестный пользователь!
        <br>
        Перейдите по ссылке для авторизации.
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif

</div>
</body>
</html>

