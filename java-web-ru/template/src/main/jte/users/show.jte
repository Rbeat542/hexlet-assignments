<!-- src/main/jte/users/show.jte -->
@import exercise.dto.users.UserPage
@param UserPage page

<!-- Для наглядности мы не стали включать сюда основную структуру HTML -->
<main>
    <h1>${page.getUser().getId()}</h1>
    <p>${page.getUser().getFirstName()}</p>
    <p>${page.getUser().getLastName()}</p>
    <p>${page.getUser().getEmail()}</p>
    <br>
</main>