INSERT INTO exchange (id, name, country, city) VALUES ('XNAS', 'NASDAQ - ALL MARKETS', 'US', 'New York');
INSERT INTO exchange (id, name, country, city) VALUES ('XNYS', 'NEW YORK STOCK EXCHANGE, INC.', 'US', 'New York');

INSERT INTO stock (symbol, exchange_id, currency, company_name, market_cap, industry, sector) VALUES ('AAPL', 'XNAS', 'USD', 'Apple Inc.', 3677003448320, 'Consumer Electronics', 'Technology');
INSERT INTO stock (symbol, exchange_id, currency, company_name, market_cap, industry, sector) VALUES ('AMZN', 'XNAS', 'USD', 'Amazon.com, Inc.', 2299035123712, 'Internet Retail', 'Consumer Cyclical');