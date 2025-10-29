CREATE TABLE exchange (
    id character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    country character varying(255) NOT NULL,
    city character varying(255) NOT NULL
);

CREATE TABLE stock (
    symbol character varying(255) NOT NULL,
    exchange_id character varying(255) NOT NULL,
    currency character varying(255) NOT NULL,
    company_name character varying(255) NOT NULL,
    market_cap numeric(50,0) NOT NULL,
    industry character varying(255) NOT NULL,
    sector character varying(255) NOT NULL
);

CREATE TABLE candlestick (
    stock_id character varying(255) NOT NULL,
    "date" date NOT NULL,
    open numeric(10,3) NOT NULL,
    high numeric(10,3) NOT NULL,
    low numeric(10,3) NOT NULL,
    close numeric(10,3) NOT NULL
);


ALTER TABLE exchange
    ADD CONSTRAINT exchange_pkey PRIMARY KEY (id);

ALTER TABLE stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (symbol);

ALTER TABLE candlestick
    ADD CONSTRAINT candlestick_pkey PRIMARY KEY (stock_id, "date");


CREATE INDEX stock_exchange_id ON stock USING btree (exchange_id);

CREATE INDEX candlestick_stock_id ON candlestick USING btree (stock_id);


ALTER TABLE stock
    ADD CONSTRAINT stock_exchange_id_fkey FOREIGN KEY (exchange_id) REFERENCES exchange(id);

ALTER TABLE candlestick
    ADD CONSTRAINT candlestick_stock_id_fkey FOREIGN KEY (stock_id) REFERENCES stock(symbol);
