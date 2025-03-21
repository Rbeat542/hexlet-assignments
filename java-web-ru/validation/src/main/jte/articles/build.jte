@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Add course</title>
</head>
<body>
@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

<form action="/articles" method="post">
    <div>
        <label>
            Title
            <input type="text" required name="title" value="${page.getTitle()}" />
        </label>
    </div>
    <div>
        <label>
            Description
            <input type="text" required name="content" value="${page.getContent()}" />
        </label>
    </div>
    <input type="submit" value="Зарегистрировать" />
</form>
</body>
</html>
