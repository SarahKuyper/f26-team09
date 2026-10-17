/* ==========================================================================
   PrestoPaws — Provider (Business) Scripts
   ========================================================================== */

// ==========================================
// 1. Review Logic (RecentReviews.html)
// ==========================================

const REVIEW_REPLIES_KEY = "prestoPawsReplies";

function getReplies() {
  try {
    const saved = JSON.parse(localStorage.getItem(REVIEW_REPLIES_KEY) || "{}");
    return saved && typeof saved === "object" ? saved : {};
  } catch {
    return {};
  }
}

function saveReplies(replies) {
  localStorage.setItem(REVIEW_REPLIES_KEY, JSON.stringify(replies));
}

function reviewKey(review) {
  return `${review.productId}::${review.author}::${review.text}`;
}

function getAllReviews() {
  // Note: This relies on the 'products' array from app.js
  return products.flatMap((product) =>
    product.reviews.map((review) => ({
      productName: product.name,
      productId: product.id,
      productImage: product.image,
      productAlt: product.alt,
      author: review.author,
      text: review.text
    }))
  );
}

function renderRecentReviews() {
  const tbody = document.querySelector("[data-reviews-body]");
  if (!tbody) return; // Exit if not on the RecentReviews page

  const emptyState = document.querySelector("[data-reviews-empty]");
  const reviews = getAllReviews();

  if (!reviews.length) {
    if (emptyState) emptyState.hidden = false;
    return;
  }

  const replies = getReplies();

  tbody.innerHTML = reviews
    .map((review) => {
      const imgSrc = `../SharedPages/Images/${review.productImage}`;
      const key = reviewKey(review);
      const existingReply = replies[key];

      return `
        <tr class="review-row" data-review-key="${encodeURIComponent(key)}">
          <td>
            <a class="review-product" href="../customerPrototype/productPage.html?id=${review.productId}">
              <img src="${imgSrc}" alt="${review.productAlt}">
              <span>${review.productName}</span>
            </a>
          </td>
          <td>${review.author}</td>
          <td>
            <p class="review-text">${review.text}</p>
            ${existingReply ? `<p class="review-reply"><strong>Your reply:</strong> ${existingReply}</p>` : ""}
          </td>
        </tr>
        <tr class="reply-row" hidden>
          <td colspan="3">
            <form class="reply-form">
              <label for="reply-${encodeURIComponent(key)}">Reply to ${review.author}</label>
              <textarea id="reply-${encodeURIComponent(key)}" name="reply" rows="3" placeholder="Write your response…">${existingReply || ""}</textarea>
              <div class="reply-actions">
                <button type="submit" class="button">Post reply</button>
                <button type="button" class="button secondary" data-cancel-reply>Cancel</button>
              </div>
            </form>
          </td>
        </tr>`;
    })
    .join("");

  wireReplyHandlers(tbody);
}

function wireReplyHandlers(tbody) {
  tbody.querySelectorAll(".review-row").forEach((row) => {
    row.addEventListener("click", (event) => {
      if (event.target.closest("a")) return; // Let product links work

      const replyRow = row.nextElementSibling;
      if (!replyRow) return;
      const isOpen = !replyRow.hidden;

      // Close any other open reply forms first
      tbody.querySelectorAll(".reply-row").forEach((other) => {
        other.hidden = true;
      });

      replyRow.hidden = isOpen;
      if (!isOpen) {
        const textarea = replyRow.querySelector("textarea");
        if (textarea) textarea.focus();
      }
    });
  });

  tbody.querySelectorAll(".reply-form").forEach((form) => {
    form.addEventListener("submit", (event) => {
      event.preventDefault();
      const row = form.closest(".reply-row");
      const reviewRow = row.previousElementSibling;
      const key = decodeURIComponent(reviewRow.dataset.reviewKey);
      const textarea = form.querySelector("textarea");
      const value = textarea.value.trim();
      if (!value) return;

      const replies = getReplies();
      replies[key] = value;
      saveReplies(replies);
      renderRecentReviews(); // Re-render to show the new reply
    });
  });

  tbody.querySelectorAll("[data-cancel-reply]").forEach((button) => {
    button.addEventListener("click", () => {
      const row = button.closest(".reply-row");
      row.hidden = true;
    });
  });
}

