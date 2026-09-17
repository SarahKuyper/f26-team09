
# Requirements – Starter Template

**Project Name:** PrestoPaws
**Team: 9** Sarah Kuyper (Provider) and Colin Todd (Customer)
**Course:** CSC 340-01\
**Version:** 1.0\
**Date:** 2026-09-25

---

## 1. Overview
**Vision.** This app is for cat owners. The core problem we're trying to solve is "Owners want to find quality items for their cats". The outcome we're hoping for is to create a website for cat owners to buy cat-focused items from pet businesses.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
**Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — Overall goal is to be able to make an account and sell items on the website.

**Scope (this semester) - Provider only**
- User profiles (for Providers)
- Ability to see item ratings and reviews
- Selling items

**Out of scope (deferred) - Provider only**
- Provider verification
- Item subscription

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US-1 - Search and filter**

  _Story:_ As a customer, I want to search and filter products by category, brand, and price range so that I can find relevant supplies.

  _Acceptance:_
  ```gherkin
  Scenario: Filter products by category, brand, and price range
    Given the customer is on the product search page
    When they apply filters for category, brand, and a specific price range
    Then the search results update to display only products matching all selected criteria
    And the applied filter tags are visibly displayed on the page
  ```

- **US-2 - View product details**

  _Story:_ As a customer, I want to view detailed product descriptions, ingredients/size info, reviews and ratings to make informed purchases.

  _Acceptance:_
  ```gherkin
  Scenario: Viewing comprehensive product details
    Given the customer is on a product details page
    When the page finishes loading
    Then the customer should see the detailed product description
    And the ingredients and size information should be visible
    And the customer rating and user reviews should be displayed
  ```

- **US-3 - Add items to cart, complete checkout**

  _Story:_ As a customer, I want to add items to a shopping cart and complete a secure checkout

  _Acceptance:_
  ```gherkin
  Scenario: Add an item to the shopping cart
    Given the customer is viewing a product page
    When they select a quantity and click "Add to Cart"
    Then the item should be added to their shopping cart
    And the cart icon should update to show the correct total item count
  Scenario: Complete a secure checkout
    Given the customer has at least one item in their shopping cart
    When they proceed to checkout, enter valid shipping and payment details, and confirm the order
    Then the payment should be processed securely
    And the customer should see an order confirmation screen with a unique order ID
  ```

- **US-4 - Write a review after a purchase**

  _Story:_ As a customer, I want to write a review so that others can make informed purchases.

  _Acceptance:_
  ```gherkin
  Scenario: Write a review after a purchase
    Given I have purchased and recieved an item
    When I submit a review for that item
    Then the review should be saved and visible to other customers
  ```

### 2.2 Provider Stories
- **US-20 — <Provider Goal>**  
  _Story:_ As a provider, I want to sell cat-related items so that (new) cat owners can properly take care of their cat(s).  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US-21 — <short title>**  
  _Story:_ As a provider, I want to be able to make an account so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```
---

## 3. Non-Functional Requirements
- **Performance:** Product search results and product detail pages should load within a few seconds under normal usage. Applying a search filter should update results within a few seconds.
- **Availability/Reliability:** The application should be available at least 99% of the time during testing. Cart contents should not be lost when a customer refreshes the page or navigates between pages.
- **Security/Privacy:** 
- **Usability:** A customer should be able to search for a product, add it to the cart, and begin checkout. The application should work in modern web browsers.

---

## 4. Assumptions, Constraints, and Policies
- **Assumptions**
- Customers have internet access and use a modern web browser.
- Providers are responsible for accurately describing products, prices, ingredients, sizes, and availability.
- Product reviews are submitted by customers who purchased the item.
- **Constraints**
- The project must be completed within the milestones listed.
- **Policies**
- Users must provide accurate account information.
- Providers may only list legal, cat-related products and must not post misleading information.
- Reviews must be relevant to the purchased product and must not contain abusive, offensive, or personal information.
---

## 5. Milestones (course‑aligned)
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
