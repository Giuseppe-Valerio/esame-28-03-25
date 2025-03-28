package it.epicode.biblioteca.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.JOINED) // Mantiene la gerarchia delle tabelle
public abstract class ElementoCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false, length = 255)
    private String titolo;

    @Column(nullable = false)
    private int annoPubblicazione;

    @Column(nullable = false)
    private int numeroPagine;


    public ElementoCatalogo() {}


    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }


    public Long getId() { return id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public void setAnnoPubblicazione(int annoPubblicazione) { this.annoPubblicazione = annoPubblicazione; }

    public int getNumeroPagine() { return numeroPagine; }
    public void setNumeroPagine(int numeroPagine) { this.numeroPagine = numeroPagine; }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Titolo: " + titolo + ", Anno: " + annoPubblicazione + ", Pagine: " + numeroPagine;
    }
}
