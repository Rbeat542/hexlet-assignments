@import exercise.dto.posts.PostPage
@param PostPage page

<html lang="en">
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

<div class="mx-auto p-4 py-md-5">
<main>
    <h3>${page.getPost().getName()}</h3>
    <p>${page.getPost().getBody()}</p>
    <br>
    <div><a href="/posts">Вернуться к списку постов</a> </div>
</main>
</div>

</body>
</html>

