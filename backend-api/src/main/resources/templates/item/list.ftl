<!DOCTYPE html>
<html>
<head>
    <title>${provider.name} - Items - PrestoPaws</title>
</head>
<body>
    <h1>PrestoPaws</h1>
    <h2>Items for ${provider.name}</h2>

    <p>
        <a href="/providers/${provider.id}">Back to profile</a> |
        <a href="/providers/${provider.id}/items/new">Create new item</a>
    </p>

    <#if items?? && items?size gt 0>
        <table border="1" cellpadding="6">
            <tr>
                <th>ID</th><th>Name</th><th>Category</th>
                <th>Price</th><th>Stock</th><th>Available</th>
            </tr>
            <#list items as item>
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.category!""}</td>
                    <td>${item.price!"—"}</td>
                    <td>${item.stockQuantity!"—"}</td>
                    <td>${(item.isAvailable!false)?then("Yes", "No")}</td>
                </tr>
            </#list>
        </table>
    <#else>
        <p>No items for this provider yet.</p>
    </#if>

    <p><a href="/">Back to home</a></p>
</body>
</html>