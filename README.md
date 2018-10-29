# revolutRest


Java RESTful API for money transfers between users accounts

Technologies:

H2 in memory database,

Jetty Container (for Test and Demo app),

Apache HTTP Client

you need:
maven, java 8

build: mvn clean install assembly:assembly

start server:

java -jar server.jar

Services

| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /account?accountID={accountID} | get account by id | 
| POST | /bank?fromAccount={accountID}&toAccount={accountID}&currency={currencyCode}&amount={amount}|  transaction between 2 user accounts | 

    