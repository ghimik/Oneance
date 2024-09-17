CREATE TYPE user_role AS ENUM ('investor', 'administrator');

CREATE TYPE transaction_type AS ENUM ('buy', 'sell');

-- Create the table for users
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role user_role NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE companies (
    company_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    ticker VARCHAR(10) UNIQUE NOT NULL,
    capitalization DECIMAL(20, 2) DEFAULT 0.00,
    total_shares BIGINT NOT NULL,
    available_shares BIGINT NOT NULL CHECK (available_shares >= 0),
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE stocks (
    stock_id SERIAL PRIMARY KEY,
    company_id INT REFERENCES companies(company_id) ON DELETE CASCADE,
    user_id INT REFERENCES users(user_id) ON DELETE SET NULL,
    shares_owned BIGINT NOT NULL CHECK (shares_owned >= 0),
    purchase_price DECIMAL(20, 2) NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE stock_price_history (
    history_id SERIAL PRIMARY KEY,
    company_id INT REFERENCES companies(company_id) ON DELETE CASCADE,
    recorded_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    price DECIMAL(20, 2) NOT NULL,
    volume BIGINT DEFAULT 0
);

CREATE TABLE transactions (
    transaction_id SERIAL PRIMARY KEY,
    stock_id INT REFERENCES stocks(stock_id) ON DELETE CASCADE,
    user_id INT REFERENCES users(user_id) ON DELETE SET NULL,
    type transaction_type NOT NULL,
    shares BIGINT NOT NULL CHECK (shares > 0),
    price DECIMAL(20, 2) NOT NULL, -- price per share at transaction
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE sessions (
    session_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
    session_token TEXT NOT NULL,
    csrf_token TEXT NOT NULL,
    expires_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_companies_ticker ON companies(ticker);
CREATE INDEX idx_sessions_user_id ON sessions(user_id);
