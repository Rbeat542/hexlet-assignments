@import exercise.dto.users.UsersPage
@param UsersPage page


<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>Hello Hexlet!</title>
</head>
<body>
<header class="mx-auto p-4 py-md-5"><a href="/">Главная страница </a>
    <br>
<a href="/users">Пользователи</a>
    <hr>
</header>
<main>
    <div class="mx-auto p-4 py-md-4">
        <div>
            <form action="/users" method="get">
                <input type="search" required name="term">
                <input type="submit" value="Find">
            </form>
        </div>

        @if(page.getUsers().isEmpty())
            <p></p>
        @else
            @for(var user : page.getUsers())
                <div>
                    <h2><a href="/users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></h2>
                    <p>${user.getEmail()}</p>
                </div>
            @endfor
        @endif
    </div>
</main>
</body>
</html>