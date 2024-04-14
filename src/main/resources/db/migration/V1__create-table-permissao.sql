CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE permissao (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome_permissao VARCHAR(100) NOT NULL
);
