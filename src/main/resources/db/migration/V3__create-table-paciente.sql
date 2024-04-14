CREATE TABLE paciente (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    sexo VARCHAR(10),
    estado_civil VARCHAR(20),
    possui_filhos BOOLEAN DEFAULT FALSE,
    profissao VARCHAR(100),
    religiao VARCHAR(50),
    nivel_ensino VARCHAR(50),
    possui_acompanhante BOOLEAN DEFAULT FALSE,
    nome_acompanhante VARCHAR(255)
);
