package it.epicode.biblioteca.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "elemento_id", nullable = false)
    private ElementoCatalogo elementoPrestato;

    @Column(nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(nullable = false)
    private LocalDate dataRestituzionePrevista;

    private LocalDate dataRestituzioneEffettiva;


    public Prestito() {}

    // Costruttore con parametri
    public Prestito(Utente utente, ElementoCatalogo elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30); // Scadenza a 30 giorni
        this.dataRestituzioneEffettiva = null;
    }

    // Getter e Setter
    public Long getId() { return id; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public ElementoCatalogo getElementoPrestato() { return elementoPrestato; }
    public void setElementoPrestato(ElementoCatalogo elementoPrestato) { this.elementoPrestato = elementoPrestato; }

    public LocalDate getDataInizioPrestito() { return dataInizioPrestito; }
    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    public LocalDate getDataRestituzionePrevista() { return dataRestituzionePrevista; }

    public LocalDate getDataRestituzioneEffettiva() { return dataRestituzioneEffettiva; }
    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito di " + elementoPrestato.getTitolo() + " a " + utente.getNome() + " " + utente.getCognome() +
                ", Inizio: " + dataInizioPrestito + ", Prevista: " + dataRestituzionePrevista +
                ", Restituzione Effettiva: " + (dataRestituzioneEffettiva != null ? dataRestituzioneEffettiva : "Non ancora restituito");
    }
}
