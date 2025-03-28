package it.epicode.biblioteca.entities;

import it.epicode.biblioteca.utils.Periodicita;
import jakarta.persistence.*;

@Entity
@Table(name = "riviste")

public class Rivista extends ElementoCatalogo {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Periodicita periodicita;

    // Costruttore vuoto richiesto da JPA
    public Rivista() {}

    // Costruttore con parametri
    public Rivista(String isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    // Getter e Setter
    public Periodicita getPeriodicita() { return periodicita; }
    public void setPeriodicita(Periodicita periodicita) { this.periodicita = periodicita; }

    @Override
    public String toString() {
        return super.toString() + ", Periodicità: " + periodicita;
    }
}
