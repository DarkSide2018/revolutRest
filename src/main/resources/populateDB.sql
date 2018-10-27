DROP TABLE IF EXISTS User;

CREATE TABLE User (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
 name VARCHAR(30) NOT NULL);

CREATE UNIQUE INDEX idx_ue on User(name);

INSERT INTO User (name) VALUES ('test123222222');
INSERT INTO User (name) VALUES ('test12411111');
INSERT INTO User (name) VALUES ('test1290');

DROP TABLE IF EXISTS Score;

CREATE TABLE Score (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
userID BIGINT,
balance DECIMAL(19,4),
currencyCode VARCHAR(30)
);

INSERT INTO Score (userId,balance,currencyCode) VALUES (1,100.0000,'USD');
INSERT INTO Score (userId,balance,currencyCode) VALUES (2,200.0000,'USD');
INSERT INTO Score (userId,balance,currencyCode) VALUES (1,500.0000,'EUR');
INSERT INTO Score (userId,balance,currencyCode) VALUES (2,600.0000,'EUR');
INSERT INTO Score (userId,balance,currencyCode) VALUES (1,700.0000,'GBP');
INSERT INTO Score (userId,balance,currencyCode) VALUES (2,800.0000,'GBP');