function renderReviewTotals() {
  const totalDisplay = document.querySelectorAll("[data-review-total]");
  if (!totalDisplay.length) return; // Exit if no total display on page

  const total = products.reduce((sum, product) => sum + product.reviewCount, 0);
  totalDisplay.forEach((el) => {
    el.textContent = `${total} reviews across ${products.length} products`;
  });
}

// ==========================================
// 2. Publish Item Form Logic (PublishItem.html)
// ==========================================

function setupPublishForm() {
  const form = document.getElementById("publishForm");
  if (!form) return; // Exit if not on PublishItem page

  form.addEventListener("submit", async (e) => {
    e.preventDefault(); // Stop page reload

    // Gather data from form inputs
    const newItem = {
      name: document.getElementById("Item_name").value,
      price: parseFloat(document.getElementById("price").value),
      category: document.getElementById("category").value,
      description: document.getElementById("desc").value,
      stockQuantity: parseInt(document.getElementById("stock").value) || 0,
      isAvailable: true,
      imageUrl: document.getElementById("imageUrl").value || "../SharedPages/Images/NoImage.jpg", // Default placeholder if empty
      providerId: 1      // Hardcoded to provider account for testing
    };

    try {
      // Send POST request to Spring Boot API
      const response = await fetch("http://localhost:8080/api/items", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newItem)
      });

      if (response.ok) {
        alert("Item published successfully!");
        window.location.href = "BsnAccountPage.html"; // Redirect back to account
      } else {
        alert("Failed to publish item. Check console for details.");
        console.error(await response.text());
      }
    } catch (error) {
      console.error("Fetch Error:", error);
      alert("Cannot reach server. Make sure your Spring Boot backend is running!");
    }
  });
}

// ==========================================
// 3. Manage Inventory Logic (ManageInventory.html)
// ==========================================

async function loadInventory() {
  const tbody = document.getElementById("inventoryBody");
  if (!tbody) return;

  try {
    const response = await fetch("http://localhost:8080/api/items");
    const items = await response.json();

    tbody.innerHTML = "";

    if (items.length === 0) {
      tbody.innerHTML = `<tr><td colspan="4" style="text-align:center;">No items found. Publish some!</td></tr>`;
      return;
    }

    items.forEach(item => {
      // 1. Check if the item has an imageUrl. If not, use the default NoImage.
      const imageSrc = item.imageUrl ? item.imageUrl : "../SharedPages/Images/NoImage.jpg";

      // 2. Render the row using the dynamic imageSrc
      const row = `
        <tr>
          <td><img src="${imageSrc}" alt="${item.name} Image" style="width: 60px; height: 60px; object-fit: cover; border-radius: 8px;" onerror="this.src='../SharedPages/Images/NoImage.jpg'"></td>
          <td>${item.name}</td>
          <td>$${item.price.toFixed(2)}</td>
          <td>${item.stockQuantity}</td>
        </tr>
      `;
      tbody.innerHTML += row;
    });

  } catch (error) {
    console.error("Error loading inventory:", error);
    tbody.innerHTML = `<tr><td colspan="4" style="text-align:center; color:red;">Failed to connect to server. Is Spring Boot running?</td></tr>`;
  }
} // <-- This closes loadInventory

// ==========================================
// 4. Initialize Everything on Page Load
// ==========================================

document.addEventListener("DOMContentLoaded", () => {
  renderRecentReviews();
  renderReviewTotals();
  setupPublishForm();
  loadInventory();
});


// ==========================================
// 4. Initialize Everything on Page Load
// ==========================================

document.addEventListener("DOMContentLoaded", () => {
  // Note: These functions will automatically exit early 
  // if their specific HTML elements are not on the current page.

  renderRecentReviews();
  renderReviewTotals();
  setupPublishForm();
  loadInventory();
});