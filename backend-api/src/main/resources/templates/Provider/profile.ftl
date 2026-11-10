<!DOCTYPE html>
<html>
<head>
    <title>${provider.name} - PrestoPaws</title>
    <link rel="stylesheet" href="/css/Styling.css">
</head>
<body>

    <header>
        <a class="brand" href="/">
            <img src="/Images/logo.jpg" alt="PrestoPaws Logo">
            PrestoPaws
        </a>
    </header>

    <main>
        <div class="account-header">
            <img src="/Images/BlankPFP.jpg" alt="Profile" class="avatar" onerror="this.style.display='none'">
            <div class="account-header-text">
                <h2>${provider.name}</h2>
                <p>Email: ${provider.email} | ID: ${provider.id}</p>
            </div>
        </div>

        <p>
            <a href="/providers/${provider.id}/edit" class="button" style="width: auto; display: inline-block; margin-right: 10px;">Edit profile</a>
            <a href="/providers/${provider.id}/items" class="button" style="width: auto; display: inline-block;">View my items</a>
        </p>

        <br>
        <form action="/providers/${provider.id}/delete" method="post"
              onsubmit="return confirm('Delete this provider profile?');">
            <button type="submit" class="button" style="width: auto; background-color: #e67d63; color: white;">Delete profile</button>
        </form>

        <p style="margin-top: 2rem;"><a href="/">Back to home</a></p>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>