@import exercise.dto.users.UsersPage
@param UsersPage page

<html>
    <head>
        <title>Hexlet</title>
    </head>
    <body>
        <h1>${page.getHeader()}</h1>
        @if(page.getUsers().isEmpty())
            <p>Пользователей пока нет</p>
        @else
        <table>
            @for(var user : page.getUsers())
               <tr>
               <td>${user.getFirstName()} ${user.getLastName()}</td>
               <td><a href="/users/${user.getId()}">${user.getEmail()}</a></td>
               </tr>

            @endfor
        </table>
        @endif
    </body>
</html>