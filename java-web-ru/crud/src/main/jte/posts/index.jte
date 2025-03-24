@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page
@param Integer pagePrev
@param Integer pageNext


<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>List of posts</title>
</head>
<body>
<h1>List of posts (from repository)</h1>

@if(PostRepository.getEntities().isEmpty())
    <p>Пока не добавлено ни поста</p>
@else
    @for(var post : page.getPosts())
        <div>
            <h2><a href="/posts/${post.getId()}">${post.getName()}</a></h2>
            <p>${post.getBody()}</p>
        </div>
    @endfor
@endif
<table>
    @if (pagePrev == 0)
        <tc></tc>
    @else
        <tc><a href="posts?page=${pagePrev}">Previous page</a></tc>
    @endif
    <tc>              </tc>
    @if (pageNext == 0)
        <tc></tc>
    @else
        <tc><a href="posts?page=${pageNext}">Next page</a></tc>
    @endif

</table>
</body>
</html>