CREATE TABLE sinais_vitais (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_paciente UUID,
    id_profissional UUID,
    data_hora TIMESTAMP,
    freq_cardiaca INTEGER,
    freq_respiratoria INTEGER,
    pressao_arterial VARCHAR(20),
    constipacao BOOLEAN,
    glicemia FLOAT,
    temperatura FLOAT,
    oxigenacao FLOAT,
    peso FLOAT,
    mobilidade BOOLEAN,
    observacoes TEXT,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_profissional) REFERENCES usuarios(id)
);
