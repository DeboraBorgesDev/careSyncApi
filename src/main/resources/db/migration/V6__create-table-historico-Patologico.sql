CREATE TABLE historico_patologico (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_paciente UUID,
    queixa_principal TEXT,
    historia_doenca_atual TEXT,
    possui_diabetes BOOLEAN,
    possui_hipertensao BOOLEAN,
    possui_dislipidemia BOOLEAN,
    medicacao_continua BOOLEAN,
    medicacoes TEXT,
    internado_anteriormente BOOLEAN,
    cirurgias_anteriormente BOOLEAN,
    cirurgias_anteriores_observacoes TEXT,
    vacinas TEXT,
    alergias TEXT,
    data_atualizacao TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);
