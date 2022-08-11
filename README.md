# Exchange Rate Challenge
---
## Features
Expose below API to fetch the currency exchange details and the rates

1. GET: Get exchange rate from Currency A to Currency B 
2. GET: Get all exchange rates from Currency A
3. POST: Get value conversion from Currency A to Currency B
4. POST: Get value conversion from Currency A to a list of supplied currencies

Fetch data using publicly available API https://exchangerate.host <br/>
Caching the output of API call and evicting it after a minute (Alternate solution: Store data in H2 database and refresh after every 1 minute)

## Assumptions
1. Data upto 2 minute old is permissible
2. Exchange rate for 2 currencies is not inverse to each other (Exchange rate between INR->USD is not equal to 1/(USD->INR) ). In case that is true, reverse key can be formed and checked in database for existance.
3. Rounding of value is not done as no information provided

## How to run the project
1. Import the project in eclipse or any other IDE
2. Open file betvictor.project.shivani.shah.exchangeratechallenge.ExchangeRateChallengeApplication and run the code as Java application.
3. Access API documentation using link : http://localhost:3000/swagger-ui
4. All test cases are kept under betvictor.project.shivani.shah.ExchangeRateChallenge package
mvn '-Dtest=betvictor.project.shivani.shah.ExchangeRateChallenge.*' test

## Alternate Solutions
Data can be stored in cache and evicted as required

### Author
Shivani Shah (email: shivani3693@gmail.com)

### Credits
All data is fetched from API hosted at https://exchangerate.host