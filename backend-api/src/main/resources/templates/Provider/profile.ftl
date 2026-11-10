<!DOCTYPE html>
<html>
<head>
    <title>${provider.name} - PrestoPaws</title>
</head>
<body>
    <h1>PrestoPaws</h1>
    <h2>${provider.name}</h2>
    <p>Email: ${provider.email}</p>
    <p>Provider ID: ${provider.id}</p>

    <p>
        <a href="/providers/${provider.id}/edit">Edit profile</a> |
        <a href="/providers/${provider.id}/items">View my items</a>
    </p>

    <form action="/providers/${provider.id}/delete" method="post"
          onsubmit="return confirm('Delete this provider profile?');">
        <button type="submit">Delete profile</button>
    </form>

    <p><a href="/">Back to home</a></p>
</body>
</html>