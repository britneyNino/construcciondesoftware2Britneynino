-- Tablas simples para demo (ajusta según tus entidades reales)

CREATE TABLE IF NOT EXISTS CLIENT (
  identification VARCHAR(64) PRIMARY KEY,
  client_type VARCHAR(32),
  email VARCHAR(200),
  phone VARCHAR(50),
  address VARCHAR(300),
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  birth_date DATE,
  company_name VARCHAR(200),
  nit VARCHAR(64),
  legal_representative VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS USERS (
  user_id VARCHAR(64) PRIMARY KEY,
  username VARCHAR(100),
  password VARCHAR(200),
  full_name VARCHAR(200),
  identification VARCHAR(64),
  email VARCHAR(200),
  phone VARCHAR(50),
  address VARCHAR(300),
  birth_date DATE,
  role VARCHAR(50),
  status VARCHAR(50),
  created_at TIMESTAMP,
  client_id VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS BANK_PRODUCT_CATALOG (
  product_code VARCHAR(64) PRIMARY KEY,
  product_name VARCHAR(200),
  category VARCHAR(50),
  requires_approval BOOLEAN
);

CREATE TABLE IF NOT EXISTS BANK_ACCOUNTS (
  product_id VARCHAR(64) PRIMARY KEY,
  account_number VARCHAR(64),
  account_type VARCHAR(50),
  current_balance DECIMAL(19,2),
  status VARCHAR(50),
  currency VARCHAR(10),
  client_id VARCHAR(64)
);

-- Datos de ejemplo
INSERT INTO CLIENT (identification, client_type, email, phone, address, first_name, last_name, birth_date)
VALUES ('CL-1001', 'NATURAL_PERSON', 'juan.perez@example.com', '+571234567890', 'Calle Falsa 123', 'Juan', 'Perez', DATE '1990-01-01');

INSERT INTO USERS (user_id, username, password, full_name, identification, email, phone, address, birth_date, role, status, created_at, client_id)
VALUES ('U-1001','juanp','password','Juan Perez','CL-1001','juan.perez@example.com','+571234567890','Calle Falsa 123', DATE '1990-01-01', 'USER', 'ACTIVE', CURRENT_TIMESTAMP(), 'CL-1001');

INSERT INTO BANK_PRODUCT_CATALOG (product_code, product_name, category, requires_approval)
VALUES ('PR-001','Cuenta Corriente Básica','ACCOUNT', FALSE);

INSERT INTO BANK_ACCOUNTS (product_id, account_number, account_type, current_balance, status, currency, client_id)
VALUES ('ACC-1001','ACC-1001','CHECKING', 100000.00, 'ACTIVE', 'COP', 'CL-1001');

-- Más datos de prueba
INSERT INTO CLIENT (identification, client_type, email, phone, address, first_name, last_name)
VALUES ('CL-2001', 'NATURAL_PERSON', 'maria.gomez@example.com', '+57111222333', 'Av Siempre Viva 742', 'Maria', 'Gomez');

INSERT INTO USERS (user_id, username, password, full_name, identification, email, phone, address, role, status, created_at, client_id)
VALUES ('U-2001','mariag','password','Maria Gomez','CL-2001','maria.gomez@example.com','+57111222333','Av Siempre Viva 742', 'USER', 'ACTIVE', CURRENT_TIMESTAMP(), 'CL-2001');

INSERT INTO BANK_ACCOUNTS (product_id, account_number, account_type, current_balance, status, currency, client_id)
VALUES ('ACC-2001','ACC-2001','SAVINGS', 50000.00, 'ACTIVE', 'COP', 'CL-2001');

INSERT INTO BANK_PRODUCT_CATALOG (product_code, product_name, category, requires_approval)
VALUES ('PR-002','Cuenta Ahorros Plus','ACCOUNT', FALSE);

-- Ejemplo de transferencia pendiente
CREATE TABLE IF NOT EXISTS TRANSFERS (
  transfer_id VARCHAR(64) PRIMARY KEY,
  amount DECIMAL(19,2),
  created_at TIMESTAMP,
  approval_date TIMESTAMP,
  status VARCHAR(50),
  description VARCHAR(500),
  source_account_id VARCHAR(64),
  destination_account_id VARCHAR(64),
  creator_user_id VARCHAR(64),
  approver_user_id VARCHAR(64)
);

INSERT INTO TRANSFERS (transfer_id, amount, created_at, status, description, source_account_id, destination_account_id, creator_user_id)
VALUES ('TR-1001', 25000.00, CURRENT_TIMESTAMP(), 'PENDING', 'Pago factura', 'ACC-1001', 'ACC-2001', 'U-1001');
