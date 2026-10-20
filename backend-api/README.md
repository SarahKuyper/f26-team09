# PrestoPaws Backend API

## UML Diagram

![Class Diagram](/docs/team09 UML design.png)

## Endpoints

Customer (Colin Todd)
| Method | Path | Description | Success |
|--------|------|-------------|---------|
| `POST` | `/api/customers` | Create a buyer | 201 |
| `GET` | `/api/customers` | List buyers | 200 |
| `GET` | `/api/customers/{id}` | Get a buyer | 200 |
| `POST` | `/api/customers/orders` | Create an order | 201 |
| `GET` | `/api/customers/orders` | List orders | 200 |
| `GET` | `/api/customers/orders/{id}` | Get an order | 200 |
| `GET` | `/api/customers/orders/buyer/{buyerId}` | List a buyer’s orders | 200 |
| `POST` | `/api/customers/order-items/order/{orderId}` | Add an item to an order | 201 |
| `GET` | `/api/customers/order-items` | List order items | 200 |
| `GET` | `/api/customers/order-items/{id}` | Get an order item | 200 |
| `GET` | `/api/customers/order-items/order/{orderId}` | List items in an order | 200 |
| `POST` | `/api/customers/reviews` | Create a review | 201 |

#### `POST /api/customers` example
```json
{
"name": "Alex Buyer",
"email": "alex@example.com",
"password": "example-password"
}
```

#### `POST /api/customers/orders` example
```json
{
"buyerId": 12,
"shippingPrice": 5.00,
"orderItems": [
{ "itemId": 4, "quantity": 2 },
{ "itemId": 9, "quantity": 1 }
]
}
```

#### `POST /api/customers/order-items/order/{orderId}` example
```json
{
"itemId": 4,
"quantity": 2
}
```

#### `POST /api/customers/reviews` example
```json
{
"buyerId": 12,
"itemId": 4,
"rating": 5,
"comment": "Exactly what I was looking for."
}
```


Provider (Sarah Kuyper)
| Method | Path | Description | Success |
|--------|------|-------------|---------|
| `GET` | `/api/providers/reviews` | List reviews | 200 |
| `GET` | `/api/providers/reviews/{id}` | Get a review | 200 |
| `GET` | `/api/providers/reviews/item/{itemId}` | List reviews for an item | 200 |
| `GET` | `/api/providers/reviews/provider/{providerId}` | List reviews for a provider | 200 |
| `POST` | `/api/items` | Creates an item | 201 |
| `GET` | `/api/items` | Returns all items | 200 |
| `POST` | `/api/providers` | Creates a provider | 201 |
| `GET` | `/api/providers/{id}` | Returns the provider | 200 |

#### `POST /api/items` example
```json
{
"name": "Paw Balm",
"description": "Moisturizing balm for paws",
"price": 12.99,
"stockQuantity": 25,
"category": "Grooming",
"isAvailable": true,
"imageUrl": "https://example.com/paw-balm.jpg",
"providerId": 3
}
```
#### `POST /api/providers` example
```json
{
"name": "Example Provider",
"email": "provider@example.com",
"password": "example-password"
}
```

There are currently no update or delete endpoints.
