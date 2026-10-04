function getAllReviews() {
    return products.flatMap((product) =>
        product.reviews.map((review) => ({
            productName: product.name,
            productId: product.id,
            rating: product.rating,
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

  tbody.innerHTML = reviews
    .map(
      (review) => `
      <tr>
        <td>
          <a class="review-product" href="productPage.html?id=${review.productId}">
            <img src="${review.productImage}" alt="${review.productAlt}">
            <span>${review.productName}</span>
          </a>
        </td>
        <td>${review.author}</td>
        <td><span class="checked">${"★".repeat(Math.round(review.rating))}${"☆".repeat(5 - Math.round(review.rating))}</span></td>
        <td>${review.text}</td>
      </tr>`
    )
    .join("");
}

function renderReviewTotals() {
    const total = products.reduce((sum, product) => sum + product.reviewCount, 0);
    document.querySelectorAll("[data-review-total]").forEach((el) => {
        el.textContent = `${total} reviews across ${products.length} products`;
    });
}

document.addEventListener("DOMContentLoaded", () => {
    updateCartCount();
    renderHome();
    renderProductDetail();
    renderCart();
    renderCheckout();
    renderAccountForms();
    renderRecentReviews();   // ← add this
    renderReviewTotals();    // ← add this
});

reviews: [
  { author: "Lloyd I.", text: "...", date: "2026-09-28" },
  { author: "Asbel L.", text: "...", date: "2026-09-25" }
]

function getAllReviews() {
  return products
    .flatMap((product) =>
      product.reviews.map((review) => ({
        productName: product.name,
        productId: product.id,
        rating: product.rating,
        author: review.author,
        text: review.text,
        date: review.date
      }))
    )
    .sort((a, b) => new Date(b.date) - new Date(a.date));
}

function getAllReviews() {
  return products.flatMap((product) =>
    product.reviews.map((review) => ({
      productName: product.name,
      productId: product.id,
      productImage: product.image,
      productAlt: product.alt,
      rating: product.rating,
      author: review.author,
      text: review.text
    }))
  );
}

