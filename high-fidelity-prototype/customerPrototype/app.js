const products = [
  {
    id: "anti-parasite",
    name: "Anti-Parasite",
    category: "Care",
    price: 24,
    image: "item1.webp",
    alt: "Anti-parasite cat care treatment",
    description: "A gentle monthly treatment that helps protect indoor cats from common parasites while keeping their care routine simple.",
    details: ["3 monthly treatments", "For cats 8 weeks and older", "Easy topical application"],
    rating: 4.8,
    reviewCount: 18,
    reviews: [
      { author: "Lloyd I.", text: "I accidentally ate it. I think I'm alright though." },
      { author: "Asbel L.", text: "A simple addition to our monthly care routine." }
    ]
  },
  {
    id: "indoor-kibble",
    name: "Indoor Kibble",
    category: "Food",
    price: 38,
    image: "item2.webp",
    alt: "Indoor kibble for cats",
    description: "Balanced dry food made for indoor cats, with satisfying crunch and everyday nutrition for healthy, happy living.",
    details: ["5 lb bag", "Chicken and brown rice recipe", "Complete adult cat nutrition"],
    rating: 4.6,
    reviewCount: 27,
    reviews: [
      { author: "Chris R.", text: "Both of my cats come running when they hear the bag." },
      { author: "Leon K.", text: "Good ingredients and a size that lasts us a while." }
    ]
  },
  {
    id: "owl-wand",
    name: "Owl Wand",
    category: "Toys",
    price: 10,
    image: "item3.webp",
    alt: "Owl wand cat toy",
    description: "A feathered owl toy on a flexible wand for interactive play, pounces, and plenty of satisfying zoomies.",
    details: ["24 inch wand", "Soft feather and felt details", "Interactive play toy"],
    rating: 4.9,
    reviewCount: 31,
    reviews: [
      { author: "Milla M.", text: "The owl is an instant favorite in our house." },
      { author: "Jude M.", text: "Great for a quick play session before bedtime." }
    ]
  },
  {
    id: "steel-bowl",
    name: "Steel Bowl",
    category: "Feeders",
    price: 16,
    image: "item4.webp",
    alt: "Stainless steel cat bowl",
    description: "A sturdy stainless steel bowl with a clean silhouette and a stable base for everyday meals and fresh water.",
    details: ["16 oz capacity", "Food-safe stainless steel", "Non-skid base"],
    rating: 4.7,
    reviewCount: 22,
    reviews: [
      { author: "Squall L.", text: "Easy to clean and stays put on the kitchen floor." },
      { author: "Bartz K.", text: "Simple, sturdy, and exactly what we needed." }
    ]
  }
];

const CART_KEY = "prest paws-cart".replace(" ", "");

function formatPrice(value) {
  return `$${value.toFixed(2)}`;
}

function getProduct(productId) {
  return products.find((product) => product.id === productId);
}

function getCart() {
  try {
    const savedCart = JSON.parse(localStorage.getItem(CART_KEY) || "[]");
    return Array.isArray(savedCart) ? savedCart : [];
  } catch (error) {
    return [];
  }
}

function saveCart(cart) {
  localStorage.setItem(CART_KEY, JSON.stringify(cart.filter((item) => item.quantity > 0)));
  updateCartCount();
}

function getCartItems() {
  return getCart()
    .map((item) => ({ ...item, product: getProduct(item.id) }))
    .filter((item) => item.product);
}

function getCartCount() {
  return getCart().reduce((total, item) => total + item.quantity, 0);
}

function getCartTotal() {
  return getCartItems().reduce((total, item) => total + item.product.price * item.quantity, 0);
}

function addToCart(productId, quantity = 1) {
  const product = getProduct(productId);
  if (!product) return;

  const cart = getCart();
  const existingItem = cart.find((item) => item.id === productId);
  if (existingItem) {
    existingItem.quantity += quantity;
  } else {
    cart.push({ id: productId, quantity });
  }
  saveCart(cart);
}

