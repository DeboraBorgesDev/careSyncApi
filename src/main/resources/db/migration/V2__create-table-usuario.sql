CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    is_medico BOOLEAN DEFAULT FALSE,
    crm VARCHAR(20),
    cpf VARCHAR(14) NOT NULL UNIQUE,
    is_enfermeiro BOOLEAN DEFAULT FALSE,
    coren VARCHAR(20),
    is_estudante BOOLEAN DEFAULT FALSE,
    matricula VARCHAR(20) UNIQUE,
    id_permissao UUID,
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);
