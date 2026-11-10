<!DOCTYPE html>
<html>
<head>
    <title>New Item - PrestoPaws</title>
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

            <button type="submit" class="button">Create item</button>
        </form>

        <p style="margin-top: 20px;"><a href="/providers/${providerId}/items">Back to items</a></p>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>