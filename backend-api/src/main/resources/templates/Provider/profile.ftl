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
        <!-- Provider Info Header -->
        <div class="account-header">
            <img src="/Images/BlankPFP.jpg" alt="Profile" class="avatar">
            <div class="account-header-text">
                <h2>${provider.name}</h2>
                <p>Email: ${provider.email} | ID: ${provider.id}</p>
            </div>
        </div>

        <!-- Dashboard Cards -->
        <div class="card-row">
            
            <!-- Manage Inventory Card -->
            <div class="card-column">
                <a href="/providers/${provider.id}/items" style="text-decoration: none; color: inherit;">
                    <div class="card">
                        <img src="/Images/ManageInventory.jpg" alt="Manage Inventory" class="product-image" style="width: 100%; aspect-ratio: 1/1;">
                        <div class="product-info" style="text-align: center;">
                            <h3>Manage Inventory</h3>
                        </div>
                    </div>
                </a>
            </div>

            <!-- Publish Item Card -->
            <div class="card-column">
                <a href="/providers/${provider.id}/items/new" style="text-decoration: none; color: inherit;">
                    <div class="card">
                        <img src="/Images/PublishInventory.jpg" alt="Publish Item" class="product-image" style="width: 100%; aspect-ratio: 1/1;">
                        <div class="product-info" style="text-align: center;">
                            <h3>Publish Item</h3>
                        </div>
                    </div>
                </a>
            </div>

            <!-- Recent Reviews Card -->
            <div class="card-column">
                <a href="#" style="text-decoration: none; color: inherit;">
                    <div class="card">
                        <img src="/Images/RecentReviews.jpg" alt="Recent Reviews" class="product-image" style="width: 100%; aspect-ratio: 1/1;">
                        <div class="product-info" style="text-align: center;">
                            <h3>Recent Reviews</h3>
                        </div>
                    </div>
                </a>
            </div>

        </div>

        <!-- Profile Actions -->
        <div style="margin-top: 3rem; padding-top: 1.5rem; border-top: 1px solid var(--line);">
            <a href="/providers/${provider.id}/edit" class="button" style="display: inline-block; width: auto; margin-right: 10px;">Edit Profile</a>
            
            <form action="/providers/${provider.id}/delete" method="post" style="display: inline-block;" onsubmit="return confirm('Delete this provider profile?');">
                <button type="submit" class="button" style="width: auto; background-color: #e67d63; color: white; border: none;">Delete Profile</button>
            </form>
        </div>

        <p style="margin-top: 2rem;"><a href="/">Back to home</a></p>
    </main>

    <footer>
        &copy; 2026 PrestoPaws
    </footer>
</body>
</html>