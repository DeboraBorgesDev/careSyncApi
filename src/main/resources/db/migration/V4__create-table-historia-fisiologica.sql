CREATE TABLE historia_fisiologica (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_paciente UUID REFERENCES paciente(id),
    metodo_parto VARCHAR(100),
    experiencia_parto TEXT,
    saude_infancia TEXT
);