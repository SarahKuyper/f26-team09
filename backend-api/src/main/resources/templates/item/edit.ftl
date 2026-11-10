<!DOCTYPE html>
<html>
<head>
    <title>Edit Item - PrestoPaws</title>
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
        <h2>Edit Item</h2>

        <!-- Note the action URL now includes the itemId -->
        <form action="/providers/${providerId}/items/${itemId}/edit" method="post">

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
                <select name="category">
                    <option value="Food and Treats" <#if item.category! == "Food and Treats">selected</#if>>Food and Treats</option>
                    <option value="Health and Hygiene" <#if item.category! == "Health and Hygiene">selected</#if>>Health and Hygiene</option>
                    <option value="Toys and Enrichment" <#if item.category! == "Toys and Enrichment">selected</#if>>Toys and Enrichment</option>
                    <option value="Feeding and Watering" <#if item.category! == "Feeding and Watering">selected</#if>>Feeding and Watering</option>
                </select>
            </div>

            <div>
                <label>Image URL:</label><br>
                <input type="text" name="imageUrl" value="${item.imageUrl!}" placeholder="e.g. /Images/item1.webp" />
            </div>

            <div>
                <label>
                    <input type="checkbox" name="isAvailable" value="true"
                           <#if item.isAvailable!false>checked</#if> />
                    Available
                </label>
            </div>

            <button type="submit" class="button">Save Changes</button>
        </form>

        <p style="margin-top: 20px;"><a href="/providers/${providerId}/items">Cancel</a></p>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>