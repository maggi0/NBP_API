# NBP_API

Spring Boot service that uses NBP API in order to expose following endpoints:

GET /api/exchange-rates/{currencyCode} - returns currency exchange rate PLN to {currencyCode} for the last 5 business days.
GET /api/gold-price/average - returns average gold price for the last 14 business days.
