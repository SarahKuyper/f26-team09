<!DOCTYPE html>
<html>
<head>
    <title>${provider.name} - Items - PrestoPaws</title>
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
        <h2>Items for ${provider.name}</h2>
        
        <p>
            <a href="/providers/${provider.id}">&larr; Back to profile</a> | 
            <a href="/providers/${provider.id}/items/new">+ Publish new item</a>
        </p>

        <#if items?? && items?size gt 0>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Available</th>
                    </tr>
                </thead>
                <tbody>
                    <#list items as item>
                        <tr>
                            <td>${item.name}</td>
                            <td>${item.category!""}</td>
                            <td>$${item.price!"0.00"}</td>
                            <td>${item.stockQuantity!"0"}</td>
                            <td>${(item.isAvailable!false)?then("Yes", "No")}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        <#else>
            <p>No items for this provider yet.</p>
        </#if>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>