function updateCartItem(productId, quantity) {
  const cart = getCart();
  const item = cart.find((cartItem) => cartItem.id === productId);
  if (item) item.quantity = Math.max(0, quantity);
  saveCart(cart);
}

function removeFromCart(productId) {
  saveCart(getCart().filter((item) => item.id !== productId));
}

function clearCart() {
  localStorage.removeItem(CART_KEY);
  updateCartCount();
}

function updateCartCount() {
  document.querySelectorAll("[data-cart-count]").forEach((element) => {
    element.textContent = getCartCount();
  });
}

function productCard(product) {
  return `
    <article class="product">
      <a class="product-link" href="productPage.html?id=${product.id}">
        <img class="product-image" src="${product.image}" alt="${product.alt}">
        <div class="product-info">
          <p class="category">${product.category}</p>
          <h3>${product.name}</h3>
          <p class="price">${formatPrice(product.price)}</p>
          <span class="text-link">View details</span>
        </div>
      </a>
    </article>`;
}

function renderHome() {
  const productGrid = document.querySelector("[data-products]");
  if (!productGrid) return;

  const searchInput = document.querySelector("[data-search]");
  const resultCount = document.querySelector("[data-result-count]");
  const emptyState = document.querySelector("[data-empty-state]");
  const params = new URLSearchParams(window.location.search);
  const selectedCategory = params.get("category") || "";
  const initialSearch = params.get("search") || "";

  if (searchInput) searchInput.value = initialSearch;
  document.querySelectorAll("[data-category]").forEach((link) => {
    link.classList.toggle("active", link.dataset.category === selectedCategory);
  });

  function renderResults() {
    const searchTerm = searchInput ? searchInput.value.trim().toLowerCase() : "";
    const matchingProducts = products.filter((product) => {
      const matchesCategory = !selectedCategory || product.category.toLowerCase() === selectedCategory.toLowerCase();
      const matchesSearch = !searchTerm || `${product.name} ${product.category}`.toLowerCase().includes(searchTerm);
      return matchesCategory && matchesSearch;
    });

    productGrid.innerHTML = matchingProducts.map(productCard).join("");
    if (resultCount) resultCount.textContent = `${matchingProducts.length} ${matchingProducts.length === 1 ? "item" : "items"}`;
    if (emptyState) emptyState.hidden = matchingProducts.length > 0;
  }

  if (searchInput) searchInput.addEventListener("input", renderResults);
  renderResults();
}

function renderProductDetail() {
  const detail = document.querySelector("[data-product-detail]");
  if (!detail) return;

  const product = getProduct(new URLSearchParams(window.location.search).get("id"));
  if (!product) {
    detail.innerHTML = `<div class="notice"><h1>Product not found</h1><p>That item is not part of the current catalog.</p><a class="button" href="homePage.html">Return to shop</a></div>`;
    return;
  }

  detail.innerHTML = `
    <div class="detail-image-wrap"><img class="detail-image" src="${product.image}" alt="${product.alt}"></div>
    <div class="detail-copy">
      <p class="category">${product.category}</p>
      <h1>${product.name}</h1>
      <p class="rating" aria-label="${product.rating} out of 5 stars">★★★★★ <span>${product.rating} (${product.reviewCount} reviews)</span></p>
      <p class="detail-price">${formatPrice(product.price)}</p>
      <p class="description">${product.description}</p>
      <ul class="details-list">${product.details.map((item) => `<li>${item}</li>`).join("")}</ul>
      <div class="purchase-row">
        <label for="quantity">Quantity</label>
        <input id="quantity" class="quantity-input" type="number" min="1" max="20" value="1">
        <button class="button" type="button" data-add-to-cart>Add to cart</button>
      </div>
      <p class="success-message" data-add-message role="status" hidden>Added to your cart.</p>
      <a class="secondary-link" href="cartPage.html">View cart (<span data-cart-count>0</span>)</a>
    </div>
    <section class="reviews" aria-labelledby="reviews-title">
      <h2 id="reviews-title">Customer reviews</h2>
      ${product.reviews.map((review) => `<blockquote><p>“${review.text}”</p><cite>${review.author}</cite></blockquote>`).join("")}
      <form class="review-form" data-review-form>
        <h3>Leave a review</h3>
        <label for="review-text">Your review</label>
        <textarea id="review-text" name="review" rows="4" placeholder="Tell us what you think" required></textarea>
        <button class="button" type="submit">Leave review</button>
      </form>
    </section>`;

  detail.querySelector("[data-add-to-cart]").addEventListener("click", () => {
    const quantity = Math.min(20, Math.max(1, Number(detail.querySelector("#quantity").value) || 1));
    addToCart(product.id, quantity);
    detail.querySelector("[data-add-message]").hidden = false;
  });
  updateCartCount();
}

