@import exercise.dto.posts.PostsPage
@import exercise.repository.PostRepository
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(

content = @`
    <div class="mb-3">
        <h1>List of posts</h1>
    </div>
    <br>
    @if(PostRepository.getEntities().isEmpty())
        <p>No posts added yet! </p>
    @else
        @if(page != null && page.getFlash() != null)
            <div class="alert alert-success" role="alert">
                ${page.getFlash()}
            </div>
        @endif
        <div class="mb-3">
            @for(var post : PostRepository.getEntities())
                <div>
                    <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getId()} </a> ${post.getName()} ${post.getBody()}</h2>
                </div>
            @endfor
        </div>
    @endif `
)
