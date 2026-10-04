/* ==========================================================================
   Business page scripts — PrestoPaws
   Depends on `products` defined in app.js. Load app.js first.
   ========================================================================== */

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
  if (!tbody) return;

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
      // Let the product link behave normally.
      if (event.target.closest("a")) return;

      const replyRow = row.nextElementSibling;
      if (!replyRow) return;
      const isOpen = !replyRow.hidden;

      // Close any other open reply forms first.
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
      renderRecentReviews();
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
  const total = products.reduce((sum, product) => sum + product.reviewCount, 0);
  document.querySelectorAll("[data-review-total]").forEach((el) => {
    el.textContent = `${total} reviews across ${products.length} products`;
  });
}

document.addEventListener("DOMContentLoaded", () => {
  renderRecentReviews();
  renderReviewTotals();
});