function renderCart() {
  const container = document.querySelector("[data-cart-page]");
  if (!container) return;
  const items = getCartItems();
  const itemList = container.querySelector("[data-cart-items]");
  const emptyState = container.querySelector("[data-cart-empty]");
  const content = container.querySelector("[data-cart-content]");

  if (!items.length) {
    emptyState.hidden = false;
    content.hidden = true;
    return;
  }

  emptyState.hidden = true;
  content.hidden = false;
  itemList.innerHTML = items.map(({ product, quantity }) => `
    <article class="cart-item">
      <img src="${product.image}" alt="${product.alt}">
      <div class="cart-item-info"><p class="category">${product.category}</p><h2>${product.name}</h2><p>${formatPrice(product.price)} each</p></div>
      <label class="cart-quantity">Qty <input type="number" min="1" max="20" value="${quantity}" data-quantity="${product.id}"></label>
      <strong>${formatPrice(product.price * quantity)}</strong>
      <button class="remove-button" type="button" data-remove="${product.id}">Remove</button>
    </article>`).join("");
  container.querySelector("[data-subtotal]").textContent = formatPrice(getCartTotal());
  container.querySelector("[data-total]").textContent = formatPrice(getCartTotal());

  itemList.querySelectorAll("[data-quantity]").forEach((input) => {
    input.addEventListener("change", () => {
      updateCartItem(input.dataset.quantity, Math.min(20, Math.max(1, Number(input.value) || 1)));
      renderCart();
    });
  });
  itemList.querySelectorAll("[data-remove]").forEach((button) => {
    button.addEventListener("click", () => {
      removeFromCart(button.dataset.remove);
      renderCart();
    });
  });
}

function renderCheckout() {
  const page = document.querySelector("[data-checkout-page]");
  if (!page) return;
  const items = getCartItems();
  const form = page.querySelector("[data-checkout-form]");
  const summary = page.querySelector("[data-checkout-summary]");
  const emptyState = page.querySelector("[data-checkout-empty]");
  const confirmation = page.querySelector("[data-confirmation]");

  if (!items.length) {
    form.hidden = true;
    summary.hidden = true;
    emptyState.hidden = false;
    return;
  }

  summary.innerHTML = `${items.map(({ product, quantity }) => `<div><span>${product.name} × ${quantity}</span><strong>${formatPrice(product.price * quantity)}</strong></div>`).join("")}<div class="summary-total"><span>Total</span><strong>${formatPrice(getCartTotal())}</strong></div>`;
  form.addEventListener("submit", (event) => {
    event.preventDefault();
    if (!form.reportValidity()) return;
    const orderId = `PP-${Date.now().toString().slice(-8)}`;
    clearCart();
    form.hidden = true;
    summary.hidden = true;
    confirmation.hidden = false;
    confirmation.querySelector("[data-order-id]").textContent = orderId;
  });
}

function renderAccountForms() {
  document.querySelectorAll("[data-account-form]").forEach((form) => {
    form.addEventListener("submit", (event) => {
      event.preventDefault();
      if (form.reportValidity()) window.location.href = form.action;
    });
  });
}

document.addEventListener("DOMContentLoaded", () => {
  updateCartCount();
  renderHome();
  renderProductDetail();
  renderCart();
  renderCheckout();
  renderAccountForms();
});
