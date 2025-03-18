@import exercise.dto.users.UsersPage

@param UsersPage page
@param gg.jte.Content content

@template.layout.page(

content = @`
    <h2>Welcome, friends</h2>
    <div>Here is a list of users</div>
    @for(var user : page.getUsers())
        <div>
            <h3>${user.getFirstName() + user.getLastName()}</h3>
            <p>${user.getEmail()}</p>
        </div>
    @endfor
`,

footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)

