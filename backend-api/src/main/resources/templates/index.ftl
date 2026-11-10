<!DOCTYPE html>
<html>
<head>
    <title>PrestoPaws</title>
    <link rel="stylesheet" href="/css/Styling.css">
</head>
<body>
    <header>
        <a class="brand" href="/">
            <img src="/Images/logo.jpg" alt="PrestoPaws Logo">
            PrestoPaws
        </a>
        <nav>
            <a href="/providers/new">Create Provider</a>
        </nav>
    </header>

    <main>
        <h1>Welcome to PrestoPaws</h1>
        <p style="margin-bottom: 2rem;">Connecting cat owners with quality products.</p>

        <h2>Provider Portal</h2>
        <ul>
            <li><a href="/providers/new">Create a new provider profile</a></li>
        </ul>

        <#if providers?? && providers?size gt 0>
            <h3>Existing Providers</h3>
            <ul>
                <#list providers as p>
                    <li>
                        <a href="/providers/${p.id}">${p.name}</a> 
                        — <a href="/providers/${p.id}/items">View Items</a>
                    </li>
                </#list>
            </ul>
        <#else>
            <p>No providers registered yet.</p>
        </#if>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>