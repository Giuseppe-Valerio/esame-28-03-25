package it.epicode.biblioteca.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String cognome;

    @Column(nullable = false)
    private LocalDate dataNascita;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroTessera;

    // Costruttore vuoto richiesto da JPA
    public Utente() {}

    // Costruttore con parametri
    public Utente(String nome, String cognome, LocalDate dataNascita, String numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }

    // Getter e Setter
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

    public String getNumeroTessera() { return numeroTessera; }
    public void setNumeroTessera(String numeroTessera) { this.numeroTessera = numeroTessera; }

    @Override
    public String toString() {
        return "Utente: " + nome + " " + cognome + " (Tessera: " + numeroTessera + ")";
    }
}
