@import exercise.dto.users.UserPage

@param UserPage page
@param gg.jte.Content content

@template.layout.page(
content = @`
    <h3>Welcome!</h3>
    <div>Here is some text about user ${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</div>
    <div>Here is an user's <a href="${page.getUser().getEmail()}">email</a></div>

`,
footer = @`
    <p>Thanks for visiting, come again soon!</p>
`
)