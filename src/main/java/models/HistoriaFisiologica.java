package models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historia_fisiologica")
public class HistoriaFisiologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", unique = true)
    private Paciente paciente;

    @Column(name = "metodo_parto", length = 100)
    private String metodoParto;

    @Column(name = "experiencia_parto", columnDefinition = "TEXT")
    private String experienciaParto;

    @Column(name = "saude_infancia", columnDefinition = "TEXT")
    private String saudeInfancia;

    public HistoriaFisiologica(UUID id, Paciente paciente, String metodoParto, String experienciaParto,
            String saudeInfancia) {
        this.id = id;
        this.paciente = paciente;
        this.metodoParto = metodoParto;
        this.experienciaParto = experienciaParto;
        this.saudeInfancia = saudeInfancia;
    }

    public HistoriaFisiologica() {}

}