CREATE TABLE internacao (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_paciente UUID,
    data_entrada TIMESTAMP,
    data_saida TIMESTAMP,
    motivo TEXT,
    diagnostico TEXT,
    observacoes TEXT,
    id_medico UUID,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_medico) REFERENCES usuarios(id)
);