<!DOCTYPE html>
<html>
<head>
    <title>New Item - PrestoPaws</title>
</head>
<body>
    <h1>PrestoPaws</h1>
    <h2>Create a new item</h2>

    <form action="/providers/${providerId}/items" method="post">

        <div>
            <label>Name:</label><br>
            <input type="text" name="name" value="${item.name!}" />
            <#if errors?? && errors.hasFieldErrors("name")>
                <p style="color: red;">${errors.getFieldError("name").defaultMessage}</p>
            </#if>
        </div>

        <div>
            <label>Description:</label><br>
            <textarea name="description">${item.description!}</textarea>
        </div>

        <div>
            <label>Price:</label><br>
            <input type="number" step="0.01" name="price" value="${(item.price!)?c}" />
            <#if errors?? && errors.hasFieldErrors("price")>
                <p style="color: red;">${errors.getFieldError("price").defaultMessage}</p>
            </#if>
        </div>

        <div>
            <label>Stock quantity:</label><br>
            <input type="number" name="stockQuantity" value="${(item.stockQuantity!)?c}" />
        </div>

        <div>
            <label>Category:</label><br>
            <input type="text" name="category" value="${item.category!}" />
        </div>

        <div>
            <label>Image URL:</label><br>
            <input type="text" name="imageUrl" value="${item.imageUrl!}" />
        </div>

        <div>
            <label>
                <input type="checkbox" name="isAvailable" value="true"
                       <#if item.isAvailable!false>checked</#if> />
                Available
            </label>
        </div>

        <button type="submit">Create item</button>
    </form>

    <p><a href="/providers/${providerId}/items">Back to items</a></p>
</body>
</html>