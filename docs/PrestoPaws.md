
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
- **Buyer/Customer:** The people who visit our website in order to buy item(s).
- **Seller/Provider:** The suppliers who use our website to sell their item(s).
- **Profile:** A collection of information about a user (for both customers and providers). This profile would include personal details (name, addresses, and payment type for customers), average shop ratings (for providers only), and a page for submitting new items to the shop (for providers only).
- **Item(s)/Inventory:** The specific item(s) that are sold by a supplier.

**Primary Users / Roles.**
**Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — Overall goal is to be able to make an account and sell items on the website.

**Scope (this semester) - Provider only**
- User profiles (for Providers)
- Ability to see and respond to item reviews
- Selling items

**Out of scope (deferred) - Provider only**
- Provider verification
- Item subscription

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
### 2.2 Provider Stories
- **US-20 — <Register and manage business profile>**  
  _Story:_ As a provider, I want to make a business profile, so that I can sell my item(s)/inventory.  
  _Acceptance:_
  ```gherkin
  Scenario: <Successfully register a business profile>
    Given <I am not registered>
    When  <I submit my information and create a business acount>
    Then  <Customers can view my account>
    And   <I can sell my inventory>

- **US-21 — <Respond to reviews>**  
  _Story:_ As a provider, I want to be able to make an account so that I can respond to reviews and respond accordingly  
  _Acceptance:_
  ```gherkin
  Scenario: <Respond to reviews>
    Given <I am registered in a business account>
    When  <I log in>
    Then  <I should be able to see 'recent reviews' and respond to them>
  ```
---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** 95% of searches (via search bar) should be returned in less than 5 seconds after typical load
- **Availability/Reliability:** System should be available 97% of the time, with planned maintenance being communicated in advance
- **Security/Privacy:** Customers should not be able to respond to reviews, businesses should not be able to respond to reviews left on competitor reviews. All sensitive data should be encrypted in transit and at rest.
- **Usability:** New customers should be able to create an account within 5 minutes without external assistance. New businesses should be able to create and account within 15 to 20 minutes without external assistance.

---

## 4. Assumptions, Constraints, and Policies
- Modern browsers (latest Chrome/Firefox/Edge/Safari) and stable connectivity.
- Course timeline and campus infrastructurer constraints apply.

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
