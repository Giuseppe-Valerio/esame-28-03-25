package it.epicode.biblioteca.entities;

import it.epicode.biblioteca.utils.Genere;
import jakarta.persistence.*;

@Entity
@Table(name = "libri")

public class Libro extends ElementoCatalogo {

    @Column(nullable = false, length = 100)
    private String autore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Genere genere;


    public Libro() {}


    public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }


    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }

    public Genere getGenere() { return genere; }
    public void setGenere(Genere genere) { this.genere = genere; }

    @Override
    public String toString() {
        return super.toString() + ", Autore: " + autore + ", Genere: " + genere;
    }
}
