<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
</head>
<body>
<main>
    <form action="/users" method="post">
        <div>
            <label>
                Имя
                <input type="text" name="firstName" />
            </label>
        </div>
        <div>
            <label>
                Фамилия
                <input type="text" name="lastName" />
            </label>
        </div>
        <div>
            <label>
                Email
                <input type="email" required name="email" />
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" required name="password" />
            </label>
        </div>
        <input type="submit" value="Зарегистрировать" />
    </form>
</main>
</body>
</html>