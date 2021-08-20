-- customer
CREATE TABLE customer (
    id          BIGSERIAL   PRIMARY KEY,
    full_name   TEXT        NOT NULL
);

INSERT INTO customer (full_name) VALUES ('Customer-01');
INSERT INTO customer (full_name) VALUES ('Customer-02');

-- transaction
CREATE TABLE customer_transaction (
    id                      BIGSERIAL   PRIMARY KEY,
    transaction_date        TIMESTAMP   NOT NULL,
    transaction_type        TEXT        NOT NULL,
    customer_id             BIGINT      NOT NULL,
    currency                CHAR(3)     NOT NULL,
    amount                  NUMERIC     NOT NULL,
    counterparty_id         BIGINT,
    counterparty_currency   CHAR(3),
    counterparty_amount     NUMERIC,
    rate_at                 NUMERIC
);

INSERT INTO customer_transaction (transaction_date, transaction_type, customer_id, currency, amount) VALUES (NOW() AT TIME ZONE 'UTC', 'DEPOSIT', 1, 'HKD', '100');
INSERT INTO customer_transaction (transaction_date, transaction_type, customer_id, currency, amount) VALUES (NOW() AT TIME ZONE 'UTC', 'DEPOSIT', 1, 'USD', '100');
INSERT INTO customer_transaction (transaction_date, transaction_type, customer_id, currency, amount) VALUES (NOW() AT TIME ZONE 'UTC', 'DEPOSIT', 2, 'HKD', '100');
INSERT INTO customer_transaction (transaction_date, transaction_type, customer_id, currency, amount) VALUES (NOW() AT TIME ZONE 'UTC', 'DEPOSIT', 2, 'USD', '100');

CREATE TABLE customer_transaction_event (
    id              BIGSERIAL   PRIMARY KEY,
    customer_id     BIGINT      NOT NULL,
    event_type      TEXT        NOT NULL,
    event_version   SMALLINT    NOT NULL,
    event_content   TEXT        NOT NULL,
    create_at       TIMESTAMP   NOT NULL
);

INSERT INTO customer_transaction_event (customer_id, event_type, event_version, event_content, create_at) VALUES (1, 'DEPOSIT', 1, '{"currency":"HKD","amount":100}', NOW() AT TIME ZONE 'UTC');
INSERT INTO customer_transaction_event (customer_id, event_type, event_version, event_content, create_at) VALUES (1, 'DEPOSIT', 1, '{"currency":"USD","amount":100}', NOW() AT TIME ZONE 'UTC');
INSERT INTO customer_transaction_event (customer_id, event_type, event_version, event_content, create_at) VALUES (2, 'DEPOSIT', 1, '{"currency":"HKD","amount":100}', NOW() AT TIME ZONE 'UTC');
INSERT INTO customer_transaction_event (customer_id, event_type, event_version, event_content, create_at) VALUES (2, 'DEPOSIT', 1, '{"currency":"USD","amount":100}', NOW() AT TIME ZONE 'UTC');


-- wallet
CREATE TABLE customer_wallet (
    id              BIGSERIAL   PRIMARY KEY,
    customer_id     BIGINT      NOT NULL,
    currency        CHAR(3)     NOT NULL,
    amount          NUMERIC     NOT NULL
);

CREATE UNIQUE INDEX ON customer_wallet (customer_id, currency);

INSERT INTO customer_wallet (customer_id, currency, amount) VALUES (1, 'HKD', 100);
INSERT INTO customer_wallet (customer_id, currency, amount) VALUES (1, 'USD', 100);
INSERT INTO customer_wallet (customer_id, currency, amount) VALUES (2, 'HKD', 100);
INSERT INTO customer_wallet (customer_id, currency, amount) VALUES (2, 'USD', 100);

CREATE TABLE customer_wallet_event (
    id              BIGSERIAL   PRIMARY KEY,
    customer_id     BIGINT      NOT NULL,
    event_type      TEXT        NOT NULL,
    event_version   SMALLINT    NOT NULL,
    event_content   TEXT        NOT NULL,
    create_at       TIMESTAMP   NOT NULL
);
