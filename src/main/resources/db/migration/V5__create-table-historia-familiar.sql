CREATE TABLE historia_familiar (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_paciente UUID,
    idade_morte_pai INTEGER,
    causa_morte_pai TEXT,
    idade_morte_mae INTEGER,
    causa_morte_mae TEXT,
    doencas_mae TEXT,
    doencas_pai TEXT,
    filhos_saudaveis BOOLEAN DEFAULT TRUE,
    filhos_observacoes TEXT,
    historico_saude_parentes TEXT,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);
